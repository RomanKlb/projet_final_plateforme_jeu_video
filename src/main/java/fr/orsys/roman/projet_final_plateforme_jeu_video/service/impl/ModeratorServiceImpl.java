package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Moderator;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.ModeratorAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.ModeratorRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ModeratorService;

@Service
public class ModeratorServiceImpl implements ModeratorService{

	private final ModeratorRepository moderatorRepository;
	private final PasswordEncoder passwordEncoder;
	
	public ModeratorServiceImpl(ModeratorRepository moderatorRepository, PasswordEncoder passwordEncoder) {
		super();
		this.moderatorRepository = moderatorRepository;
		this.passwordEncoder = passwordEncoder;
	}

	
	@Override
	public Moderator saveUserModerator(@Valid Moderator moderator) throws ModeratorAlreadyExistInDbException {
		if(moderatorRepository.existsByEmail(moderator.getEmail())) {
			throw new ModeratorAlreadyExistInDbException();
		}
		return moderatorRepository.save(new Moderator(moderator.getPseudo(), passwordEncoder.encode(moderator.getMotDePasse()), moderator.getEmail(), moderator.getNumeroDeTelephone()));
	}

}
