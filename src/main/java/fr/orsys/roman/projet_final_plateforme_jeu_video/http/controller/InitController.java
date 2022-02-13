package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Classification;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ClassificationService;

@Controller
public class InitController {
	
	@Autowired
	private ClassificationService classificationService;
	
	/*public InitController(ClassificationService classificationService) {
		this.classificationService = classificationService;
	}*/
	
	@PostConstruct
	private void init() {
		initClassifications();
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
	
}
