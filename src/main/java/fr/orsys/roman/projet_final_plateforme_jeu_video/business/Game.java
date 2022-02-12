package fr.orsys.roman.projet_final_plateforme_jeu_video.business;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Le nom du jeu ne peut pas être vide")
	@NotBlank(message = "Le nom du jeu doit être complété")
	private String nom;
	
	@Length(max = 10000, message = "La description est maximum de 10000 caractères")
	private String description;
	
	private LocalDate dateSortie;
	
	private String image;
	
	@OneToMany(mappedBy = "jeu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reviews> avis;
	
	@ManyToOne
	private Classification classification;
	
	@ManyToOne
	private Genre genre;
	
	@ManyToOne
	private Editor editeur;
	
	@ManyToOne
	private Platform plateforme;
	
	@ManyToOne
	private BusinessModel modeleEconomique;

	public Game(
			@NotNull(message = "Le nom du jeu ne peut pas être vide") @NotBlank(message = "Le nom du jeu doit être complété") String nom,
			@Length(max = 10000, message = "La description est maximum de 10000 caractères") String description,
			LocalDate dateSortie, String image, Classification classification, Genre genre, Editor editeur,
			Platform plateforme, BusinessModel modeleEconomique) {
		super();
		this.nom = nom;
		this.description = description;
		this.dateSortie = dateSortie;
		this.image = image;
		this.classification = classification;
		this.genre = genre;
		this.editeur = editeur;
		this.plateforme = plateforme;
		this.modeleEconomique = modeleEconomique;
	}
	
	
	
}
