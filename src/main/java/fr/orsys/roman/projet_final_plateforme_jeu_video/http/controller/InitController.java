package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Classification;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl.ClassificationServiceImpl;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl.PlatformServiceImpl;

@Controller
public class InitController {
	
	private ClassificationServiceImpl classificationService;
	private PlatformServiceImpl platformService;

	public InitController(ClassificationServiceImpl classificationService, PlatformServiceImpl platformService) {
		this.classificationService = classificationService;
		this.platformService = platformService;
	}
	
	@PostConstruct
	private void init() {
		initClassifications();
		initPlatforms();
	}
	
	private void initClassifications() {
		if(classificationService.getClassifications().size() < 1) {
			Classification pegi3 = new Classification("PEGI 3");
			Classification pegi7 = new Classification("PEGI 7");
			Classification pegi12 = new Classification("PEGI 12");
			Classification pegi16 = new Classification("PEGI 16");
			Classification pegi18 = new Classification("PEGI 18");
			classificationService.createClassification(pegi3);
			classificationService.createClassification(pegi7);
			classificationService.createClassification(pegi12);
			classificationService.createClassification(pegi16);
			classificationService.createClassification(pegi18);
		}
	}
	
	private void initPlatforms() {
		/*if(platformService.getPlatforms().size() < 1) {
			
		}*/
	}
	
}
