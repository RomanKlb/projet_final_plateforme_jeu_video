package fr.orsys.roman.projet_final_plateforme_jeu_video.service.it;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
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

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserGamerDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB.GamerAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GamerService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GamerServiceIT {

	@Autowired
	GamerService gamerService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Test
	@Order(1)
	void shouldSaveUserGamer() throws GamerAlreadyExistInDbException {

		UserGamerDto userGamerDto = new UserGamerDto();
		userGamerDto.setPseudo("blabla");
		userGamerDto.setPassword("azerty");
		userGamerDto.setEmail("blabla@gmail.com");
		userGamerDto.setBirthDate(LocalDate.of(2002, 02, 10));

		Gamer gamerExpected = gamerService.saveUserGamer(userGamerDto);

		assertEquals(gamerExpected.getPseudo(), userGamerDto.getPseudo());
		assertEquals(gamerExpected.getBirthDate(), userGamerDto.getBirthDate());
		assertEquals(gamerExpected.getEmail(), userGamerDto.getEmail());
		assertTrue(passwordEncoder().matches(userGamerDto.getPassword(), gamerExpected.getPassword()));
	}

	@Test
	@Order(2)
	void shouldFindByIdGamer() {

		long id = 1L;

		Gamer gamerExpected = gamerService.findByIdGamer(id);

		assertEquals(gamerExpected.getPseudo(), "coco");
	}


	@Test
	@Order(3)
	void shouldFindAllGamers() {

		List<Gamer> gamers = gamerService.findAll();

		assertTrue(gamers.size()>0);
		//		-1 du saveGamer dans le test
		assertEquals(gamers.size()-1, 6);
	}
}
