package fr.orsys.roman.projet_final_plateforme_jeu_video.service.it;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Moderator;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserModeratorDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB.ModeratorAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ModeratorService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ModeratorServiceIT {

	@Autowired
	ModeratorService moderatorService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Test
	@Order(1)
	void shouldSaveUsermoderator() throws ModeratorAlreadyExistInDbException {

		UserModeratorDto userModeratorDto = new UserModeratorDto();
		userModeratorDto.setPseudo("blabla");
		userModeratorDto.setPassword("azerty");
		userModeratorDto.setEmail("blabla@gmail.com");
		userModeratorDto.setPhoneNumber("0123456789");

		Moderator moderatorExpected = moderatorService.createUserModerator(userModeratorDto);

		assertEquals(moderatorExpected.getPseudo(), userModeratorDto.getPseudo());
		assertEquals(moderatorExpected.getPhoneNumber(), userModeratorDto.getPhoneNumber());
		assertEquals(moderatorExpected.getEmail(), userModeratorDto.getEmail());
		assertTrue(passwordEncoder().matches(userModeratorDto.getPassword(),moderatorExpected.getPassword()));
	}

	@Test
	@Order(2)
	void shouldFindByIdGamer() {

		long id = 1L;

		Moderator moderatorExpected = moderatorService.findByIdModerator(id);

		assertEquals(moderatorExpected.getPseudo(), "alaric");
	}


	@Test
	@Order(3)
	void shouldFindAllGamers() {

		List<Moderator> moderators = moderatorService.findAll();

		assertTrue(moderators.size()>0);
		//		-1 du saveModerator dans le test
		assertEquals(moderators.size()-1, 3);
	}
}
