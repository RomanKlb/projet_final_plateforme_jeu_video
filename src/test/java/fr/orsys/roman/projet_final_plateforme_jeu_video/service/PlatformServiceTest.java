package fr.orsys.roman.projet_final_plateforme_jeu_video.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Platform;

@Sql("/PlatformService.sql")
@SpringBootTest
class PlatformServiceTest {

	@Autowired
	PlatformService platformService;
	
	@Test
	void testCreatePlatform() {
		Platform xbox = new Platform("Xbox One");
		platformService.createPlatform(xbox);
		
		assertTrue(platformService.getPlatforms().size() > 1);
		assertEquals(platformService.getPlatformById(1L).getName(), "Xbox One");
	}

	@Test
	void testGetPlatforms() {
		List<Platform> platforms = platformService.getPlatforms();
		
		
		assertTrue(platforms.size() > 0);
		assertEquals(platforms.get(0).getName(), "PlayStation");
	}

	@Test
	void testGetPlatformById() {
		Platform platform = platformService.getPlatformById(1L);
		
		
		assertNotNull(platform);
		assertEquals(platform.getName(), "PlayStation");
	}

	@Test
	void testDeletePlatform() {
		platformService.deletePlatform(1L);
		Platform platform = platformService.getPlatformById(1L);
		
		
		assertNull(platform);
		assertNotEquals(platform.getName(), "PlayStation");
	}

}
