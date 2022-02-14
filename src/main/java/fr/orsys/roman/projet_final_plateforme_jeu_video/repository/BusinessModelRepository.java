package fr.orsys.roman.projet_final_plateforme_jeu_video.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.BusinessModel;

public interface BusinessModelRepository extends JpaRepository<BusinessModel, Long>{
	BusinessModel findByName(String name);
}
