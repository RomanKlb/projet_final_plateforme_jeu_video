package fr.orsys.roman.projet_final_plateforme_jeu_video.service.ut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.BusinessModel;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl.BusinessModelServiceImpl;

@SpringBootTest
class BusinessModelServiceTest {

	/*@InjectMocks
	BusinessModelServiceImpl bmService;

	@Test
	void create_whenArgIsEmpty_shouldThrowIllegalArgumentException() {
		when(bmService.createModel("")).thenThrow(IllegalArgumentException.class);

		assertThrows(IllegalArgumentException.class, () -> bmService.createModel(""));
	}

	@Test
	void create_whenArgIsValid_shouldCreateDBEntry() {
		BusinessModel bm = bmService.createModel("pay to win");

		assertEquals("pay to win", bm.getName());
	}

	@Test
	void getAll_shouldReturnModels() {
		List<BusinessModel> models = bmService.getAll();

		assertEquals(2, models.size());
	}

	@Test
	void getById_idNegative_shouldThrowIllegalArgumentException() {
		when(bmService.getById(-3L)).thenThrow(IllegalArgumentException.class);

		assertThrows(IllegalArgumentException.class, () -> bmService.getById(-3L));
	}
	
	@Test
	void getById_idUnknown_shouldReturnException() {
		when(bmService.getById(8L)).thenThrow(IllegalArgumentException.class);

		assertThrows(IllegalArgumentException.class, () -> bmService.getById(8L));
	}
	
	@Test
	void getById_idValid_shouldReturnOneValue() {
		when(bmService.getById(8L)).thenReturn(new BusinessModel("free to play"));

		assertEquals("free to play", bmService.getById(1L).getName());
	}*/

}
