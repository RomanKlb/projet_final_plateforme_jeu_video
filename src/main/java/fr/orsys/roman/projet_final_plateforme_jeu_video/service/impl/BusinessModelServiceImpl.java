package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.BusinessModel;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.BusinessModelRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.BusinessModelService;

@Service
public class BusinessModelServiceImpl implements BusinessModelService {

	private final BusinessModelRepository businessModelRepository;
	
	public BusinessModelServiceImpl(BusinessModelRepository businessModelRepository) {
		this.businessModelRepository = businessModelRepository;
	}
	
	@Override
	public BusinessModel createModel(String name) {
		if(name == "") {
			throw new IllegalArgumentException("Le nom ne peut pas être vide");
		}
		return businessModelRepository.save(new BusinessModel(name));
	}

	@Override
	public List<BusinessModel> getAll() {
		return businessModelRepository.findAll();
	}

	@Override
	public BusinessModel getById(Long id) {
		return businessModelRepository.findById(id).orElse(null);
	}
	
	@Override
	public BusinessModel getByName(String name) {
		return businessModelRepository.findByName(name);
	}

	@Override
	public boolean deleteById(Long id) {
		businessModelRepository.deleteById(id);
		return !businessModelRepository.existsById(id);
	}

}
