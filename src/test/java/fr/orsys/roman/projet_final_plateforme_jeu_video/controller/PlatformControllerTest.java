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
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Platform;
import fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.PlatformController;

@SpringBootTest
public class PlatformControllerTest {
	
	List<Platform> platforms = new ArrayList<>();
	
	private MockMvc mockMvc;
	
	@Mock
	PlatformController platformController;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(platformController).build();
		Platform p = new Platform(1L, "PC");
		platforms.add(p);
		p = new Platform(2L, "Xbox One");
		platforms.add(p);
		p = new Platform(3L, "Xbox Series X");
		platforms.add(p);
		p = new Platform(4L, "PlayStation 4");
		platforms.add(p);
		p = new Platform(5L, "PlayStation 5");
		platforms.add(p);
	}
	
	@Test
	public void getAll() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/platform/all");
		when(platformController.getPlatforms()).thenReturn(platforms);
	
		mockMvc.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
			.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(5))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("PC"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Xbox One"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
			.andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("Xbox Series X"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[3].id").value(4))
			.andExpect(MockMvcResultMatchers.jsonPath("$[3].name").value("PlayStation 4"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[4].id").value(5))
			.andExpect(MockMvcResultMatchers.jsonPath("$[4].name").value("PlayStation 5"));
	}
	
	@Test
	public void getById() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/platform/5");
		when(platformController.getPlatformById(5L)).thenReturn(platforms.get(4));
	
		mockMvc.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5))
			.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("PlayStation 5"));
	}
}
