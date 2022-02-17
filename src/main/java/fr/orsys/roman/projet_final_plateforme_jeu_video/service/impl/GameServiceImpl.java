package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Game;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Platform;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.CreateGameDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.GameDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.GameRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.BusinessModelService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ClassificationService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.EditorService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GameService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GenreService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.PlatformService;
@Service
public class GameServiceImpl implements GameService {
	
	private final GameRepository gameRepository;
	private final PlatformService platformService;
	private final BusinessModelService businessModelService;
	private final ClassificationService classificationService;
	private final GenreService genreService;
	private final EditorService editorService;
	
	/**
	 * @param gameRepository
	 */
	public GameServiceImpl(PlatformService platformService, GenreService genreService, GameRepository gameRepository, EditorService editorService, ClassificationService classificationService, BusinessModelService businessModelService) {
		this.gameRepository = gameRepository;
		this.platformService = platformService;
		this.businessModelService = businessModelService;
		this.classificationService = classificationService;
		this.genreService = genreService;
		this.editorService = editorService;

	}

	@Override
	public Game saveGame(Game game) {
		return gameRepository.save(game);
	}

	@Override
	public Game saveGame(GameDto gameDto) {
		Game game = new Game();
		game.setBusinessModel(businessModelService.getByName(gameDto.getBusinessModelName()));
		game.setClassification(classificationService.getClassificationByName(gameDto.getClassificationName()));
		game.setEditor(editorService.getEditorByName(gameDto.getEditorName()));
		game.setGenre(genreService.getGenreByName(gameDto.getGenreName()));
		game.setName(gameDto.getName());
		game.setDescription(gameDto.getDescription());
		game.setReleaseDate(gameDto.getReleaseDate());
		game = setPlatformsByName(game, gameDto.getPlatformNames());
		return gameRepository.save(game);
	}
	
	@Override
	public Game saveGame(CreateGameDto dto) {
		Game game = new Game();
		game.setName(dto.getName());
		game.setDescription(dto.getDescription());
		game.setReleaseDate(dto.getReleaseDate());
		game.setClassification(classificationService.getClassificationById(dto.getClassificationId()));
		game.setGenre(genreService.getGenreById(dto.getGenreId()));
		game.setEditor(editorService.getEditorById(dto.getEditorId()));
		game.setBusinessModel(businessModelService.getById(dto.getBusinessModelId()));
		game = setPlatformsById(game, dto.getPlatformIds());
		return gameRepository.save(game);
	}
	
	@Override
	public Game getById(Long id) {
		return gameRepository.findById(id).orElseThrow();
	}
	
	@Override
	public List<Game> getAll() {
		return gameRepository.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		gameRepository.deleteById(id);
		return !gameRepository.existsById(id);
	}

	private Game setPlatformsById(Game game, List<Long> list) {
		List<Platform> platforms = new ArrayList<>();
		for(Long e: list) {
			platforms.add(platformService.getPlatformById(e));
		}
		game.setPlatforms(platforms);
		return game;
	}
	
	private Game setPlatformsByName(Game game, List<String> list) {
		List<Platform> platforms = new ArrayList<>();
		for(String e: list) {
			platforms.add(platformService.getPlatformByName(e));
		}
		game.setPlatforms(platforms);
		return game;
	}

}
