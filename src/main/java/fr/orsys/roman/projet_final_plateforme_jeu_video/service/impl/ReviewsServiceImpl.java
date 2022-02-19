package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Reviews;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.CreateReviewsDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.GameNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.GamerNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.ModeratorNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.ReviewsNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.ReviewsRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GameService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GamerService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ModeratorService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ReviewsService;

@Service
public class ReviewsServiceImpl implements ReviewsService{

	Logger log = LoggerFactory.getLogger(this.getClass());
	
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
	public Reviews saveOneReviews(@Valid CreateReviewsDto reviewsDto) throws GameNotFoundException, GamerNotFoundException {
		log.info("Service saveOneReviews");
		if(gamerService.existsById(reviewsDto.getGamerId())) {
			if(gameService.existsById(reviewsDto.getGameId())) {
				Reviews reviews = new Reviews();
				reviews.setDescription(reviewsDto.getDescription());
				reviews.setRating(reviewsDto.getRating());
				reviews.setGame(gameService.getById(reviewsDto.getGameId()));
				reviews.setGamer(gamerService.findByIdGamer(reviewsDto.getGamerId()));
				return reviewsRepository.save(reviews);
			} else {
				throw new GameNotFoundException("game not found");
			}
		} else {
			throw new GamerNotFoundException("gamer not found");
		}
	}

	@Override
	public Reviews findOneReviews(Long id) {
		return reviewsRepository.findById(id).orElse(null);
	}

	@Override
	public List<Reviews> findAllReviews() {
		return reviewsRepository.findAll();
	}
	
	@Override
	public List<Reviews> findAllByGameId(Long id) {
		return reviewsRepository.findAllByGameIdAndModeratorIdIsNotNull(id);
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
				Optional<Reviews> reviews = reviewsRepository.findById(idReviews);
				reviews.get().setModerator(moderatorService.findByIdModerator(idModerator));
				reviews.get().setModeratorDate(LocalDateTime.now());
				return reviewsRepository.save(reviews.get());
			} else {
				throw new ModeratorNotFoundException("moderator not found");
			}
		} else {
			throw new ReviewsNotFoundException("reviews not found");
		}
	}


	@Override
	public long count() {
		return this.reviewsRepository.count();
	}


	@Override
	public Reviews saveReviews(Reviews reviews) {
		return this.reviewsRepository.save(reviews);
	}

}
