package fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb;


public class UserNotFoundException extends NotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
