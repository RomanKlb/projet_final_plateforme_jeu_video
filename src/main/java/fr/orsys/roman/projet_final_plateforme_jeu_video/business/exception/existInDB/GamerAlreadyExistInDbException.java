package fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB;


public class GamerAlreadyExistInDbException extends AlreadyExistsInDB {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GamerAlreadyExistInDbException(String message) {
		super(message);
	}

}
