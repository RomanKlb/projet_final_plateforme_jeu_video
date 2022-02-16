package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Genre;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GenreService;
@RestController
@RequestMapping("/genre")
public class GenreController {
	private final GenreService genreService;

	/**
	 * @param GenreService
	 */
	public GenreController(GenreService genreService) {
		this.genreService = genreService;
	}
	
	@GetMapping("/all")
	public List<Genre> getGenres() {
		return genreService.getGenres();
	}
	
	@GetMapping("/{id}")
	public Genre getGenreById(@PathVariable Long id) {
		return genreService.getGenreById(id);
	}
}
