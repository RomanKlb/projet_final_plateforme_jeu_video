package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.EditorRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl.EditorServiceImpl;

@SpringBootTest
class EditorServiceTest {
	
	@InjectMocks
	EditorServiceImpl editorService;
	
	@Mock
	EditorRepository editorRepository;

	@Test
	void create_paramIsEmpty_shouldThrow() {
		
		when(editorService.createEditor("")).thenThrow(IllegalArgumentException.class);
		
		assertThrows(IllegalArgumentException.class, ()  -> editorService.createEditor(""));
	}
	
	@Test
	void create_paramIsValid_shouldInsert() {
		fail("unimplemented");
	}

}
