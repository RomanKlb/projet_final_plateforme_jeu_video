package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Moderator;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.LoginFormDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserResponse;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.UserNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.GamerRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.ModeratorRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ConnexionService;

@Service
public class ConnexionServiceImpl implements ConnexionService {

	Logger log = LoggerFactory.getLogger(this.getClass());


	private final ModeratorRepository moderatorRepository;
	private final GamerRepository gamerRepository;
	private final PasswordEncoder passwordEncoder;

	public ConnexionServiceImpl(ModeratorRepository moderatorRepository, GamerRepository gamerRepository,
			PasswordEncoder passwordEncoder) {
		super();
		this.moderatorRepository = moderatorRepository;
		this.gamerRepository = gamerRepository;
		this.passwordEncoder = passwordEncoder;
	}


	public ResponseEntity<UserResponse> getAuthenticateUser(LoginFormDto loginFormDto) throws UserNotFoundException {
		log.info("Service getAuthenticateUser");

		UserResponse userResponse = new UserResponse();

		if(moderatorRepository.existsByPseudo(loginFormDto.getPseudo())) {
			Optional<Moderator> moderator = moderatorRepository.findByPseudo(loginFormDto.getPseudo());
			if(moderator.isPresent()) {

				if(!passwordEncoder.matches(loginFormDto.getPassword(), moderator.get().getPassword())) 
					return ResponseEntity.badRequest().build();

				userResponse.setId(moderator.get().getId());
				userResponse.setPseudo(moderator.get().getPseudo());
				userResponse.setEmail(moderator.get().getEmail());
				userResponse.setPassword(moderator.get().getPassword());
				userResponse.setAdmin(true);
				userResponse.setPhoneNumber(moderator.get().getPhoneNumber());
			}
		} else if(gamerRepository.existsByPseudo(loginFormDto.getPseudo())) {
			Optional<Gamer> gamer = gamerRepository.findByPseudo(loginFormDto.getPseudo());
			if(gamer.isPresent()) {
				if(!passwordEncoder.matches(loginFormDto.getPassword(), gamer.get().getPassword())) 
					return ResponseEntity.badRequest().build();

				userResponse.setId(gamer.get().getId());
				userResponse.setPseudo(gamer.get().getPseudo());
				userResponse.setEmail(gamer.get().getEmail());
				userResponse.setPassword(gamer.get().getPassword());
				userResponse.setAdmin(false);
				userResponse.setBirthDate(gamer.get().getBirthDate());
			}
		} else {
			throw new UserNotFoundException();
		}

		return ResponseEntity.ok(userResponse);	

	}
}
