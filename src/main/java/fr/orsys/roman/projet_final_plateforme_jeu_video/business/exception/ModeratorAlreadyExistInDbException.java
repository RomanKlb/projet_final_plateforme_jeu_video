package fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "moderator already exist")
public class ModeratorAlreadyExistInDbException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
