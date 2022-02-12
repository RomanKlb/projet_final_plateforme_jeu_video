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
	public Gamer addGamer(@Valid @RequestBody Gamer gamer,
			BindingResult result) throws GamerAlreadyExistInDbException {
		System.out.println("controller addGamer");
		if(result.hasErrors()) {
			System.out.println("controller bindinresult addGamer");
			return null;
		} else {
			return gamerService.saveUserGamer(gamer);
		}
	}
	
	@PostMapping("/moderator/save")
	public Moderator addModerator(@Valid @RequestBody Moderator moderator,
			BindingResult result) throws ModeratorAlreadyExistInDbException {
		if(result.hasErrors()) {
			return null;
		} else {
			return moderatorService.saveUserModerator(moderator);
		}
	}
}