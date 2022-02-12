package fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto;

import java.time.LocalDate;

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
@ToString(of = {"pseudo","email","password", "birthDate"} )
@EqualsAndHashCode(of = {"pseudo","email", "password", "birthDate"})
public class UserGamerDto {

	String pseudo;
	String password;
	String email;
	LocalDate birthDate;
}
