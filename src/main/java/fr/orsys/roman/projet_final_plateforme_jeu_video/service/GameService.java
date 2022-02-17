package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import java.util.List;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Game;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.CreateGameDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.GameDto;

public interface GameService {

	Game saveGame(GameDto gameDto);
	Game saveGame(CreateGameDto gameDto);
	Game saveGame(Game game);
	
	List<Game> getAll();
	
	Game getById(Long id);
	
	boolean deleteById(Long id);
	boolean existsById(Long gameId);
}
