package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.it;


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

import fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.ConnexionController;

@SpringBootTest
class ConnexionControllerIT {

	private MockMvc mockMvc;

	@Autowired
	private ConnexionController connexionController;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(connexionController).build();
	}


	@Test
	void authenticateUser_shouldReturnNotFoundStatus() throws Exception {

		String jsonBody = "{\"pseudo\": \"Blablabla\", \"password\": \"blavvla\"}";

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login/signin");
		this.mockMvc.perform(requestBuilder
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound());

	}
	
		@Test
		void authenticateUser_shouldReturnUserResponseGamer() throws Exception {
			String jsonBody = "{\"pseudo\": \"coco\", \"password\": \"azerty\"}";

			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login/signin");
			this.mockMvc.perform(requestBuilder
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.pseudo").value("coco"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.admin").value(false));
	
		}
	
		@Test
		void authenticateUser_shouldReturnUserResponseModerator() throws Exception {
			String jsonBody = "{\"pseudo\": \"roman\", \"password\": \"azerty\"}";

			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login/signin");
			this.mockMvc.perform(requestBuilder
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.pseudo").value("roman"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.admin").value(true));
	
		}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
