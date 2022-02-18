package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.BusinessModel;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Editor;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.BusinessModelService;
/**
 * 
 * @author Jurufola
 *
 */
@RestController
@RequestMapping("/businessModel")
public class BusinessModelController {
	private final BusinessModelService businessModelService;

	/**
	 * @param editorService
	 */
	public BusinessModelController(BusinessModelService businessModelService) {
		this.businessModelService = businessModelService;
	}
	
	@GetMapping("/all")
	public List<BusinessModel> getBusinessModels() {
		return businessModelService.getAll();
	}
	
	@GetMapping("/{id}")
	public BusinessModel getBusinessModelById(@PathVariable Long id) {
		return businessModelService.getById(id);
	}
}
