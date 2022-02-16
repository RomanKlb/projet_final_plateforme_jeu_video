package fr.orsys.roman.projet_final_plateforme_jeu_video.business;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Reviews {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "La description doit être complété")
	@Length(max = 5000, message = "La description est maximum de 5000 caractères")
	private String description;
	
	private LocalDateTime sendingDate;
	
	@NotNull(message = "La note doit être complété")
	@Range(min = 0, max = 20, message = "La notation est compris entre 0 et 20")
	private Float rating;
	
	private LocalDateTime moderatorDate;

	@ManyToOne
	private Gamer gamer;
	
	@ManyToOne
	private Moderator moderator;
	
	@ManyToOne
	private Game game;
	
	public Reviews() {
		sendingDate = LocalDateTime.now();
	}

	public Reviews(@Length(max = 5000, message = "La description est maximum de 5000 caractères") String description,
			@Range(min = 0, max = 20, message = "La notation est compris entre 0 et 20") Float rating, Gamer gamer,
			Moderator moderator, Game game) {
		this();
		this.description = description;
		this.rating = rating;
		this.gamer = gamer;
		this.moderator = moderator;
		this.game = game;
	}


	
}
