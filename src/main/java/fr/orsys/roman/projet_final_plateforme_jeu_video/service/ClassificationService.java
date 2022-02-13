package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import java.util.List;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Classification;

public interface ClassificationService {
	
	Classification createClassification(Classification classification);
	
	List<Classification> getClassifications();
	Classification getClassificationById(Long id);
	
	boolean deleteClassification(Long id);
}
