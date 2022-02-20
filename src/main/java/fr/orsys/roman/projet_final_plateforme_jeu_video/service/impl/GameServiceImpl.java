package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Game;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Platform;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.CreateGameDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.GameDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB.GameAlreadyExistInDbException;
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
	public Game saveGame(GameDto gameDto) throws GameAlreadyExistInDbException {
		
		if(this.gameRepository.findByNameAndEditorNameAndReleaseDate(gameDto.getName(), gameDto.getEditorName(), gameDto.getReleaseDate()) != null) {
			throw new GameAlreadyExistInDbException("Le jeu " + gameDto.getName() + " de l'éditeur " + gameDto.getEditorName() + " sorti le " + gameDto.getReleaseDate() + " existe déjà");
		}
		Game game = new Game();
		return gameRepository.save(this.constructGameByGameDto(game, gameDto));
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
	public Long getCount() {
		return gameRepository.count();
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

	@Override
	public Game updateGame(GameDto gameDto, Long id) {
		Game game = this.gameRepository.findById(id).orElseThrow();
		
		return gameRepository.save(constructGameByGameDto(game, gameDto));
	}

	@Override
	public Game constructGameByGameDto(Game game, GameDto gameDto) {
		game.setBusinessModel(businessModelService.getByName(gameDto.getBusinessModelName()));
		game.setClassification(classificationService.getClassificationByName(gameDto.getClassificationName()));
		game.setEditor(editorService.getEditorByName(gameDto.getEditorName()));
		game.setGenre(genreService.getGenreByName(gameDto.getGenreName()));
		game.setName(gameDto.getName());
		game.setDescription(gameDto.getDescription());
		game.setReleaseDate(gameDto.getReleaseDate());
		game = setPlatformsByName(game, gameDto.getPlatformNames());
		return game;
	}

	@Override
	public boolean existsById(Long gameId) {
		return gameRepository.existsById(gameId);
	}

	@Override
	public Game findByNameAndEditorNameAndReleaseDate(String name, String editorName, LocalDate releaseDate) {
		return this.gameRepository.findByNameAndEditorNameAndReleaseDate(name, editorName, releaseDate);
	}

}
