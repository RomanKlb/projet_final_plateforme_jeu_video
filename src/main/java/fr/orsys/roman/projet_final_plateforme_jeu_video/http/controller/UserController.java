package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Moderator;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.PasswordDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserGamerDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserModeratorDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB.GamerAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB.ModeratorAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.ModeratorNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GamerService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ModeratorService;

@RestController
@RequestMapping(path="/user")
@Validated
public class UserController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private final GamerService gamerService;
	private final ModeratorService moderatorService;

	public UserController(GamerService gamerService, ModeratorService moderatorService) {
		super();
		this.gamerService = gamerService;
		this.moderatorService = moderatorService;
	}

	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	@ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
	public List<String> traiterDonneesInvalidesAvecDetails(ConstraintViolationException exception) {
		return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
	}
	
	@ExceptionHandler(fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB.AlreadyExistsInDB.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public String traiterModeratorInDbException(Exception exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.NotFoundException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public String traiterModeratorNotFoundException(Exception exception) {
		return exception.getMessage();
	}

	/*
	 * Gamer
	 */
	@PostMapping("/gamer/save")
	public Gamer addGamer(@Valid @RequestBody UserGamerDto gamerDto,
			BindingResult result) throws GamerAlreadyExistInDbException {
		log.info("controller addGamer");
		if(gamerService.existsbyPseudo(gamerDto.getPseudo())) {
			throw new GamerAlreadyExistInDbException("gamer already pseudo exist " + gamerDto.getPseudo());
		}
		if(gamerService.existsByEmail(gamerDto.getEmail())) {
			throw new GamerAlreadyExistInDbException("gamer already email exist " + gamerDto.getEmail());
		}
		return gamerService.saveUserGamer(gamerDto);
	}

	@GetMapping("/gamer/{id}")
	public Gamer findByIdGamer(@PathVariable Long id) {
		log.info("controller findByIdGamer");
		return gamerService.findByIdGamer(id);
	}

	@PatchMapping("/gamer/{id}/patchPassword")
	public Gamer patchPasswordGamer(@PathVariable Long id, @Valid @RequestBody PasswordDto passwordDto) {
		log.info("controller patchPasswordGamer");
		return gamerService.updatePasswordGamer(id, passwordDto);
	}

	/*
	 * Moderator
	 */
	@PostMapping("/moderator/save")
	public Moderator addModerator(@Valid @RequestBody UserModeratorDto moderatorDto,
			BindingResult result) throws ModeratorAlreadyExistInDbException {
		log.info("controller addModerator");
		if(moderatorService.existsbyPseudo(moderatorDto.getPseudo())) {
			throw new ModeratorAlreadyExistInDbException("moderator already pseudo exist " + moderatorDto.getPseudo());
		}
		if(moderatorService.existsByEmail(moderatorDto.getEmail())) {
			throw new ModeratorAlreadyExistInDbException("moderator already email exist " + moderatorDto.getEmail());
		}
		return moderatorService.createUserModerator(moderatorDto);
	}

	@GetMapping("/moderator/{id}")
	public Moderator findByIdModerator(@PathVariable Long id) {
		log.info("controller findByIdModerator");
		return moderatorService.findByIdModerator(id);
	}

	@PatchMapping("/moderator/{id}/patchPassword")
	public Moderator patchPasswordModerator(@PathVariable Long id, @Valid @RequestBody PasswordDto passwordDto) throws ModeratorNotFoundException {
		log.info("controller patchPasswordModerator");
		return moderatorService.updatePasswordModerator(id, passwordDto);
	}
}
