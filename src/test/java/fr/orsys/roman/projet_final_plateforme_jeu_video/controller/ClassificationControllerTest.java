package fr.orsys.roman.projet_final_plateforme_jeu_video.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Classification;
import fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.ClassificationController;

@SpringBootTest
public class ClassificationControllerTest {
	
	List<Classification> classifications = new ArrayList<>();
	
	private MockMvc mockMvc;
	
	@Mock
	ClassificationController classificationController;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(classificationController).build();
		Classification c = new Classification(1L, "PEGI 3");
		classifications.add(c);
		c = new Classification(2L, "PEGI 7");
		classifications.add(c);
		c = new Classification(3L, "PEGI 12");
		classifications.add(c);
		c = new Classification(4L, "PEGI 16");
		classifications.add(c);
		c = new Classification(5L, "PEGI 18");
		classifications.add(c);
	}
	
	@Test
	public void getAll() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/classification/all");
		when(classificationController.getClassifications()).thenReturn(classifications);
	
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
		when(classificationController.getClassificationById(5L)).thenReturn(classifications.get(4));
	
		mockMvc.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5))
			.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("PEGI 18"));
	}
}