package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.it;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.GameDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.GameController;

@SpringBootTest
public class GameControllerIT {
	private MockMvc mockMvc;
	
	@Autowired
	private GameController gameController;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
	}
	/*
	@Test
	public void getAll_Should_Return_AList() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/game/all");
		this.mockMvc.perform(requestBuilder)
		/*.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(12))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("GTFO"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[6].id").value(7))
		.andExpect(MockMvcResultMatchers.jsonPath("$[6].name").value("Assassin's Creed Valhalla"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}*/
	
	/*@Test
	public void findOneGame_Should_Return_AGame() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/game/1");
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("GTFO"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}*/
	
	@Test
	public void findOneGame_Why_Unknown_Id_Provided_Should_Return_NOT_FOUND_Status() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/game/22");
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(MockMvcResultMatchers.jsonPath("$").value("Le jeu d'id 22 n'existe pas"));
	}
	
	@Test
	public void getGameCount_Should_Return_Numbers_OF_Games() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/game/count");
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.jsonPath("$").value("10"));
	}
	
	@Test
	public void addGame_Should_Save_A_Game() throws Exception {
		GameDto gameDto = new GameDto();
		gameDto.setBusinessModelName("Free to play");
		gameDto.setClassificationName("PEGI 3");
		gameDto.setDescription("Gros blabla");
		gameDto.setEditorName("Nintendo");
		gameDto.setGenreName("Action");
		gameDto.setName("Mon jeui");
		gameDto.setReleaseDate(LocalDate.of(2022, 2, 17));
		gameDto.setPlatformNames( Arrays.asList("Playstation", "Playstation 2"));
		
		
		
		String jsonBody = "{\"description\": \"Blablabla\", \"gameId\": 1, \"gamerId\": 1, \"rating\": 8}";
		//this.mockMvc.perform(MockMvcRequestBuilders.post("/reviews/save")
				/*.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());*/
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/game/save")
		 .content(asJsonString(gameDto))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(11))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Mon jeui"));
		/*this.mockMvc.perform(MockMvcRequestBuilders.post("/reviews/save")
				.content(jsonBody)
		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
       
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());*/
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
