package fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString(of = {"name"} )
public class CreateClassificationDto {

	private String name;
	
}
