package fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb;

public class NotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
		super(message);
	}
}