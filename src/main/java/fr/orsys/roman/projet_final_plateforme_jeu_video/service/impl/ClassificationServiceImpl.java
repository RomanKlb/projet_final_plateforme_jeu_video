package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import java.util.List;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Classification;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.ClassificationRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ClassificationService;

public class ClassificationServiceImpl implements ClassificationService {
	
	private final ClassificationRepository cRepo;

	public ClassificationServiceImpl(ClassificationRepository cRepo) {
		this.cRepo = cRepo;
	}
	
	@Override
	public Classification createClassification(Classification classification) {
		cRepo.save(classification);
		return classification;
	}

	@Override
	public List<Classification> getClassifications() {
		return cRepo.findAll();
	}

	@Override
	public Classification getClassificationById(Long id) {
		return cRepo.findById(id).orElse(null);
	}

	@Override
	public boolean deleteClassification(Long id) {
		cRepo.deleteById(id);
		return cRepo.existsById(id);
	}
	
}
