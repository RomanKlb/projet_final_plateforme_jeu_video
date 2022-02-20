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

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.BusinessModel;
import fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller.BusinessModelController;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.BusinessModelService;

@SpringBootTest
public class BusinessModelControllerTest {
	
	private List<BusinessModel> models = new ArrayList<>();
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private BusinessModelController businessModelController;

	@Mock
	private BusinessModelService businessModelService;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(businessModelController).build();
		models.add(new BusinessModel(1L, "Free to play"));
		models.add(new BusinessModel(2L, "Pay to play"));
	}
	
	@Test
	public void getBusinessModelsShouldreturnAList() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/businessModel/all");
		when(businessModelService.getAll()).thenReturn(models);
		
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
		when(businessModelService.getById(1L)).thenReturn(models.get(0));
		
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Free to play"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
