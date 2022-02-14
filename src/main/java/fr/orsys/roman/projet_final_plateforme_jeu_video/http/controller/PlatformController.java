package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Platform;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.PlatformService;

@RestController
@RequestMapping("/platform")
public class PlatformController {
	
	private final PlatformService platformService;
	
	public PlatformController(PlatformService platformService) {
		this.platformService = platformService;
	}
	
	@GetMapping("/all")
	public List<Platform> getPlatforms() {
		return platformService.getPlatforms();
	}
	
	@GetMapping("/{id}")
	public Platform getPlatformById(@PathVariable Long id) {
		return platformService.getPlatformById(id);
	}
}
