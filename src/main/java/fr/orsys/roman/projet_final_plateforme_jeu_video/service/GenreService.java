package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Genre;

public interface GenreService {
	
	long count();

    Genre addGenre(String name);
}
