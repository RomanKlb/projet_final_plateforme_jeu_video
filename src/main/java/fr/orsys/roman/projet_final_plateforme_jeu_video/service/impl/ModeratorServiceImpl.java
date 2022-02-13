package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Moderator;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserModeratorDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.ModeratorAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.ModeratorRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ModeratorService;

@Service
public class ModeratorServiceImpl implements ModeratorService{

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final ModeratorRepository moderatorRepository;
	private final PasswordEncoder passwordEncoder;
	
	public ModeratorServiceImpl(ModeratorRepository moderatorRepository, PasswordEncoder passwordEncoder) {
		super();
		this.moderatorRepository = moderatorRepository;
		this.passwordEncoder = passwordEncoder;
	}

	
	@Override
	public Moderator saveUserModerator(@Valid UserModeratorDto moderatorDto) throws ModeratorAlreadyExistInDbException {
		log.info("Service saveUserModerator");
		if(moderatorRepository.existsByEmail(moderatorDto.getEmail())) {
			throw new ModeratorAlreadyExistInDbException();
		}
		return moderatorRepository.save(new Moderator(moderatorDto.getPseudo(), passwordEncoder.encode(moderatorDto.getPassword()), moderatorDto.getEmail(), moderatorDto.getPhoneNumber()));
	}


	@Override
	public Moderator findByIdModerator(Long id) {
		log.info("Service findByIdModerator");
		return moderatorRepository.findById(id).orElseThrow();
	}

}
