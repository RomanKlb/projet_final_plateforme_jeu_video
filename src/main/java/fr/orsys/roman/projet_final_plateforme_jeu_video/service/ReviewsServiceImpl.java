package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Reviews;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.ReviewsRepository;

@Service
public class ReviewsServiceImpl implements ReviewsService{

	private final ReviewsRepository reviewsRepository;

	public ReviewsServiceImpl(ReviewsRepository reviewsRepository) {
		super();
		this.reviewsRepository = reviewsRepository;
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



}
