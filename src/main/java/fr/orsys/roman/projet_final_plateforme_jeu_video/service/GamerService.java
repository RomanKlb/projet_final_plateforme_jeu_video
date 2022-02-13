package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import javax.validation.Valid;


import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.PasswordDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserGamerDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.GamerAlreadyExistInDbException;

public interface GamerService {

	Gamer saveUserGamer(@Valid UserGamerDto gamerDto) throws GamerAlreadyExistInDbException;

	Gamer findByIdGamer(Long id);

	Gamer updatePasswordGamer(Long id, PasswordDto passwordDto);

}
