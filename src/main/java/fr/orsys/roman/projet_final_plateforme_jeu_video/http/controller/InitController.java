package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import fr.orsys.roman.projet_final_plateforme_jeu_video.service.BusinessModelService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ClassificationService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.EditorService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GenreService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.PlatformService;

@Controller
public class InitController {

	private final ClassificationService classificationService;
	private final PlatformService platformService;
	private final GenreService genreService;
	private final BusinessModelService businessModelService;
	private final EditorService editorService;

	public InitController(ClassificationService classificationService, PlatformService platformService,
			GenreService genreService, BusinessModelService businessModelService, EditorService editorService) {
		this.classificationService = classificationService;
		this.platformService = platformService;
		this.genreService = genreService;
		this.businessModelService = businessModelService;
		this.editorService = editorService;
	}

	@PostConstruct
	private void init() {
		initClassifications();
		initPlatforms();
		initGenres();
		initBusinessModel();
		initEditors();
	}

	private void initClassifications() {
		if (classificationService.getClassifications().size() < 1) {
			classificationService.createClassification("PEGI 3");
			classificationService.createClassification("PEGI 7");
			classificationService.createClassification("PEGI 12");
			classificationService.createClassification("PEGI 16");
			classificationService.createClassification("PEGI 18");
		}
	}

	private void initPlatforms() {
		if (platformService.getPlatforms().size() < 1) {
			platformService.createPlatform("PlayStation");
			platformService.createPlatform("PlayStation 2");
			platformService.createPlatform("PlayStation 3");
			platformService.createPlatform("PlayStation 4");
			platformService.createPlatform("PlayStation 5");
			platformService.createPlatform("Xbox");
			platformService.createPlatform("Xbox 360");
			platformService.createPlatform("Xbox One");
			platformService.createPlatform("Xbox Series S");
			platformService.createPlatform("Xbox Series X");
			platformService.createPlatform("PC");
		}
	}

	private void initGenres() {
		if (genreService.count() == 0) {
			genreService.addGenre("Sandbox");
			genreService.addGenre("Action");
			genreService.addGenre("Adventure");
		}
	}

	private void initBusinessModel() {
		if(businessModelService.getAll().size() < 1) {
			businessModelService.createModel("free to play");
			businessModelService.createModel("pay to play");
		}
	}
	
	private void initEditors() {
		if(editorService.getEditors().size() < 1) {
			editorService.createEditor("Electronic Arts");
			editorService.createEditor("Activision");
			editorService.createEditor("Ubisoft");
			editorService.createEditor("Square Enix");
			editorService.createEditor("10 Chambers");
		}
	}
}
