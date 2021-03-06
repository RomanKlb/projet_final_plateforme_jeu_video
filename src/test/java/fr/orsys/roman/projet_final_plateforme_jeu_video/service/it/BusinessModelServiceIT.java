package fr.orsys.roman.projet_final_plateforme_jeu_video.service.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.BusinessModel;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.BusinessModelService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BusinessModelServiceIT {

	@Autowired
	BusinessModelService businessModelService;
	
	@Test
	@Order(1)
	void getAll_shouldReturnTwoElements() {
		
		List<BusinessModel> models = businessModelService.getAll();
		
		assertEquals(2, models.size());
		
		assertEquals("Free to play", models.get(0).getName());
		assertEquals("Pay to play", models.get(1).getName());
	}
	
	@Test
	@Order(2)
	void getById_shouldReturnOneElement() {
		
		BusinessModel model = businessModelService.getById(1L);
		
		assertNotNull(model);
		
		assertEquals(1L, model.getId());
		assertEquals("Free to play", model.getName());
	}
	
	@Test
	@Order(3)
	void createModel_argIsEmpty_shouldThrow() {
		assertThrows(IllegalArgumentException.class, () -> businessModelService.createModel(""));
	}
	
	@Test
	@Order(3)
	void createModel_argIsValid_shouldCreateEntry() {
		BusinessModel model = businessModelService.createModel("Pay to win");
		
		assertNotNull(model);
		
		int size = businessModelService.getAll().size();
		assertEquals(size, model.getId());
		assertEquals("Pay to win", model.getName());
	}
	
	@Test
	@Order(4)
	void deleteModel_argIsValid_shouldDeleteEntry() {
		int size = businessModelService.getAll().size();
		assertTrue(businessModelService.deleteById((long) size));
		assertNull(businessModelService.getById((long) size));
	}
}
