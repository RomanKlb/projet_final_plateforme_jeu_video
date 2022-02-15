package fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto;


import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Game;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;

public class CreateReviewsDto {

	@NotBlank(message = "La description doit être complété")
	@Length(max = 5000, message = "La description est maximum de 5000 caractères")
	String description;

	@NotBlank(message = "La note doit être complété")
	@Range(min = 0, max = 20, message = "La notation est compris entre 0 et 20")
	Float rating;
	
	@NotBlank(message = "Le joueur doit être complété")
	Gamer gamer;

	@NotBlank(message = "Le jeu doit être complété")
	Game game;
}
