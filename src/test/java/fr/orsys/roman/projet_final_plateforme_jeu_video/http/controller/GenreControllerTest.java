package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Genre;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GenreService;

@SpringBootTest
public class GenreControllerTest {
	
	private List<Genre> genres = new ArrayList<>();
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private GenreController genreController;
	
	@Mock
	private GenreService genreService;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
		genres.add(new Genre(1L, "Sandbox"));
		genres.add(new Genre(2L, "Action"));
		genres.add(new Genre(3L, "Adventure"));
		genres.add(new Genre(4L, "FPS"));
		genres.add(new Genre(5L, "MMORPG"));
		genres.add(new Genre(6L, "TPS"));
		genres.add(new Genre(7L, "RPG"));
	}
	
	@Test
	public void getGenresShouldreturnAList() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/genre/all");
		when(genreService.getGenres()).thenReturn(genres);
		
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(7))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Sandbox"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[6].id").value(7))
		.andExpect(MockMvcResultMatchers.jsonPath("$[6].name").value("RPG"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@Test
	public void getGenreShouldReturnAnGenre() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/genre/3");
		when(genreService.getGenreById(3L)).thenReturn(genres.get(2));
		
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Adventure"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
}
