package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Game;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.GameDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.BusinessModelRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.ClassificationRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.EditorRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.GameRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.GenreRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.PlatformRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GameService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.PlatformService;
@Service
public class GameServiceImpl implements GameService {
	
	private final GameRepository gameRepository;
	private final PlatformRepository platformRepository;
	private final BusinessModelRepository businessModelRepository;
	private final ClassificationRepository classificationRepository;
	private final GenreRepository genreRepository;
	private final EditorRepository editorRepository;
	private final PlatformService platfomrService;
	
	/**
	 * @param gameRepository
	 */
	public GameServiceImpl(GameRepository gameRepository, PlatformRepository platformRepository, GenreRepository genreRepository, EditorRepository editorRepository, ClassificationRepository classificationRepository, BusinessModelRepository businessModelRepository, PlatformService platfomrService) {
		this.gameRepository = gameRepository;
		this.platformRepository = platformRepository;
		this.businessModelRepository = businessModelRepository;
		this.classificationRepository = classificationRepository;
		this.genreRepository = genreRepository;
		this.editorRepository = editorRepository;
		this.platfomrService = platfomrService;
	}

	/*@Override
	public Game saveGame(Game game) {
		
		return this.saveGame(game);
	}*/

	@Override
	public Game saveGame(GameDto gameDto) {
		Game game = new Game();
		game.setBusinessModel(this.businessModelRepository.findByName(gameDto.getBusinessModelName()));
		game.setClassification(this.classificationRepository.findByName(gameDto.getClassificationName()));
		game.setEditor(this.editorRepository.findByName(gameDto.getEditorName()));
		game.setGenre(this.genreRepository.findByName(gameDto.getGenreName()));
		game.setReviews(new ArrayList<>());
		game.setPlatforms(this.platfomrService.getPlatormsByNames(gameDto.getPlatformNames()));
		game.setName(gameDto.getName());
		game.setDescription(gameDto.getDescription());
		game.setReleaseDate(gameDto.getReleaseDate());
		return this.gameRepository.save(game);
	}

	
}
