package fr.orsys.roman.projet_final_plateforme_jeu_video.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Reviews;

public interface ReviewsRepository extends JpaRepository<Reviews, Long>{
	List<Reviews> findAllByGameIdAndModeratorIdIsNotNull(Long id);
}
