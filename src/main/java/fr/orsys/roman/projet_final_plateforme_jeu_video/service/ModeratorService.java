package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import javax.validation.Valid;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Moderator;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.ModeratorAlreadyExistInDbException;

public interface ModeratorService {

	Moderator saveUserModerator(@Valid Moderator moderator) throws ModeratorAlreadyExistInDbException;

}
