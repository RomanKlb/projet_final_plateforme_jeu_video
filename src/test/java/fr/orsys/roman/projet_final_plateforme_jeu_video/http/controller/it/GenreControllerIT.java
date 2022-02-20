package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.it;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.GenreController;

@SpringBootTest
public class GenreControllerIT {
	private MockMvc mockMvc;
	@Autowired
	private GenreController genreController;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
	}
	
	@Test
	public void getGenresShouldreturnAList() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/genre/all");
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
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Adventure"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
}
