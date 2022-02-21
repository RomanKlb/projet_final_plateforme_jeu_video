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
@AutoConfigureMockMvc
public class GameControllerIT {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getAll_Should_Return_AList() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/game/all");
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(10))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("GTFO"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[6].id").value(7))
		.andExpect(MockMvcResultMatchers.jsonPath("$[6].name").value("Assassin's Creed Valhalla"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@Test
	public void findOneGame_Should_Return_AGame() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/game/1");
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("GTFO"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
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
}
