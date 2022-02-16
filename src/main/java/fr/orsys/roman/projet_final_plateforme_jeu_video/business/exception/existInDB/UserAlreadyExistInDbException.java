package fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB;

public class UserAlreadyExistInDbException extends AlreadyExistsInDB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistInDbException(String message) {
		super(message);
	}
}
