package fr.orsys.roman.projet_final_plateforme_jeu_video.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;

@Repository
public interface GamerRepository extends JpaRepository<Gamer, Long>{

	boolean existsByEmail(String email);
	boolean existsByPseudo(String pseudo);
	
	Optional<Gamer> findByEmail(String email);
	Optional<Gamer> findByPseudo(String pseudo);

}
