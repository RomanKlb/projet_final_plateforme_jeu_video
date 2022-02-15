package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Editor;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.EditorRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.EditorService;

@Service
public class EditorServiceImpl implements EditorService {
	
	private final EditorRepository editorRepository;
	
	public EditorServiceImpl(EditorRepository editorRepository) {
		this.editorRepository = editorRepository;
	}

	@Override
	public Editor createEditor(String name) {
		if(name != "") {
			return editorRepository.save(new Editor(name));			
		} else {
			throw new IllegalArgumentException("Le nom ne doit pas être vide");
		}
	}

	@Override
	public List<Editor> getEditors() {
		return editorRepository.findAll();
	}

	@Override
	public Editor getEditorById(Long id) {
		return editorRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deleteEditorById(Long id) {
		editorRepository.deleteById(id);
		return !editorRepository.existsById(id);
	}

}
