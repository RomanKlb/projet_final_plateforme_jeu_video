package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Classification;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ClassificationService;

@RestController
@RequestMapping("/classification")
public class ClassificationController {
	
	private final ClassificationService classificationService;
	
	public ClassificationController(ClassificationService classificationService) {
		this.classificationService = classificationService;
	}
	
	@GetMapping("/all")
	public List<Classification> getClassifications() {
		return classificationService.getClassifications();
	}
	
	@GetMapping("/{id}")
	public Classification getClassificationById(@PathVariable Long id) {
		return classificationService.getClassificationById(id);
	}
}
