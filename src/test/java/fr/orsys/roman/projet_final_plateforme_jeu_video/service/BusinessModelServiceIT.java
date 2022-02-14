package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

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
		
		assertEquals("free to play", models.get(0).getName());
		assertEquals("pay to play", models.get(1).getName());
	}
	
	@Test
	@Order(2)
	void getById_shouldReturnOneElement() {
		
		BusinessModel model = businessModelService.getById(1L);
		
		assertNotNull(model);
		
		assertEquals(1L, model.getId());
		assertEquals("free to play", model.getName());
	}
	
	@Test
	@Order(3)
	void createModel_argIsEmpty_shouldThrow() {
		assertThrows(IllegalArgumentException.class, () -> businessModelService.createModel(""));
	}
	
	@Test
	@Order(3)
	void createModel_argIsValid_shouldCreateEntry() {
		BusinessModel model = businessModelService.createModel("pay to win");
		
		assertNotNull(model);
		
		assertEquals(3L, model.getId());
		assertEquals("pay to win", model.getName());
	}
	
	@Test
	@Order(4)
	void deleteModel_argIsValid_shouldDeleteEntry() {
		assertTrue(businessModelService.deleteById(1L));
		assertNull(businessModelService.getById(1L));
	}
}
