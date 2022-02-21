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

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Platform;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.PlatformService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlatformServiceIT {

	@Autowired
	PlatformService platformService;
	
	@Test
	@Order(3)
	void testCreatePlatform() {
		Platform p = platformService.createPlatform("Xbox One Test");
		
		assertNotNull(p);
		assertEquals("Xbox One Test", p.getName());
		
		int size = platformService.getPlatforms().size();
		assertTrue(size > 13);
		assertEquals(platformService.getPlatformById((long) size).getName(), "Xbox One Test");
	}

	@Test
	@Order(1)
	void testGetPlatforms() {
		List<Platform> platforms = platformService.getPlatforms();
		
		
		assertTrue(platforms.size() > 0);
		assertEquals(platforms.get(0).getName(), "PC");
	}

	@Test
	@Order(2)
	void testGetPlatformById() {
		Platform platform = platformService.getPlatformById(1L);
		
		
		assertNotNull(platform);
		assertEquals(platform.getName(), "PC");
	}

	@Test
	@Order(4)
	void testDeletePlatform() {
		int size = platformService.getPlatforms().size();
		assertTrue(platformService.deletePlatform((long) size));
		Platform platform = platformService.getPlatformById((long) size);
		
		
		assertNull(platform);
	}

}
