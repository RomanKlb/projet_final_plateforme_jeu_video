package fr.orsys.roman.projet_final_plateforme_jeu_video.business;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@ToString(of = {"id","pseudo","email"})
@EqualsAndHashCode
public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Le pseudo ne peut pas être vide")
	@NotBlank(message = "Le pseudo doit être complété")
	@Pattern(regexp = "^([A-Za-z0-9]+)*", message = "votre pseudo doit être composé uniquement de majuscule, minuscule et/ou chiffre")
	private String pseudo;
	
	@NotNull(message = "Le mot de passe ne peut pas être vide")
	@NotBlank(message = "Le mot de passe doit être complété")
	@Length(min = 6, message = "Mettre un mot de passe de minimum 6 caractères")
	private String motDePasse;
	
	@NotBlank(message="Merci de préciser une adresse email")
//	@Email(regexp = "^[a-zA-Z0-9]*[^!#$%&;:§'*+=?`{|}~^.-]+[a-zA-Z0-9]*+[-.]{0,1}+[a-zA-Z0-9]*[^!#$%&;:§'*+=?`{|}~^.-]@[a-zA-Z0-9-]+[.]{1}+[a-zA-Z]{2,6}$", 
//	message = "Il faut un email valide")
	private String email;
	
	private LocalDateTime dateTime;

	public User() {
		dateTime = LocalDateTime.now();
	}

	

	public User(
			@NotNull(message = "Le pseudo ne peut pas être vide") @NotBlank(message = "Le pseudo doit être complété") @Pattern(regexp = "^([A-Za-z0-9]+)*", message = "votre pseudo doit être composé uniquement de majuscule, minuscule et/ou chiffre") String pseudo,
			@NotNull(message = "Le mot de passe ne peut pas être vide") @NotBlank(message = "Le mot de passe doit être complété") @Length(min = 6, message = "Mettre un mot de passe de minimum 6 caractères") String motDePasse,
			@NotBlank(message = "Merci de préciser une adresse email") String email) {
		this();
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.email = email;
	}

	
	
	
	
}
