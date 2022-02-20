package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.it;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.CreateReviewsDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.ReviewsController;

@SpringBootTest
public class ReviewsControllerIT {
	private MockMvc mockMvc;
	@Autowired
	private ReviewsController reviewsController;
	
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(reviewsController).build();
	}
	
	/*@Test
	public void saveOneReviewsShouldSaveACreateReviewsDto() throws Exception {
		CreateReviewsDto reviewsDto = new CreateReviewsDto();
		reviewsDto.setDescription("Blablabla");
		reviewsDto.setGameId(1L);
		reviewsDto.setGamerId(1L);
		reviewsDto.setRating(18f);
		String jsonBody = "{\"description\": \"Blablabla\", \"gameId\": 1, \"gamerId\": 1, \"rating\": 8}";
		/*this.mockMvc.perform(MockMvcRequestBuilders.post("/reviews/save")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());*/
		
		/*this.mockMvc.perform(MockMvcRequestBuilders.post("/reviews/save")
		 .content(asJsonString(reviewsDto))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		//.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("blabla"));
		this.mockMvc.perform(MockMvcRequestBuilders.post("/reviews/save")
				.content(jsonBody)
		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
       
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}*/
	
	@Test
	public void findAllReviewsAListofReviewsWithOKStatus() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/all");
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].rating").value(8))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].game.id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].gamer.id").value(1))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@Test
	public void findOneReviewsShouldReturnAReviewsWithOKStatus() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/2");
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(8))
		.andExpect(MockMvcResultMatchers.jsonPath("$.game.id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.gamer.id").value(1))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@Test
	public void findOneReviewsWithUnknowIdProvidedShouldReturnAStatus404() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/4");
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(MockMvcResultMatchers.jsonPath("$").value("L'avis d'id 4 n'existe pas"));
	}
	
	@Test
	public void findOneReviewsWithUnknowIdProvideShouldReturnAStatus404() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/4");
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(MockMvcResultMatchers.jsonPath("$").value("L'avis d'id 4 n'existe pas"));
	}
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
