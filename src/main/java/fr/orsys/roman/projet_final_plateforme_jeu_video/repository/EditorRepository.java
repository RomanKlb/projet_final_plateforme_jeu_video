package fr.orsys.roman.projet_final_plateforme_jeu_video.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Editor;

public interface EditorRepository extends JpaRepository<Editor, Long>{
	Editor findByName(String name);
}
