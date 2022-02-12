package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.GamerAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.GamerRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GamerService;

@Service
public class GamerServiceImpl implements GamerService{

	private final GamerRepository gamerRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	
	public GamerServiceImpl(GamerRepository gamerRepository, PasswordEncoder passwordEncoder) {
		super();
		this.gamerRepository = gamerRepository;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public Gamer saveUserGamer(@Valid Gamer gamer) throws GamerAlreadyExistInDbException {
		System.out.println("saveUserGamer");
		if(gamerRepository.existsByEmail(gamer.getEmail())) {
			throw new GamerAlreadyExistInDbException();
		}
		return gamerRepository.save(new Gamer(gamer.getPseudo(), passwordEncoder.encode(gamer.getMotDePasse()), gamer.getEmail(), gamer.getDateDeNaissance()));
	}
	
	

}
