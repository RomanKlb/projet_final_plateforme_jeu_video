package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Classification;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Editor;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.EditorService;

@RestController
@RequestMapping("/editor")
public class EditorController {
	private final EditorService editorService;

	/**
	 * @param editorService
	 */
	public EditorController(EditorService editorService) {
		this.editorService = editorService;
	}
	
	@GetMapping("/all")
	public List<Editor> getEditors() {
		return editorService.getEditors();
	}
	
	@GetMapping("/{id}")
	public Editor getEditorById(@PathVariable Long id) {
		return editorService.getEditorById(id);
	}
}
