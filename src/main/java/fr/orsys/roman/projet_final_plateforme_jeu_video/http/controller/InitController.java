package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Classification;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ClassificationService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GenreService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.PlatformService;

@Controller
public class InitController {
	
	private final ClassificationService classificationService;
	private final PlatformService platformService;
	private final GenreService genreService;

	public InitController(ClassificationService classificationService, PlatformService platformService, GenreService genreService) {
		this.classificationService = classificationService;
		this.platformService = platformService;
		this.genreService = genreService;
	}
	
	@PostConstruct
	private void init() {
		initClassifications();
		initPlatforms();
		initGenres();
	}
	
	private void initClassifications() {
		if(classificationService.getClassifications().size() < 1) {
			classificationService.createClassification("PEGI 3");
			classificationService.createClassification("PEGI 7");
			classificationService.createClassification("PEGI 12");
			classificationService.createClassification("PEGI 16");
			classificationService.createClassification("PEGI 18");
		}
	}
	
	private void initPlatforms() {
		if(platformService.getPlatforms().size() < 1) {
			platformService.createPlatform("PlayStation");
			platformService.createPlatform("PlayStation 2");
			platformService.createPlatform("Xbox");
		}
	}
	
	private void initGenres() {
		if (genreService.count()==0) {
		    this.genreService.addGenre("Sandbox");
		    this.genreService.addGenre("Action");
		    this.genreService.addGenre("Adventure");
		}
	}
	
}
