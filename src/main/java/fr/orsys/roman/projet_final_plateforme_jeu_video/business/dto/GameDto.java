package fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameDto {
	
    @NotBlank(message = "Le nom du jeu doit être complété")
	String name;
    @NotBlank(message = "La description doit être complétée")
	String description;
	LocalDate releaseDate;
	String image;
	@NotNull(message = "La classification doit être mentionnée")
	String classificationName;
	@NotNull(message = "Le genre doit être mentionné")
	String genreName;
	@NotNull(message = "L'editeur doit être mentionné")
	String editorName;
	@NotNull(message = "La ou les platefomrmes doit ou doivent être mentionnée")
	List<String> platformNames;
	@NotNull(message = "Le modèle économique doit être mentionné")
	String businessModelName;
}
