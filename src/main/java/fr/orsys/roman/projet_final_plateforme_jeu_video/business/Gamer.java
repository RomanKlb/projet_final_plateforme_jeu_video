package fr.orsys.roman.projet_final_plateforme_jeu_video.business;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;


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

	
	public Gamer(@Past(message = "La date de naissance doit être dans le passé") LocalDate dateDeNaissance) {
		super();
		this.dateDeNaissance = dateDeNaissance;
	}
	
	
}
