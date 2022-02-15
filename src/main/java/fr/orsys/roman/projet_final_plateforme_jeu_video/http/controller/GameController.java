package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Game;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.GameDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GameService;
/**
 * 
 * @author Jurufola
 * Controller for Game CRUD
 *
 */
@RestController
@CrossOrigin(origins = "*")
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
	public Game addGame(@Valid @RequestBody Game game, BindingResult result) {
		log.info(game.toString());
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError objectError : errors) {
				log.error(objectError.getDefaultMessage());
			}
		}
		return null;// this.gameService.saveGame(game);
	}
}
