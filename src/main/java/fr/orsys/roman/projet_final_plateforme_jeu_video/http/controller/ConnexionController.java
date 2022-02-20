package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.LoginFormDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserResponse;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ConnexionService;

@RestController
@RequestMapping(path="/login")
@Validated
public class ConnexionController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final ConnexionService connexionService;
	

	public ConnexionController(ConnexionService connexionService) {
		super();
		this.connexionService = connexionService;
	}

	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	@ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
	public List<String> traiterDonneesInvalidesAvecDetails(ConstraintViolationException exception) {
		return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
	}

	@PostMapping(path="/signin")
	public ResponseEntity<UserResponse> authenticateUser(@Valid @RequestBody LoginFormDto loginFormDto)  {
		log.info("authenticateUser() est appelée");
		return connexionService.getAuthenticateUser(loginFormDto);
	}
}
