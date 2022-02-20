package fr.orsys.roman.projet_final_plateforme_jeu_video.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Game;

public interface GameRepository extends JpaRepository<Game, Long>{
	//boolean existByNameAndEditorNameAndReleaseDate(String name, String editorName, LocalDate releaseDate);
	Game findByNameAndEditorNameAndReleaseDate(String name, String editorName, LocalDate releaseDate);
}
