package fr.orsys.roman.projet_final_plateforme_jeu_video.service.it;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.LoginFormDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserResponse;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ConnexionService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConnexionServiceIT {
	
	@Autowired
	ConnexionService connexionService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Test
	@Order(1)
	void shouldGetAuthenticateGamer() {
		
		LoginFormDto loginFormDto = new LoginFormDto("coco", "azerty");
		
		boolean expectedGamer = false;
		
		ResponseEntity<UserResponse> responseExpected = connexionService.getAuthenticateUser(loginFormDto);
		
		assertEquals(responseExpected.getBody().getPseudo(), loginFormDto.getPseudo());
		assertTrue((passwordEncoder().matches(loginFormDto.getPassword(), responseExpected.getBody().getPassword())));
		assertEquals(responseExpected.getBody().isAdmin(), expectedGamer);
	}
	
	@Test
	@Order(2)
	void shouldGetAuthenticateModerator() {
		
		LoginFormDto loginFormDto = new LoginFormDto("roman", "azerty");
		
		boolean expectedModerator = true;
		
		ResponseEntity<UserResponse> responseExpected = connexionService.getAuthenticateUser(loginFormDto);
		
		assertEquals(responseExpected.getBody().getPseudo(), loginFormDto.getPseudo());
		assertTrue((passwordEncoder().matches(loginFormDto.getPassword(), responseExpected.getBody().getPassword())));
		assertEquals(responseExpected.getBody().isAdmin(), expectedModerator);
	}
	
	@Test
	@Order(3)
	void shouldNotGetAuthenticateUser() {
		
		LoginFormDto loginFormDto = new LoginFormDto("yolo", "yolooo");
		
		ResponseEntity<UserResponse> responseExpected = connexionService.getAuthenticateUser(loginFormDto);
		
		assertNull(responseExpected.getBody());
		assertEquals(responseExpected.getStatusCode(), HttpStatus.NOT_FOUND);

	}

}
