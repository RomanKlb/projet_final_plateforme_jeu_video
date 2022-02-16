package fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Game;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreateReviewsDto {

	@NotBlank(message = "La description doit être complété")
	@Length(max = 5000, message = "La description est maximum de 5000 caractères")
	String description;

//	@NotBlank(message = "La note doit être complété")
	@Range(min = 0, max = 20, message = "La notation est compris entre 0 et 20")
	Float rating;
	
	Long gamerId;

	Long gameId;
}
