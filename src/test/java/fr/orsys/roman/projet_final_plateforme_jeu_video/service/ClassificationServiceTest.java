package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Classification;

@Sql("ClassificationService.sql")
class ClassificationServiceTest {
	
	@Autowired
	ClassificationService classificationService;

	@Test
	void testClassificationService() {
		Classification classification = new Classification("PEGI 12");
		classificationService.createClassification(classification);
		
		assertTrue(classificationService.getClassifications().size() > 2);
		assertEquals(classificationService.getClassificationById(3L), "PEGI 12");
	}

}
