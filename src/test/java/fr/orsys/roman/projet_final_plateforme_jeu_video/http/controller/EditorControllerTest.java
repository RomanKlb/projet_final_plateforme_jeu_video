package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

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

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Editor;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.EditorService;

@SpringBootTest
public class EditorControllerTest {
	
	private List<Editor> editors = new ArrayList<>();
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private EditorController editorController;
	
	@Mock
	private EditorService editorService;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(editorController).build();
		editors.add(new Editor(1L, "Electronic Arts"));
		editors.add(new Editor(2L, "Activision"));
		editors.add(new Editor(3L, "Ubisoft"));
		editors.add(new Editor(4L, "Square Enix"));
		editors.add(new Editor(5L, "10 Chambers"));
		editors.add(new Editor(6L, "Battlestate Games"));
		editors.add(new Editor(7L, "Nintendo"));
	}
	
	@Test
	public void getEditorsShouldreturnAList() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/editor/all");
		when(editorController.getEditors()).thenReturn(editors);
		
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(7))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Electronic Arts"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[6].id").value(7))
		.andExpect(MockMvcResultMatchers.jsonPath("$[6].name").value("Nintendo"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@Test
	public void getEditorShouldReturnAnEditor() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/editor/5");
		when(editorService.getEditorById(5L)).thenReturn(editors.get(4));
		
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("10 Chambers"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@Test
	public void getEditorWhenUnknownIdIsProvided() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/editor/8");
		when(editorService.getEditorById(8L)).thenReturn(null);
		
		this.mockMvc.perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(MockMvcResultMatchers.jsonPath("$").value("L'editeur d'id 8 n'existe pas"));
	}

}
