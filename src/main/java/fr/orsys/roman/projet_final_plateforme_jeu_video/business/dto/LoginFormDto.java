package fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class LoginFormDto {

	@NotNull(message = "Le pseudo ne peut pas être vide")
	@NotBlank(message = "Le pseudo doit être complété")
	@Pattern(regexp = "^([A-Za-z0-9]+)*", message = "votre pseudo doit être composé uniquement de majuscule, minuscule et/ou chiffre")
	String pseudo;
	
	@NotNull(message = "Le mot de passe ne peut pas être vide")
	@NotBlank(message = "Le mot de passe doit être complété")
	@Length(min = 6, message = "Mettre un mot de passe de minimum 6 caractères")
	@JsonProperty(access = Access.WRITE_ONLY)
	String password;
}
