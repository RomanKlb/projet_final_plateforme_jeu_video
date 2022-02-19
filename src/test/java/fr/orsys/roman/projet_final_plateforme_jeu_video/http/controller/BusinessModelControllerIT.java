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
public class BusinessModelControllerIT {
	 
	private MockMvc mockMvc;
	@Autowired
	private BusinessModelController businessModelController;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(businessModelController).build();
	}
	
	@Test
	public void getBusinessModelsShouldreturnAList() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/businessModel/all");
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Free to play"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	@Test
	public void getBusinessModelsShouldReturnABusinessModel() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/businessModel/1");
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Free to play"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
