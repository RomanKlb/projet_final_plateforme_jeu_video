package fr.orsys.roman.projet_final_plateforme_jeu_video.business;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(of = {"id","description","dateEnvoi","note","dateModeration","joueur","moderateur","jeu" })
@EqualsAndHashCode
@NoArgsConstructor
public class Reviews {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Length(max = 5000, message = "La description est maximum de 5000 caractères")
	private String description;
	
	private LocalDateTime dateEnvoi;
	
	@Range(min = 0, max = 5, message = "La notation est compris entre 0 et 5 étoiles")
	private Float note;
	
	private LocalDateTime dateModeration;
	
	@ManyToOne
	private Gamer joueur;
	
	@ManyToOne
	private Moderator moderateur;
	
	@ManyToOne
	private Game jeu;

	public Reviews(@Length(max = 5000, message = "La description est maximum de 5000 caractères") String description,
			LocalDateTime dateEnvoi,
			@Range(min = 0, max = 5, message = "La notation est compris entre 0 et 5 étoiles") Float note,
			Gamer joueur, Game jeu) {
		super();
		this.description = description;
		this.dateEnvoi = dateEnvoi;
		this.note = note;
		this.joueur = joueur;
		this.jeu = jeu;
	}
	
	

	
	
	
	
	
}
