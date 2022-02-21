package fr.orsys.roman.projet_final_plateforme_jeu_video.service.ut;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.GamerRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GamerService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GamerServiceTest {

	@InjectMocks
	GamerService gamerService;

	@Mock
	GamerRepository gamerRepository;

	
	/*@Test
	@Order(1)
	void shouldSaveGamer() {
		
		
	}*/

}
