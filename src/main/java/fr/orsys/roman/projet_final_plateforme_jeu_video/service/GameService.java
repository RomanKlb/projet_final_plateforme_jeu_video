package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Game;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.GameDto;

public interface GameService {

	//Game saveGame(GameDto gameDto);
	Game saveGame(Game game);

}
