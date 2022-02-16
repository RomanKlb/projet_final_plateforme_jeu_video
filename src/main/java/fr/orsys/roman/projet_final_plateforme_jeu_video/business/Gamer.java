package fr.orsys.roman.projet_final_plateforme_jeu_video.business;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Gamer extends User{

	@NotNull(message = "La date de naissance ne peut pas être vide")
	@DateTimeFormat(iso = ISO.DATE)
	@Past(message = "La date de naissance doit être dans le passé")
	private LocalDate birthDate;

	@JsonIgnore
	@OneToMany(mappedBy = "gamer" , fetch = FetchType.LAZY)
	private List<Reviews> reviews;


	public Gamer(
			@NotNull(message = "Le pseudo ne peut pas être vide") @NotBlank(message = "Le pseudo doit être complété") @Pattern(regexp = "^([A-Za-z0-9]+)*", message = "votre pseudo doit être composé uniquement de majuscule, minuscule et/ou chiffre") String pseudo,
			@NotNull(message = "Le mot de passe ne peut pas être vide") @NotBlank(message = "Le mot de passe doit être complété") @Length(min = 6, message = "Mettre un mot de passe de minimum 6 caractères") String password,
			@NotBlank(message = "Merci de préciser une adresse email")  String email, @Past(message = "La date de naissance doit être dans le passé") LocalDate birthDate) {
		super(pseudo, password, email);
		this.birthDate = birthDate;
	}


}
