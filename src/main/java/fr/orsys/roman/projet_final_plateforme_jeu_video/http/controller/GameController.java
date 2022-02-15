package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Game;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.GameDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.DateIsInTheFuturException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GameService;
/**
 * 
 * @author Jurufola
 * Controller for Game CRUD
 *
 */
@RestController
@RequestMapping(path="/game") 
public class GameController {
	private final GameService gameService;
	Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * @param gameService
	 */
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@PostMapping("/save")
	public Game addGame(@Valid @RequestBody GameDto gameDto, BindingResult result) throws DateIsInTheFuturException {
		log.info(gameDto.toString());
		if (gameDto.getReleaseDate() == null) {
			gameDto.setReleaseDate(LocalDate.now());
		}
		if (gameDto.getReleaseDate().isAfter(LocalDate.now())) {
			throw new DateIsInTheFuturException("La date sortie soit etre dans le passé");
		}
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError objectError : errors) {
				log.error(objectError.getDefaultMessage());
			}
		}
		return this.gameService.saveGame(gameDto);
	}
	
	@ExceptionHandler(DateIsInTheFuturException.class)
	//@ResponseStatus(code = HttpStatus.)
	public String traiterDateIsInTheFuturException() {
		return "La date de sortie ne peut être dans le futur";
	}
	
	@GetMapping("{id}")
	public Game findOneGame(@PathVariable Long id) {
		log.info("controller findOneGame");
		return gameService.findOneGame(id);
	}
	
	@DeleteMapping("{id}/delete")
	public boolean deleteOneGame(@PathVariable Long id) {
		log.info("controller deleteOneGame");
		return gameService.deleteOneGame(id);
	}
}
