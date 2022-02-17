package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Moderator;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.PasswordDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserModeratorDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB.ModeratorAlreadyExistInDbException;
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
	public Moderator createUserModerator(@Valid UserModeratorDto moderatorDto) throws ModeratorAlreadyExistInDbException {
		log.info("Service saveUserModerator");
		return moderatorRepository.save(new Moderator(moderatorDto.getPseudo(), passwordEncoder.encode(moderatorDto.getPassword()), moderatorDto.getEmail(), moderatorDto.getPhoneNumber()));
	}


	@Override
	public Moderator findByIdModerator(Long id) {
		log.info("Service findByIdModerator");
		return moderatorRepository.findById(id).orElseThrow();
	}


	@Override
	public Moderator updatePasswordModerator(Long id, PasswordDto passwordDto) {
		Optional<Moderator> moderator = moderatorRepository.findById(id);
		moderator.get().setPassword(passwordEncoder.encode(passwordDto.getPassword()));
		return moderatorRepository.save(moderator.get());
	}


	@Override
	public List<Moderator> findAll() {
		return moderatorRepository.findAll();
	}


	@Override
	public boolean moderatorExist(Long id) {
		return moderatorRepository.existsById(id);
	}


	@Override
	public boolean existsbyPseudo(String pseudo) {
		return moderatorRepository.existsByPseudo(pseudo);
	}


	@Override
	public boolean existsByEmail(String email) {
		return moderatorRepository.existsByEmail(email);
	}


	@Override
	public boolean existsById(Long id) {
		return moderatorRepository.existsById(id);
	}

}
