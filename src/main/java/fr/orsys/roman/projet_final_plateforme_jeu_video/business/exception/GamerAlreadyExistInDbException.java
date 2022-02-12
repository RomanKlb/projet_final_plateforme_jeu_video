package fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "gamer already exist")
public class GamerAlreadyExistInDbException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
