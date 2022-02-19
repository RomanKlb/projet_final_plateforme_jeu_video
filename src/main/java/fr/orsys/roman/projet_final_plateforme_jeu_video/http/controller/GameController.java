package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Game;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.GameDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.DateIsInTheFuturException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.GameNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GameService;
/**
 * 
 * @author Jurufola
 * Controller for Game CRUD
 *
 */
@RestController
@RequestMapping(path="/game") 
@Validated
public class GameController {
	
	private static final String DOSSIER_IMAGES = "src/main/webapp/images/";
	
	private final GameService gameService;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * @param gameService
	 */
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@PostMapping("/save")
	public Game addGame(@Valid @RequestBody GameDto gameDto, BindingResult result) throws DateIsInTheFuturException {
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError objectError : errors) {
				log.error("Validation error ->" + objectError.getDefaultMessage());
			}
		}
		return this.gameService.saveGame(gameDto);
	}
	
	@PutMapping("/update/{id}")
	public Game updateGame(@Valid @RequestBody GameDto gameDto, @PathVariable Long id, BindingResult result) {
		if (gameDto.getReleaseDate() == null) {
			gameDto.setReleaseDate(LocalDate.now());
		}
		return this.gameService.updateGame(gameDto, id);
	}
	
	/*@ExceptionHandler(DateIsInTheFuturException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	public String traiterDateIsInTheFuturException() {
		return "La date de sortie ne peut être dans le futur";
	}*/
	
	@ExceptionHandler(GameNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)// 
	public String traiterGameNotFoundException(GameNotFoundException exception) {
		return exception.getMessage();
	}
	
	/**
	 * Handling javax validation constraints
	 * @param exception
	 * @return
	 */
	/*@ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    public String traiterDonneesInvalides(Exception exception) {
        return exception.getMessage();
    }*/
	
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    public List<String> traiterDonneesInvalidesAvecDetails(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }
	@GetMapping("/all")
	public List<Game> getAll() {
		return gameService.getAll();
	}
	
	@GetMapping("/{id}")
	public Game findOneGame(@PathVariable Long id) throws GameNotFoundException {
		try {
			Game game = gameService.getById(id);
			return game;
		}catch(NoSuchElementException e) {
			throw new GameNotFoundException("Le jeu d'id " + id + " n'existe pas");
		}
	}
	
	@GetMapping("/count")
	public Long getGameCount() {
		return gameService.getCount();
	}
	
	@DeleteMapping("/{id}/delete")
	public boolean deleteOneGame(@PathVariable Long id) throws IOException {
		Game game = gameService.getById(id);
		if(game.getImage() != null) {
			deleteFile(game.getImage());
		}
		return gameService.deleteById(id);
	}
	
	@PostMapping("/image/{id}")
	public Game patchGameImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
		Game game = gameService.getById(id);
		game.setImage(id.toString() + ".jpg");
		saveFile(game.getImage(), file);
		return gameService.saveGame(game);
	}
	
	private static void saveFile(String nom, MultipartFile multipartFile) throws IOException {
        Path chemin = Paths.get(DOSSIER_IMAGES);

        if (!Files.exists(chemin)) {
            Files.createDirectories(chemin);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path cheminFichier = chemin.resolve(nom);
            Files.copy(inputStream, cheminFichier, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Erreur d'écriture : " + nom, ioe);
        }
    }
	
	private static void deleteFile(String nom) throws IOException {
		Path chemin = Paths.get(DOSSIER_IMAGES);
		if (!Files.exists(chemin)) {
            Files.createDirectories(chemin);
        }
		String image = chemin.toString() + "\\" + nom;
		File file = new File(image);
		if(file != null) {
			file.delete();
		}
	}
	
	
}
