package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.LoginFormDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserResponse;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.UserNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ConnexionService;

@RestController
@RequestMapping(path="/login")
public class ConnexionController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final ConnexionService connexionService;
	

	public ConnexionController(ConnexionService connexionService) {
		super();
		this.connexionService = connexionService;
	}


	@PostMapping(path="/signin")
	public ResponseEntity<UserResponse> authenticateUser(@RequestBody LoginFormDto loginFormDto) throws UserNotFoundException {
		log.info("authenticateUser() est appelée");
		return connexionService.getAuthenticateUser(loginFormDto);
	}
}
