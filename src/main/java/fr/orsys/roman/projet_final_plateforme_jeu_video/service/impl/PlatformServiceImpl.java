package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Platform;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.PlatformRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.PlatformService;

@Service
public class PlatformServiceImpl implements PlatformService {
	
	private final PlatformRepository platformRepository;
	
	public PlatformServiceImpl(PlatformRepository platformRepository) {
		this.platformRepository = platformRepository;
	}

	@Override
	public Platform createPlatform(String name) {
		return platformRepository.save(new Platform(name));
	}

	@Override
	public List<Platform> getPlatforms() {
		return platformRepository.findAll();
	}

	@Override
	public Platform getPlatformById(Long id) {
		return platformRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deletePlatform(Long id) {
		platformRepository.deleteById(id);
		return !platformRepository.existsById(id);
	}

}
