package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Moderator;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.PasswordDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserGamerDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserModeratorDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.GamerAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.ModeratorAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GamerService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ModeratorService;

@RestController
@RequestMapping(path="/user")
public class UserController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final GamerService gamerService;
	private final ModeratorService moderatorService;

	public UserController(GamerService gamerService, ModeratorService moderatorService) {
		super();
		this.gamerService = gamerService;
		this.moderatorService = moderatorService;
	}
	
	/*
	 * Gamer
	 */
	@PostMapping("/gamer/save")
	public Gamer addGamer(@Valid @RequestBody UserGamerDto gamerDto,
			BindingResult result) throws GamerAlreadyExistInDbException {
		log.info("controller addGamer");
		if(result.hasErrors()) {
			log.info("controller bindinresult addGamer");
			return null;
		} else {
			return gamerService.saveUserGamer(gamerDto);
		}
	}
	
	@GetMapping("/gamer/{id}")
	public Gamer findByIdGamer(@PathVariable Long id) {
		log.info("controller findByIdGamer");
		return gamerService.findByIdGamer(id);
	}
	
	@PatchMapping("/gamer/{id}/patchPassword")
	public Gamer patchPasswordGamer(@PathVariable Long id, @RequestBody PasswordDto passwordDto) {
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
		if(result.hasErrors()) {
			log.info("controller bindinresult addModerator");
			return null;
		} else {
			return moderatorService.saveUserModerator(moderatorDto);
		}
	}
	
	@GetMapping("/moderator/{id}")
	public Moderator findByIdModerator(@PathVariable Long id) {
		log.info("controller findByIdModerator");
		return moderatorService.findByIdModerator(id);
	}
	
	@PatchMapping("/moderator/{id}/patchPassword")
	public Moderator patchPasswordModerator(@PathVariable Long id, @RequestBody PasswordDto passwordDto) {
		log.info("controller patchPasswordModerator");
		return moderatorService.updatePasswordModerator(id, passwordDto);
	}
}
