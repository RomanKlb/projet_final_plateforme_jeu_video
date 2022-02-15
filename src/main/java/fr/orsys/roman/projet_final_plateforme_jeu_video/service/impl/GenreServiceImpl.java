package fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Genre;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.GenreRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;


    /**
     * @param genreRepository
     */
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


    @Override
    public long count() {
        return this.genreRepository.count();
    }


    @Override
    public Genre addGenre(String name) {
        return this.genreRepository.save(new Genre(name));
    }


	@Override
	public List<Genre> getGenres() {
		return genreRepository.findAll();
	}


	@Override
	public Genre getGenreById(Long id) {
		return genreRepository.findById(id).orElseThrow();
	}
	
	@Override
	public Genre getGenreByName(String name) {
		return genreRepository.findByName(name);
	}

}