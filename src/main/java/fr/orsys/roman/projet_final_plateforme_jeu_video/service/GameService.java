package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import java.util.List;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Game;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Platform;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.GameDto;

public interface GameService {

	Game saveGame(GameDto gameDto);
	//Game saveGame(Game game);
	/**
	 * Add a list of Platforms to a Game
	 * @param game Game
	 * @param platforms list of Platforms
	 * @return game
	 */
	Game addPlatforms(Game game, List<Platform> platforms);

}
