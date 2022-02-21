package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.it;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc // Prepare Mock Object
public class ReviewsControllerIT {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void findAllReviewsAListofReviewsWithOKStatus() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/all");
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(12))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].rating").value(12))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].game.id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].gamer.id").value(2))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@Test
	public void findOneReviewsShouldReturnAReviewsWithOKStatus() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/2");
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(12))
		.andExpect(MockMvcResultMatchers.jsonPath("$.game.id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.gamer.id").value(2))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@Test
	public void findOneReviewsWithUnknowIdProvidedShouldReturnAStatus404() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/13");
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(MockMvcResultMatchers.jsonPath("$").value("L'avis d'id 13 n'existe pas"));
	}

}
