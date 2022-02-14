package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import java.util.List;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Platform;

public interface PlatformService {
	Platform createPlatform(Platform platform);
	
	List<Platform> getPlatforms();
	Platform getPlatformById(Long id);
	
	boolean deletePlatform(Long id);
}
