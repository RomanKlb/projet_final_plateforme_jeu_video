package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Platform;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.PlatformRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.PlatformService;

@Service
public class PlatformServiceImpl implements PlatformService {

	private  final PlatformRepository platformRepository;
	
	
	/**
	 * @param platformRepository
	 */
	public PlatformServiceImpl(PlatformRepository platformRepository) {
		this.platformRepository = platformRepository;
	}

	@Override
	public Platform createPlatform(Platform platform) {
		return null;
	}

	@Override
	public List<Platform> getPlatforms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Platform getPlatformById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePlatform(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * A list of PlatForms from a corresponding list of plateForm names
	 * @param platformNames
	 * @return  List of platForms
	 */
	
	public List<Platform> getPlatormsByNames(List<String> platformNames) {
		
		List<Platform> platforms = new ArrayList<>();
		for (String platform : platformNames) {
			platforms.add(this.platformRepository.findByName(platform));
		}
		return platforms;
		
	}
}
