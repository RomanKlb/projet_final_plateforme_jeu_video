package fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class CreateClassificationDto {

	@NotNull(message = "Le nom de la classification ne peut pas être vide")
	@NotBlank(message = "Le nom de la classification doit être complété")
	private String name;
	
}
