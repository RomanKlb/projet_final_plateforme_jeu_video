package fr.orsys.roman.projet_final_plateforme_jeu_video.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}
