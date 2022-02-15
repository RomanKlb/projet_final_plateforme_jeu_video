package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Reviews;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.CreateReviewsDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ReviewsService;

@RestController
@RequestMapping(path="/reviews") 
public class ReviewsController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private final ReviewsService reviewsService;

	public ReviewsController(ReviewsService reviewsService) {
		super();
		this.reviewsService = reviewsService;
	}

	@PostMapping("save")
	public Reviews saveOneReviews(CreateReviewsDto reviewsDto) {
		return null;
	}


	@GetMapping("{id}")
	public Reviews findOneReviews(@PathVariable Long id) {
		log.info("Controller findOneReviews");
		return reviewsService.findOneReviews(id);
	}

	@GetMapping("all")
	public List<Reviews> findAllReviews() {
		log.info("Controller findAllReviews");
		return reviewsService.findAllReviews();
	}

	@GetMapping("{id}/delete")
	public boolean deleteOneReviews(@PathVariable Long id) {
		log.info("Controller deleteOneReviews");
		return reviewsService.deleteOneReviews(id);
	}
}
