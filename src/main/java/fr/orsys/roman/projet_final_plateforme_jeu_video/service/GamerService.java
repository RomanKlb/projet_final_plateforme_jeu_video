package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import javax.validation.Valid;


import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.GamerAlreadyExistInDbException;

public interface GamerService {

	Gamer saveUserGamer(@Valid Gamer gamer) throws GamerAlreadyExistInDbException;

}
