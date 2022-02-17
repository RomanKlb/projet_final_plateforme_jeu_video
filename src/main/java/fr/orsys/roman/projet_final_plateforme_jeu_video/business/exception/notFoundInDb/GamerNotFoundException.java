package fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb;

public class GamerNotFoundException extends NotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GamerNotFoundException(String message) {
		super(message);
	}
}
