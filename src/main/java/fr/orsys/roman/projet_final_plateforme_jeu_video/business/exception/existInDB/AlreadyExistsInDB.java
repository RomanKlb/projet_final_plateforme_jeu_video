package fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB;

public class AlreadyExistsInDB  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyExistsInDB(String message) {
		super(message);
	}
}
