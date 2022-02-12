package fr.orsys.roman.projet_final_plateforme_jeu_video.business;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"dateDeNaissance"})
public class Gamer extends User{

	@Past(message = "La date de naissance doit être dans le passé")
	private LocalDate dateDeNaissance;

	@OneToMany(mappedBy = "joueur" , fetch = FetchType.EAGER)
	private List<Reviews> avis;


	public Gamer(
			@NotNull(message = "Le pseudo ne peut pas être vide") @NotBlank(message = "Le pseudo doit être complété") @Pattern(regexp = "^([A-Za-z0-9]+)*", message = "votre pseudo doit être composé uniquement de majuscule, minuscule et/ou chiffre") String pseudo,
			@NotNull(message = "Le mot de passe ne peut pas être vide") @NotBlank(message = "Le mot de passe doit être complété") @Length(min = 6, message = "Mettre un mot de passe de minimum 6 caractères") String motDePasse,
			@NotBlank(message = "Merci de préciser une adresse email")  String email, @Past(message = "La date de naissance doit être dans le passé") LocalDate dateDeNaissance) {
		super(pseudo, motDePasse, email);
		this.dateDeNaissance = dateDeNaissance;
	}


}
