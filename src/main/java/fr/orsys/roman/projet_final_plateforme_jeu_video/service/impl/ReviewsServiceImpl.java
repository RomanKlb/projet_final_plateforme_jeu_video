package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Reviews;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.CreateReviewsDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.ModeratorNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.ReviewsNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.ReviewsRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GameService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GamerService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ModeratorService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ReviewsService;

@Service
public class ReviewsServiceImpl implements ReviewsService{

	private final ReviewsRepository reviewsRepository;
	private final GamerService gamerService;
	private final GameService gameService;
	private final ModeratorService moderatorService;

	public ReviewsServiceImpl(ReviewsRepository reviewsRepository, GamerService gamerService, GameService gameService, ModeratorService moderatorService) {
		super();
		this.reviewsRepository = reviewsRepository;
		this.gamerService = gamerService;
		this.gameService = gameService;
		this.moderatorService = moderatorService;
	}


	@Override
	public Reviews saveOneReviews(@Valid CreateReviewsDto reviewsDto) {
		Reviews reviews = new Reviews();
		reviews.setDescription(reviewsDto.getDescription());
		reviews.setRating(reviewsDto.getRating());
		reviews.setGame(gameService.getById(reviewsDto.getGameId()));
		reviews.setGamer(gamerService.findByIdGamer(reviewsDto.getGamerId()));
		return reviewsRepository.save(reviews);
	}

	@Override
	public Reviews findOneReviews(Long id) {
		return reviewsRepository.findById(id).orElseThrow();
	}

	@Override
	public List<Reviews> findAllReviews() {
		return reviewsRepository.findAll();
	}

	@Override
	public boolean deleteOneReviews(Long id) {
		if(reviewsRepository.existsById(id)) {
			reviewsRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Reviews moderationReviews(Long idReviews, Long idModerator) throws ModeratorNotFoundException, ReviewsNotFoundException {
		if(reviewsRepository.existsById(idReviews)) {
			if(moderatorService.moderatorExist(idModerator)) {
				Optional<Reviews> reviews = reviewsRepository.findById(idModerator);
				reviews.get().setModerator(moderatorService.findByIdModerator(idModerator));
				reviews.get().setModeratorDate(LocalDateTime.now());
				return reviews.get();
			} else {
				throw new ModeratorNotFoundException("moderator not found");
			}
		} else {
			throw new ReviewsNotFoundException("reviews not found");
		}
	}

}
