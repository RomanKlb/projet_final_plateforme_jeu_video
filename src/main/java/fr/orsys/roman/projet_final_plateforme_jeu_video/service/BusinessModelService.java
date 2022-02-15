package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import java.util.List;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.BusinessModel;

public interface BusinessModelService {

	BusinessModel createModel(String name);
	
	List<BusinessModel> getAll();
	BusinessModel getById(Long id);
	BusinessModel getByName(String name);
	
	boolean deleteById(Long id);
	
}
