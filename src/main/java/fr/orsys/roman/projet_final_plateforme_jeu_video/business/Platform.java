package fr.orsys.roman.projet_final_plateforme_jeu_video.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Platform {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Le name de la classification ne peut pas être vide")
	@NotBlank(message = "Le name de la classification doit être complété")
	private String name;
	
	//@ToString.Exclude
	/*@OneToMany(mappedBy = "platform", fetch = FetchType.LAZY)
	private List<Game> games;*/
	@JsonIgnore
	@ToString.Exclude
	@ManyToMany(mappedBy = "platforms")
	private List<Game> games;
	
	public Platform(
			@NotNull(message = "Le name de la classification ne peut pas être vide") @NotBlank(message = "Le name de la classification doit être complété") String name) {
		super();
		this.name = name;
	}
	public Platform( Long id,
			@NotNull(message = "Le name de la classification ne peut pas être vide") @NotBlank(message = "Le name de la classification doit être complété") String name) {
		this();
		this.id = id;
		this.name = name;
	}
}
