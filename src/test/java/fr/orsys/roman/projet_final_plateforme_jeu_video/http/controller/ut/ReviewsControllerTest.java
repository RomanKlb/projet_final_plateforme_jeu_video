package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.ut;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Game;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Reviews;
import fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.ReviewsController;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.ReviewsRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ReviewsService;

@SpringBootTest
public class ReviewsControllerTest {

	private List<Reviews> reviews;
	private Gamer gamer;
	private Game game;

	private MockMvc mockMvc;

	@InjectMocks
	private ReviewsController reviewsController;

	@Mock
	private ReviewsService reviewsService;
	
	@Mock
	private ReviewsRepository reviewsRepository;


	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(reviewsController).build();

		gamer = new Gamer();
		gamer.setId(1L);
		gamer.setPseudo("coco");

		game = new Game();
		game.setId(1L);
		game.setName("GTFO");

		reviews = new ArrayList<>();

		Reviews r1 = new Reviews();
		r1.setDescription("Un très bon jeu !");
		r1.setId(1L);
		r1.setGamer(gamer);
		r1.setRating(12F);
		r1.setGame(game);
		reviews.add(r1);

		Reviews r2 = new Reviews();
		r2.setDescription("Un très bon jeu !");
		r2.setId(2L);
		r2.setGamer(gamer);
		r2.setRating(15F);
		r2.setGame(game);
		reviews.add(r2);
	}

	/*@Test
	public void saveOneReviewsShouldSaveACreateReviewsDto() throws Exception {
		CreateReviewsDto reviewsDto = new CreateReviewsDto();
		reviewsDto.setDescription("Blablabla");
		reviewsDto.setGameId(1L);
		reviewsDto.setGamerId(1L);
		reviewsDto.setRating(18f);
		
		Reviews review = new Reviews();
		review.setId(1L);
		review.setGame(game);
		review.setGamer(gamer);
		review.setRating(18f);
		
		String jsonBody = "{\"description\": \"Blablabla\", \"gameId\": 1, \"gamerId\": 1, \"rating\": 8}";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/reviews/save")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		
		//when(reviewsService.saveOneReviews(reviewsDto)).then;
		when

		this.mockMvc.perform(MockMvcRequestBuilders.post("/reviews/save")
				.content(asJsonString(reviewsDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/reviews/save")
				.content(jsonBody)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))

		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}*/

	@Test
	public void findAllReviewsAListofReviewsWithOKStatus() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/all");
		when(reviewsService.findAllReviews()).thenReturn(reviews);

		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].rating").value(12))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].game.id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].gamer.id").value(1))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	public void findOneReviewsShouldReturnAReviewsWithOKStatus() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/2");
		when(reviewsService.findOneReviews(2L)).thenReturn(reviews.get(1));

		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(15))
		.andExpect(MockMvcResultMatchers.jsonPath("$.game.id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.gamer.id").value(1))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	public void findOneReviewsWithUnknowIdProvidedShouldReturnAStatus404() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/4");
		when(reviewsService.findOneReviews(4L)).thenReturn(null);

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

