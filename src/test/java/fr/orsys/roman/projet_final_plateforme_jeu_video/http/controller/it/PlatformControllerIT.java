package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.it;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.PlatformController;

@SpringBootTest
public class PlatformControllerIT {
	
	private MockMvc mockMvc;
	
	@Autowired
	PlatformController platformController;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(platformController).build();
	}
	
	@Test
	public void testGetAll() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/platform/all");
	
		mockMvc.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
			.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(13))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("PC"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("PlayStation"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
			.andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("PlayStation 2"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[3].id").value(4))
			.andExpect(MockMvcResultMatchers.jsonPath("$[3].name").value("PlayStation 3"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[4].id").value(5))
			.andExpect(MockMvcResultMatchers.jsonPath("$[4].name").value("PlayStation 4"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[5].id").value(6))
			.andExpect(MockMvcResultMatchers.jsonPath("$[5].name").value("PlayStation 5"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[6].id").value(7))
			.andExpect(MockMvcResultMatchers.jsonPath("$[6].name").value("Xbox"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[7].id").value(8))
			.andExpect(MockMvcResultMatchers.jsonPath("$[7].name").value("Xbox 360"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[8].id").value(9))
			.andExpect(MockMvcResultMatchers.jsonPath("$[8].name").value("Xbox One"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[9].id").value(10))
			.andExpect(MockMvcResultMatchers.jsonPath("$[9].name").value("Xbox Series S"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[10].id").value(11))
			.andExpect(MockMvcResultMatchers.jsonPath("$[10].name").value("Xbox Series X"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[11].id").value(12))
			.andExpect(MockMvcResultMatchers.jsonPath("$[11].name").value("Wii U"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[12].id").value(13))
			.andExpect(MockMvcResultMatchers.jsonPath("$[12].name").value("Nintendo Switch"));
	}
	
	@Test
	public void getById() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/platform/6");
	
		mockMvc.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.jsonPath("$").exists())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(6))
			.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("PlayStation 5"));
	}
}
