package fr.orsys.roman.projet_final_plateforme_jeu_video.service.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Classification;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ClassificationService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClassificationServiceIT {
	
	@Autowired
	ClassificationService classificationService;

	@Test
	@Order(3)
	void classificationSvc_createClassification_shouldInsertClassification() {
		Classification c = classificationService.createClassification("PEGI 12");
		
		assertTrue(classificationService.getClassifications().size() > 2);
		assertEquals("PEGI 12", c.getName());
		assertEquals(6L, c.getId());
	}
	
	@Test
	@Order(1)
	void classificationSvc_getClassifications_shouldReturnTwoElements() {
		List<Classification> list = classificationService.getClassifications();
		for (Classification classification : list) {
			System.out.println(classification.getName());
		}
		
		assertEquals(5, list.size());
		assertTrue(list.get(0).getName() == "PEGI 3");
		assertEquals(1L, list.get(0).getId());
		
		assertTrue(list.get(1).getName() == "PEGI 7");
		assertEquals(2L, list.get(1).getId());
	}
	
	@Test
	@Order(2)
	void classificationSvc_getClassificationById_shouldReturnPegi7() {
		Classification c = classificationService.getClassificationById(2L);
		
		assertNotNull(c);
		assertEquals(2L, c.getId());
		assertEquals("PEGI 7", c.getName());
	}
	
	@Test
	@Order(4)
	void classificationSvc_deleteClassification_shouldReturnTrue() {
		Long id = 6L;
		assertTrue(classificationService.deleteClassification(id));
		assertNull(classificationService.getClassificationById(id));
	}

}
