package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

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

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlatformServiceIT {

	@Autowired
	PlatformService platformService;
	
	@Test
	@Order(3)
	void testCreatePlatform() {
		Platform p = platformService.createPlatform("Xbox One");
		
		assertNotNull(p);
		assertEquals("Xbox One", p.getName());
		
		assertTrue(platformService.getPlatforms().size() > 3);
		assertEquals(platformService.getPlatformById(4L).getName(), "Xbox One");
	}

	@Test
	@Order(1)
	void testGetPlatforms() {
		List<Platform> platforms = platformService.getPlatforms();
		
		
		assertTrue(platforms.size() > 0);
		assertEquals(platforms.get(0).getName(), "PlayStation");
	}

	@Test
	@Order(2)
	void testGetPlatformById() {
		Platform platform = platformService.getPlatformById(1L);
		
		
		assertNotNull(platform);
		assertEquals(platform.getName(), "PlayStation");
	}

	@Test
	@Order(4)
	void testDeletePlatform() {
		assertTrue(platformService.deletePlatform(3L));
		Platform platform = platformService.getPlatformById(3L);
		
		
		assertNull(platform);
	}

}
