package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserModeratorDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.ModeratorAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.BusinessModelService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ClassificationService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GenreService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ModeratorService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.PlatformService;

@Controller
public class InitController {

	private final ClassificationService classificationService;
	private final PlatformService platformService;
	private final GenreService genreService;
	private final BusinessModelService businessModelService;
	private final ModeratorService moderatorService;

	public InitController(ClassificationService classificationService, PlatformService platformService,
			GenreService genreService, BusinessModelService businessModelService, ModeratorService moderatorService) {
		this.classificationService = classificationService;
		this.platformService = platformService;
		this.genreService = genreService;
		this.businessModelService = businessModelService;
		this.moderatorService = moderatorService;
	}

	@PostConstruct
	private void init() throws ModeratorAlreadyExistInDbException {
		initClassifications();
		initPlatforms();
		initGenres();
		initBusinessModel();
		initModerator();
	}

	private void initModerator() throws ModeratorAlreadyExistInDbException {
		if(moderatorService.findAll().size() < 1) {
			UserModeratorDto moderator1 = new UserModeratorDto();
			moderator1.setEmail("alaric@gmail.com");
			moderator1.setPassword("azerty");
			moderator1.setPseudo("alaric");
			moderator1.setPhoneNumber("0123456789");
			moderatorService.createUserModerator(moderator1);
			UserModeratorDto moderator2 = new UserModeratorDto();
			moderator2.setEmail("roman@gmail.com");
			moderator2.setPassword("azerty");
			moderator2.setPseudo("roman");
			moderator2.setPhoneNumber("0123456789");
			moderatorService.createUserModerator(moderator2);
			UserModeratorDto moderator3 = new UserModeratorDto();
			moderator3.setEmail("moulaye@gmail.com");
			moderator3.setPassword("azerty");
			moderator3.setPseudo("moulaye");
			moderator3.setPhoneNumber("0123456789");
			moderatorService.createUserModerator(moderator3);
		}
	}
	
	private void initClassifications() {
		if (classificationService.getClassifications().size() < 1) {
			classificationService.createClassification("PEGI 3");
			classificationService.createClassification("PEGI 7");
			classificationService.createClassification("PEGI 12");
			classificationService.createClassification("PEGI 16");
			classificationService.createClassification("PEGI 18");
		}
	}

	private void initPlatforms() {
		if (platformService.getPlatforms().size() < 1) {
			platformService.createPlatform("PlayStation");
			platformService.createPlatform("PlayStation 2");
			platformService.createPlatform("Xbox");
		}
	}

	private void initGenres() {
		if (genreService.count() == 0) {
			genreService.addGenre("Sandbox");
			genreService.addGenre("Action");
			genreService.addGenre("Adventure");
		}
	}

	private void initBusinessModel() {
		if(businessModelService.getAll().size() < 1) {
			businessModelService.createModel("free to play");
			businessModelService.createModel("pay to play");
		}
	}
}
