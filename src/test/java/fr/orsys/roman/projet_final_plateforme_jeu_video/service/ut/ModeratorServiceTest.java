package fr.orsys.roman.projet_final_plateforme_jeu_video.service.ut;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
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
class ModeratorServiceTest {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Mock
	ModeratorRepository moderatorRepository;

	@InjectMocks
	ModeratorService moderatorService = new ModeratorServiceImpl(moderatorRepository, passwordEncoder());

	
//	WTF???? repo null?
	@Test
	@Order(1)
	void shouldSaveModerator() throws ModeratorAlreadyExistInDbException {

		UserModeratorDto userModeratorDto = new UserModeratorDto();
		userModeratorDto.setPseudo("blabla");
		userModeratorDto.setPassword("azerty");
		userModeratorDto.setEmail("blabla@gmail.com");
		userModeratorDto.setPhoneNumber("0123456789");

		Moderator moderatorExpected = new Moderator();
		moderatorExpected.setId(4L);
		moderatorExpected.setPseudo("blabla");
		moderatorExpected.setPassword("azerty");
		moderatorExpected.setEmail("blabla@gmail.com");
		moderatorExpected.setPhoneNumber("0123456789");

//		when(itemMapper.mapCreateItemDtoToEntity(any()))
//		.thenReturn(returnedItem);
		
		when(moderatorRepository.save(any())).thenReturn(moderatorExpected);
		
		Moderator result = moderatorService.createUserModerator(userModeratorDto);


		verify(moderatorService.createUserModerator(any(UserModeratorDto.class)));


	}

}
