package fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto;

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
@ToString(of = {"pseudo","password"} )
@EqualsAndHashCode(of = {"pseudo", "password"})
public class LoginFormDto {

	String pseudo;
	String password;
}
