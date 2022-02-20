package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Reviews;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.CreateReviewsDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.GameNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.GamerNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.ModeratorNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.ReviewsNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ReviewsService;

@RestController
@RequestMapping(path="/reviews") 
@Validated
public class ReviewsController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private final ReviewsService reviewsService;

	public ReviewsController(ReviewsService reviewsService) {
		super();
		this.reviewsService = reviewsService;
	}

	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	public List<String> traiterDonneesInvalidesAvecDetails(ConstraintViolationException exception) {
		return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
	}
	
	@ExceptionHandler(fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.NotFoundException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public String traiterModeratorNotFoundException(Exception exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.ReviewsNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String traiterReviewsrNotFoundException(Exception exception) {
		return exception.getMessage();
	}
	
	@PatchMapping("/{idReviews}/moderator/{idModerator}")
	public Reviews moderationReviews(@PathVariable Long idReviews, @PathVariable Long idModerator) throws ModeratorNotFoundException, ReviewsNotFoundException {
		log.info("Controller moderationReviews");
		return reviewsService.moderationReviews(idReviews, idModerator);
	}

	@PostMapping("/save")
	public Reviews saveOneReviews(@Valid @RequestBody CreateReviewsDto reviewsDto, BindingResult result) throws GameNotFoundException, GamerNotFoundException {
		log.info("Controller saveOneReviews");
		return reviewsService.saveOneReviews(reviewsDto);
	}

	@GetMapping("/{id}")
	public Reviews findOneReviews(@PathVariable Long id) throws ReviewsNotFoundException {
		log.info("Controller findOneReviews");
		Reviews reviews = reviewsService.findOneReviews(id);
		if (reviews == null) {
			throw new ReviewsNotFoundException("L'avis d'id " + id + " n'existe pas");
		}
		return reviewsService.findOneReviews(id);
	}

	@GetMapping("/all")
	public List<Reviews> findAllReviews() {
		log.info("Controller findAllReviews");
		return reviewsService.findAllReviews();
	}

	@GetMapping("/{id}/delete")
	public boolean deleteOneReviews(@PathVariable Long id) {
		log.info("Controller deleteOneReviews");
		return reviewsService.deleteOneReviews(id);
	}
	
	@GetMapping("/game/{id}")
	public List<Reviews> findAllByGameId(@PathVariable Long id) {
		return reviewsService.findAllByGameId(id);
	}
}
