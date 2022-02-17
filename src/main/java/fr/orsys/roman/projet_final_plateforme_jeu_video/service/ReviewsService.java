package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import java.util.List;

import javax.validation.Valid;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Reviews;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.CreateReviewsDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.GameNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.GamerNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.ModeratorNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.ReviewsNotFoundException;

public interface ReviewsService {

	Reviews findOneReviews(Long id);

	List<Reviews> findAllReviews();
	
	List<Reviews> findAllByGameId(Long id); 

	boolean deleteOneReviews(Long id);

	Reviews saveOneReviews(@Valid CreateReviewsDto reviewsDto) throws GameNotFoundException, GamerNotFoundException;

	Reviews moderationReviews(Long idReviews, Long idModerator) throws ModeratorNotFoundException, ReviewsNotFoundException;

}
