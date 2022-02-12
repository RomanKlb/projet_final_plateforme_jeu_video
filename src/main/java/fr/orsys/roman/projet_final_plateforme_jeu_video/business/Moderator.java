package fr.orsys.roman.projet_final_plateforme_jeu_video.business;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Moderator extends User{

	@Length(min = 10, message = "votre numéro de téléphone est composé de 10 chiffres minimum")
	private String numeroDeTelephone;
}
