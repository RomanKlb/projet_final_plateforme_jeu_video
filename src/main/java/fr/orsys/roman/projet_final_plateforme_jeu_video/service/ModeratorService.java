package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import java.util.List;

import javax.validation.Valid;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Moderator;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.PasswordDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserModeratorDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB.ModeratorAlreadyExistInDbException;

public interface ModeratorService {

	Moderator createUserModerator(@Valid UserModeratorDto moderatorDto) throws ModeratorAlreadyExistInDbException;

	Moderator findByIdModerator(Long id);
	
	List<Moderator> findAll();

	Moderator updatePasswordModerator(Long id, PasswordDto passwordDto);

	boolean moderatorExist(Long id);

	boolean existsbyPseudo(String pseudo);

	boolean existsByEmail(String email);

	boolean existsById(Long id);
}
