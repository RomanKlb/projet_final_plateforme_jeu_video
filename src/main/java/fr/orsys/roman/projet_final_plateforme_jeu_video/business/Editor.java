package fr.orsys.roman.projet_final_plateforme_jeu_video.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(of = {"id", "name"})
@EqualsAndHashCode
@NoArgsConstructor
public class Editor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Le name d'éditeur ne peut pas être vide")
	@NotBlank(message = "Le name d'éditeur doit être complété")
	private String name;
	
	@OneToMany(mappedBy = "editor", fetch = FetchType.EAGER)
	private List<Game> games;

	
	public Editor(
			@NotNull(message = "Le name de la classification ne peut pas être vide") @NotBlank(message = "Le name de la classification doit être complété") String name) {
		super();
		this.name = name;
	}
	
}
