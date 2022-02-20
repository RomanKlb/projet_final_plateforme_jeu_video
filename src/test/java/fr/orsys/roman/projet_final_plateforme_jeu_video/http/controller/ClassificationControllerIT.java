package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class ClassificationControllerIT {
	
	@Autowired
	ClassificationController classificationController;
	
	private MockMvc mockMvc;
	@BeforeEach
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(classificationController).build();
	}
	
	@Test
	public void getAll() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/classification/all");
		
		mockMvc.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
			.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(5))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("PEGI 3"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("PEGI 7"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
			.andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("PEGI 12"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[3].id").value(4))
			.andExpect(MockMvcResultMatchers.jsonPath("$[3].name").value("PEGI 16"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[4].id").value(5))
			.andExpect(MockMvcResultMatchers.jsonPath("$[4].name").value("PEGI 18"));
	}
	
	@Test
	public void getById() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/classification/5");
	
		mockMvc.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.jsonPath("$").exists())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5))
			.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("PEGI 18"));
	}
}
