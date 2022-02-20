package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.ut;

import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.LoginFormDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserResponse;
import fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.ConnexionController;

@SpringBootTest
public class ConnexionControllerTest {

	private MockMvc mockMvc;

	@Mock
	ConnexionController connexionController;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(connexionController).build();
	}

	@Test
	public void shouldAuthenticateUserModerator() throws Exception {

		String jsonBody = "{\"pseudo\": \"roman\", \"password\": \"azerty\"}";

		LoginFormDto loginFormDto = new LoginFormDto("roman","azerty");

		UserResponse userResponseExpected = new UserResponse(1L, "roman", "roman@gmail.com", "azerty", true, "012345679", null);
		ResponseEntity<UserResponse> responseExpected = new ResponseEntity<UserResponse>(userResponseExpected, HttpStatus.ACCEPTED);

		when(connexionController.authenticateUser(loginFormDto)).thenReturn(responseExpected);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login/signin");
		this.mockMvc.perform(requestBuilder
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.jsonPath("$.pseudo").value("roman"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.admin").value(true));
	}

	@Test
	public void shouldAuthenticateUserGamer() throws Exception {
		String jsonBody = "{\"pseudo\": \"coco\", \"password\": \"azerty\"}";

		LoginFormDto loginFormDto = new LoginFormDto("coco","azerty");

		UserResponse userResponseExpected = new UserResponse(1L, "coco", "coco@gmail.com", "azerty", false, null, LocalDate.of(1990, 07, 28));
		ResponseEntity<UserResponse> responseExpected = new ResponseEntity<UserResponse>(userResponseExpected, HttpStatus.ACCEPTED);

		when(connexionController.authenticateUser(loginFormDto)).thenReturn(responseExpected);

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
		public void shouldNotAuthenticateUser() throws Exception {
			String jsonBody = "{\"pseudo\": \"blabla\", \"password\": \"sdfdsfdsf\"}";

			LoginFormDto loginFormDto = new LoginFormDto("blabla","sdfdsfdsf");

			ResponseEntity<UserResponse> responseExpected = new ResponseEntity<UserResponse>(HttpStatus.NOT_FOUND);

			when(connexionController.authenticateUser(loginFormDto)).thenReturn(responseExpected);

			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login/signin");
			this.mockMvc.perform(requestBuilder
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
		}
}
