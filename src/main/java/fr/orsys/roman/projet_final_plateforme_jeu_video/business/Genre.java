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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Le name du genre ne peut pas être vide")
	@NotBlank(message = "Le name du genre doit être complété")
	private String name;
	
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
	private List<Game> games;

	public Genre(
			@NotNull(message = "Le name du genre ne peut pas être vide") @NotBlank(message = "Le name du genre doit être complété") String name) {
		super();
		this.name = name;
	}
	
	
}
