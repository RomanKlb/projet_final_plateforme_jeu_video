package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import java.util.List;

import javax.validation.Valid;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Reviews;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.CreateReviewsDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.ModeratorNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.ReviewsNotFoundException;

public interface ReviewsService {

	Reviews findOneReviews(Long id);

	List<Reviews> findAllReviews();

	boolean deleteOneReviews(Long id);

	Reviews saveOneReviews(@Valid CreateReviewsDto reviewsDto);

	Reviews moderationReviews(Long idReviews, Long idModerator) throws ModeratorNotFoundException, ReviewsNotFoundException;

}
