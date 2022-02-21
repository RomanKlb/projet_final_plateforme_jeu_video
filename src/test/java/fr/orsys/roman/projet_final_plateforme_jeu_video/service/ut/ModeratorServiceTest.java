package fr.orsys.roman.projet_final_plateforme_jeu_video.service.ut;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Moderator;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserModeratorDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB.ModeratorAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.repository.ModeratorRepository;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ModeratorService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.impl.ModeratorServiceImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(MockitoJUnitRunner.class)
public class ModeratorServiceTest {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@MockBean
	ModeratorRepository moderatorRepository;

	@InjectMocks
	ModeratorService moderatorService = new ModeratorServiceImpl(moderatorRepository, passwordEncoder());
	
	UserModeratorDto moderatorDto;
	
	Moderator moderatorExpected;
	
	
//	WTF???? repo null?
	
	@BeforeEach
	public void setup() {
		moderatorDto = new UserModeratorDto();
		moderatorDto.setPseudo("blabla");
		moderatorDto.setPassword("azerty");
		moderatorDto.setEmail("blabla@gmail.com");
		moderatorDto.setPhoneNumber("0123456789");

		moderatorExpected = new Moderator();
		moderatorExpected.setId(4L);
		moderatorExpected.setPseudo("blabla");
		moderatorExpected.setPassword("azerty");
		moderatorExpected.setEmail("blabla@gmail.com");
		moderatorExpected.setPhoneNumber("0123456789");
	}
	
	/*@Test
	@Order(1)
	void shouldSaveModerator() throws ModeratorAlreadyExistInDbException {

		
//		when(itemMapper.mapCreateItemDtoToEntity(any()))
//		.thenReturn(returnedItem);
		
		when(moderatorRepository.save(any())).thenReturn(moderatorExpected);
		
		System.out.println(moderatorDto == null);
		Moderator result = moderatorService.createUserModerator(moderatorDto);
		
		verify(moderatorRepository.save(any(Moderator.class)));
		assertEquals(result, moderatorExpected);
	}*/

}
