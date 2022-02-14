package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Classification;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.ClassificationRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ClassificationService;

@Service
public class ClassificationServiceImpl implements ClassificationService {
	
	private final ClassificationRepository classificationRepository;

	public ClassificationServiceImpl(ClassificationRepository repo) {
		this.classificationRepository = repo;
	}
	
	@Override
	public Classification createClassification(String name) {
		return classificationRepository.save(new Classification(name));
	}

	@Override
	public List<Classification> getClassifications() {
		return classificationRepository.findAll();
	}

	@Override
	public Classification getClassificationById(Long id) {
		return classificationRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deleteClassification(Long id) {
		classificationRepository.deleteById(id);
		return !classificationRepository.existsById(id);
	}
	
}
