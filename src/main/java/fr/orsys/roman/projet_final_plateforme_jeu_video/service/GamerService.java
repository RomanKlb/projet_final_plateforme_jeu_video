package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import java.util.List;

import javax.validation.Valid;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.PasswordDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserGamerDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB.GamerAlreadyExistInDbException;

public interface GamerService {

	Gamer saveUserGamer(@Valid UserGamerDto gamerDto) throws GamerAlreadyExistInDbException;

	Gamer findByIdGamer(Long id);
	
	List<Gamer> findAll();

	Gamer updatePasswordGamer(Long id, PasswordDto passwordDto);

	boolean existsByEmail(String email);

	boolean existsbyPseudo(String pseudo);

	boolean existsById(Long gamerId);

}
