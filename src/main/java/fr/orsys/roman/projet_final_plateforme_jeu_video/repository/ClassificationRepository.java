package fr.orsys.roman.projet_final_plateforme_jeu_video.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Classification;

public interface ClassificationRepository extends JpaRepository<Classification, Long> {
 Classification findByName(String name);
}
