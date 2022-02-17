package fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception;

public class DateIsInTheFuturException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public DateIsInTheFuturException(String message) {
		super(message);
	}
	
}
