package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Game;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.GameDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.GameRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GameService;
@Service
public class GameServiceImpl implements GameService {
	
	private final GameRepository gameRepository;
	
	/**
	 * @param gameRepository
	 */
	public GameServiceImpl(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	@Override
	public Game saveGame(Game game) {
		
		return this.saveGame(game);
	}

	/*@Override
	public Game saveGame(GameDto gameDto) {
		
		return this.saveGame(gameDto);
	}*/
	
	

}
