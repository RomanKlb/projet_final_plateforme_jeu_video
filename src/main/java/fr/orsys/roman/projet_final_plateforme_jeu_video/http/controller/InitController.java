package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RestController;

import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GenreService;

@RestController // REstController renvoie les infos en Json VS Controller renvoie vers une vue
// JSP ou thymleaf
public class InitController {
private final GenreService genreService;

/**
* @param themeService
*/
public InitController(GenreService genreService) {// ,
																								// EmotionService																						// emotionService)																							// {
	this.genreService = genreService;
}

@PostConstruct
private void init() {
if (genreService.count()==0) {
	this.genreService.addGenre("Sandbox");
	this.genreService.addGenre("Action");
	this.genreService.addGenre("Adventure");
}

}
}

