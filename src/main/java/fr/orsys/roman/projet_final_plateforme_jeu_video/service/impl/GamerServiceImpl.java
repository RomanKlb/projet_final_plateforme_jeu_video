package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.PasswordDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserGamerDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB.GamerAlreadyExistInDbException;
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
		return gamerRepository.save(new Gamer(gamerDto.getPseudo(), passwordEncoder.encode(gamerDto.getPassword()),gamerDto.getEmail(), gamerDto.getBirthDate()));
	}

	@Override
	public Gamer findByIdGamer(Long id) {
		log.info("Service findByIdGamer");
		return gamerRepository.findById(id).orElseThrow();
	}
	
	

	@Override
	public Gamer updatePasswordGamer(Long id, PasswordDto passwordDto) {
		Optional<Gamer> gamer = gamerRepository.findById(id);
		gamer.get().setPassword(passwordEncoder.encode(passwordDto.getPassword()));
		return gamerRepository.save(gamer.get());
	}

	@Override
	public boolean existsByEmail(String email) {
		return gamerRepository.existsByEmail(email);
	}

	@Override
	public boolean existsbyPseudo(String pseudo) {
		return gamerRepository.existsByPseudo(pseudo);
	}

	@Override
	public boolean existsById(Long gamerId) {
		return gamerRepository.existsById(gamerId);
	}

	@Override
	public List<Gamer> findAll() {
		return gamerRepository.findAll();
	}
	
	

}
