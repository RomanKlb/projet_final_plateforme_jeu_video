package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import java.util.List;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Editor;

public interface EditorService {
	
	Editor createEditor(String name);
	
	List<Editor> getEditors();
	Editor getEditorById(Long id);
	Editor getEditorByName(String name);
	
	boolean deleteEditorById(Long id);
	
}
