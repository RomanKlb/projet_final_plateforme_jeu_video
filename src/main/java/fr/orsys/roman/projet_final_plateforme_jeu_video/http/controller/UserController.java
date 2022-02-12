package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Moderator;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserGamerDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserModeratorDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.GamerAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.ModeratorAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GamerService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ModeratorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="/user")
public class UserController {

	
	private final GamerService gamerService;
	private final ModeratorService moderatorService;

	public UserController(GamerService gamerService, ModeratorService moderatorService) {
		super();
		this.gamerService = gamerService;
		this.moderatorService = moderatorService;
	}
	
	@PostMapping("/gamer/save")
	public Gamer addGamer(@Valid @RequestBody UserGamerDto gamerDto,
			BindingResult result) throws GamerAlreadyExistInDbException {
		System.out.println("controller addGamer");
		System.out.println(gamerDto);
		if(result.hasErrors()) {
			System.out.println("controller bindinresult addGamer");
			return null;
		} else {
			return gamerService.saveUserGamer(gamerDto);
		}
	}
	
	@PostMapping("/moderator/save")
	public Moderator addModerator(@Valid @RequestBody UserModeratorDto moderatorDto,
			BindingResult result) throws ModeratorAlreadyExistInDbException {
		if(result.hasErrors()) {
			return null;
		} else {
			return moderatorService.saveUserModerator(moderatorDto);
		}
	}
}
