package fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreateGameDto {
	
	@NotBlank(message = "Le nom du jeu doit être complété")
    String name;
    
    @NotNull(message = "Merci de renseir une description")
    @NotBlank(message = "La description doit être complétée")
    String description;
    
    @DateTimeFormat(iso = ISO.DATE)
    @Past(message = "La date de sortie doit être dans le passé")
    LocalDate releaseDate;
    
    @NotNull(message = "La classification doit être mentionnée")
    Long classificationId;
    
    @NotNull(message = "Le genre doit être mentionné")
    Long genreId;
    
    @NotNull(message = "L'editeur doit être mentionné")
    Long editorId;
    
    @NotNull(message = "La ou les platefomrmes doit ou doivent être mentionnée")
    List<Long> platformIds;
    
    @NotNull(message = "Le modèle économique doit être mentionné")
    Long businessModelId;
	
}