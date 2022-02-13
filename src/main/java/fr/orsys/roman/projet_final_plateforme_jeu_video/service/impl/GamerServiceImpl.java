package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserGamerDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.GamerAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.GamerRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GamerService;

@Service
public class GamerServiceImpl implements GamerService{

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final GamerRepository gamerRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	
	public GamerServiceImpl(GamerRepository gamerRepository, PasswordEncoder passwordEncoder) {
		super();
		this.gamerRepository = gamerRepository;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public Gamer saveUserGamer(@Valid UserGamerDto gamerDto) throws GamerAlreadyExistInDbException {
		log.info("Service saveUserGamer");
		if(gamerRepository.existsByEmail(gamerDto.getEmail())) {
			throw new GamerAlreadyExistInDbException();
		}
		return gamerRepository.save(new Gamer(gamerDto.getPseudo(), passwordEncoder.encode(gamerDto.getPassword()),gamerDto.getEmail(), gamerDto.getBirthDate()));
	}



	@Override
	public Gamer findByIdGamer(Long id) {
		log.info("Service findByIdGamer");
		return gamerRepository.findById(id).orElseThrow();
	}
	
	

}
