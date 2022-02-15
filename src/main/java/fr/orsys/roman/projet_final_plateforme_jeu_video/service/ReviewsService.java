package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import java.util.List;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Reviews;

public interface ReviewsService {

	Reviews findOneReviews(Long id);

	List<Reviews> findAllReviews();

	boolean deleteOneReviews(Long id);

}
