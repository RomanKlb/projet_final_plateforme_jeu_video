package fr.orsys.roman.projet_final_plateforme_jeu_video.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Moderator;

public interface ModeratorRepository extends JpaRepository<Moderator, Long>{

	boolean existsByEmail(String email);
	boolean existsByPseudo(String pseudo);
	
	Optional<Moderator> findByEmail(String email);
	Optional<Moderator> findByPseudo(String pseudo);
}
