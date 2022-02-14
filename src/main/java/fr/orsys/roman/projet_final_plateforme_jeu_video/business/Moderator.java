package fr.orsys.roman.projet_final_plateforme_jeu_video.business;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
@ToString(callSuper = true)
public class Moderator extends User{

	@Length(min = 10, message = "votre numéro de téléphone est composé de 10 chiffres minimum")
	private String phoneNumber;

	public Moderator(
			@NotNull(message = "Le pseudo ne peut pas être vide") @NotBlank(message = "Le pseudo doit être complété") @Pattern(regexp = "^([A-Za-z0-9]+)*", message = "votre pseudo doit être composé uniquement de majuscule, minuscule et/ou chiffre") String pseudo,
			@NotNull(message = "Le mot de passe ne peut pas être vide") @NotBlank(message = "Le mot de passe doit être complété") @Length(min = 6, message = "Mettre un mot de passe de minimum 6 caractères") String password,
			@NotBlank(message = "Merci de préciser une adresse email") @Email(regexp = "^[a-zA-Z0-9]*[^!#$%&;:§'*+=?`{|}~^.-]+[a-zA-Z0-9]*+[-.]{0,1}+[a-zA-Z0-9]*[^!#$%&;:§'*+=?`{|}~^.-]@[a-zA-Z0-9-]+[.]{1}+[a-zA-Z]{2,6}$", message = "Il faut un email valide") String email, @Length(min = 10, message = "votre numéro de téléphone est composé de 10 chiffres minimum") String phoneNumber) {
		super(pseudo, password, email);
		this.phoneNumber = phoneNumber;
	}




}
