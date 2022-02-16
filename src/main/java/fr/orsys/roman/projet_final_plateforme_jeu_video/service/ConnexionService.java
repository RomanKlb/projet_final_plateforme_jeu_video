package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import org.springframework.http.ResponseEntity;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.LoginFormDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserResponse;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.UserNotFoundException;

public interface ConnexionService {

	ResponseEntity<UserResponse> getAuthenticateUser(LoginFormDto loginFormDto) throws UserNotFoundException;
}
