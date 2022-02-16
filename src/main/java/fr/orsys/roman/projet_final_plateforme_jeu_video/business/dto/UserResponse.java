package fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
@ToString(of = {"id","email", "password", "pseudo", "isAdmin", "phoneNumber", "birthDate"} )
@EqualsAndHashCode(of = {"id","email", "password", "pseudo", "isAdmin", "phoneNumber", "birthDate"})
public class UserResponse {

	Long id;
	
	String pseudo;
	
	String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	String password;
	
	boolean isAdmin;
	
	String phoneNumber;
	
	LocalDate birthDate;
}
