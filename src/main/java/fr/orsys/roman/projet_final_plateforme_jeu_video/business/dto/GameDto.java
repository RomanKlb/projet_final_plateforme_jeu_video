package fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto;

import java.time.LocalDate;
import java.util.List;

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
	String name;
	String description;
	LocalDate releaseDate;
	String classificationName;
	String genreName;
	String editorName;
	List<String> platformNames;
	String businessModelName;
}
