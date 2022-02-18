package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Gamer;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.Reviews;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.CreateGameDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserGamerDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserModeratorDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB.GamerAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB.ModeratorAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.BusinessModelService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ClassificationService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.EditorService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GameService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GamerService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.GenreService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ModeratorService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.PlatformService;
import fr.orsys.roman.projet_final_plateforme_jeu_video.service.ReviewsService;

@Controller
public class InitController {
	private static Random rand = new Random();
	private final ClassificationService classificationService;
	private final PlatformService platformService;
	private final GenreService genreService;
	private final BusinessModelService businessModelService;
	private final ModeratorService moderatorService;
	private final EditorService editorService;
	private final GameService gameService;
	private final GamerService gamerService;
	private final ReviewsService reviewsService;

	

	public InitController(ClassificationService classificationService, PlatformService platformService,
			GenreService genreService, BusinessModelService businessModelService, ModeratorService moderatorService,
			EditorService editorService, GameService gameService, GamerService gamerService, ReviewsService reviewsService) {
		super();
		this.classificationService = classificationService;
		this.platformService = platformService;
		this.genreService = genreService;
		this.businessModelService = businessModelService;
		this.moderatorService = moderatorService;
		this.editorService = editorService;
		this.gameService = gameService;
		this.gamerService = gamerService;
		this.reviewsService = reviewsService;
	}

	@PostConstruct
	private void init() throws ModeratorAlreadyExistInDbException, GamerAlreadyExistInDbException {
		initClassifications();
		initPlatforms();
		initGenres();
		initBusinessModel();
		initModerator();
		initEditors();
		initGames();
		initGamers();
	}
	
	private void initGamers() throws GamerAlreadyExistInDbException {
		if(gamerService.findAll().size() < 1) {
			UserGamerDto gamer = new UserGamerDto();
			gamer.setBirthDate(LocalDate.now().minusYears(5));
			gamer.setEmail("a@a.fr");
			gamer.setPseudo("coco");
			gamer.setPassword("azerty");
			gamerService.saveUserGamer(gamer);

			gamer.setBirthDate(LocalDate.now().minusYears(5));
			gamer.setEmail("b@b.fr");
			gamer.setPseudo("coca");
			gamer.setPassword("azerty");
			gamerService.saveUserGamer(gamer);
			
			gamer.setBirthDate(LocalDate.now().minusYears(5));
			gamer.setEmail("c@c.fr");
			gamer.setPseudo("dodo");
			gamer.setPassword("azerty");
			gamerService.saveUserGamer(gamer);
			
			gamer.setBirthDate(LocalDate.now().minusYears(5));
			gamer.setEmail("d@d.fr");
			gamer.setPseudo("didi");
			gamer.setPassword("azerty");
			gamerService.saveUserGamer(gamer);
			
			gamer.setBirthDate(LocalDate.now().minusYears(5));
			gamer.setEmail("e@e.fr");
			gamer.setPseudo("dodu");
			gamer.setPassword("azerty");
			gamerService.saveUserGamer(gamer);
			
			gamer.setBirthDate(LocalDate.now().minusYears(5));
			gamer.setEmail("f@f.fr");
			gamer.setPseudo("efdo");
			gamer.setPassword("azerty");
			gamerService.saveUserGamer(gamer);
		}
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
			platformService.createPlatform("PC");
			platformService.createPlatform("PlayStation");
			platformService.createPlatform("PlayStation 2");
			platformService.createPlatform("PlayStation 3");
			platformService.createPlatform("PlayStation 4");
			platformService.createPlatform("PlayStation 5");
			platformService.createPlatform("Xbox");
			platformService.createPlatform("Xbox 360");
			platformService.createPlatform("Xbox One");
			platformService.createPlatform("Xbox Series S");
			platformService.createPlatform("Xbox Series X");
			platformService.createPlatform("Wii U");
			platformService.createPlatform("Nintendo Switch");
		}
	}

	private void initGenres() {
		if (genreService.count() == 0) {
			genreService.addGenre("Sandbox");
			genreService.addGenre("Action");
			genreService.addGenre("Adventure");
			genreService.addGenre("FPS");
			genreService.addGenre("MMORPG");
			genreService.addGenre("TPS");
			genreService.addGenre("RPG");
		}
	}

	private void initBusinessModel() {
		if(businessModelService.getAll().size() < 1) {
			businessModelService.createModel("Free to play");
			businessModelService.createModel("Pay to play");
		}
	}
	
	private void initEditors() {
		if(editorService.getEditors().size() < 1) {
			editorService.createEditor("Electronic Arts");
			editorService.createEditor("Activision");
			editorService.createEditor("Ubisoft");
			editorService.createEditor("Square Enix");
			editorService.createEditor("10 Chambers");
			editorService.createEditor("Battlestate Games");
			editorService.createEditor("Nintendo");
		}
	}
	
	private void initGames() {
		if(gameService.getAll().size() < 1) {
			CreateGameDto gtfo = new CreateGameDto();
			List<Long> platforms = new ArrayList<>(Arrays.asList(1L));
			gtfo.setName("GTFO");
			gtfo.setDescription("GTFO est un jeu vidéo coopératif de tir à la première personne et de type survival horror développé par le studio indépendant suédois 10 Chambers Collective. GTFO est sorti en accès anticipé le 9 décembre 2019 sur Windows et a reçu des réactions positives des utilisateurs");
			gtfo.setReleaseDate(LocalDate.of(2021, 12, 9));
			gtfo.setClassificationId(3L);
			gtfo.setBusinessModelId(2L);
			gtfo.setGenreId(4L);
			gtfo.setPlatformIds(platforms);
			gtfo.setEditorId(5L);
			gameService.saveGame(gtfo);
			
			CreateGameDto dto = new CreateGameDto();
			platforms = new ArrayList<>(Arrays.asList(1L));
			dto.setName("Escape from Tarkov");
			dto.setDescription("Escape from Tarkov est un jeu vidéo de tir tactique à la première personne créé par le studio russe Battlestate Games. Le jeu est sorti en version alpha fermée le 4 août 2016. En juin 2017, le jeu passe en bêta. Le jeu est actuellement toujours en Bêta.");
			dto.setReleaseDate(LocalDate.of(2016, 8, 4));
			dto.setClassificationId(5L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(4L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(6L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(12L, 13L));
			dto.setName("The Legend of Zelda: Breath of the Wild");
			dto.setDescription("The Legend of Zelda: Breath of the Wild est un jeu d'action-aventure développé par la division Nintendo EPD, assisté par Monolith Soft, et publié par Nintendo le 3 mars 2017 sur Nintendo Switch lors du lancement de la console, ainsi que sur Wii U dont il est le dernier jeu produit par Nintendo");
			dto.setReleaseDate(LocalDate.of(2017, 3, 3));
			dto.setClassificationId(3L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(7L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(7L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(1L, 5L, 9L));
			dto.setName("For Honor");
			dto.setDescription("For Honor est un jeu vidéo mêlant combat et beat them all développé par Ubisoft Montréal et édité par Ubisoft sur PlayStation 4, Xbox One et Microsoft Windows, disponible depuis le 14 février 2017");
			dto.setReleaseDate(LocalDate.of(2017, 2, 14));
			dto.setClassificationId(4L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(2L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(3L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(1L, 5L, 9L));
			dto.setName("Tom Clancy's The Division 2");
			dto.setDescription("Tom Clancy’s The Division 2 est un jeu vidéo en ligne en monde ouvert de tir tactique et d'action-RPG développé par Massive Entertainment et édité par Ubisoft, sorti le 15 mars 2019 sur PlayStation 4, Xbox One et Windows. Il fait suite à Tom Clancy's The Division");
			dto.setReleaseDate(LocalDate.of(2019, 2, 7));
			dto.setClassificationId(5L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(6L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(3L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(1L, 5L, 9L));
			dto.setName("Watch Dogs");
			dto.setDescription("Watch Dogs est un jeu vidéo d'action-aventure et d'infiltration développé par les studios Ubisoft Montréal et Ubisoft Reflections. Il est annoncé par Ubisoft lors de sa conférence à l'E3 2012");
			dto.setReleaseDate(LocalDate.of(2014, 5, 27));
			dto.setClassificationId(5L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(6L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(3L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(1L, 5L, 6L, 9L, 10L, 11L));
			dto.setName("Assassin's Creed Valhalla");
			dto.setDescription("Assassin's Creed Valhalla est un jeu vidéo d'action-aventure et de rôle, développé par Ubisoft Montréal et édité par Ubisoft, sorti en novembre 2020 sur Microsoft Windows, PlayStation 4, Xbox One, Xbox Series, Google Stadia et PlayStation 5");
			dto.setReleaseDate(LocalDate.of(2020, 11, 10));
			dto.setClassificationId(4L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(3L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(3L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(1L, 5L, 9L));
			dto.setName("Far Cry 5");
			dto.setDescription("Far Cry 5 est un jeu vidéo de tir à la première personne, d'action-aventure édité par Ubisoft et développé conjointement par les studios Ubisoft Montréal et Ubisoft Toronto. Après un premier teaser le 22 mai 2017, il est annoncé officiellement le 26 mai 2017, et sa sortie a lieu le 27 mars 2018.");
			dto.setReleaseDate(LocalDate.of(2018, 3, 26));
			dto.setClassificationId(5L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(4L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(3L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(1L, 5L, 6L, 9L, 10L, 11L));
			dto.setName("Tom Clancy's Rainbow Six: Extraction");
			dto.setDescription("Tom Clancy's Rainbow Six: Extraction est un jeu vidéo survival horror en coopération de un à trois joueurs, développé par Ubisoft Montréal et édité par Ubisoft, sorti le 20 janvier 2022 sur PlayStation 4, Xbox One, Amazon Luna, Stadia, Windows, PlayStation 5 et Xbox Series");
			dto.setReleaseDate(LocalDate.of(2018, 3, 26));
			dto.setClassificationId(5L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(4L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(3L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(1L, 5L, 6L, 9L, 10L, 11L));
			dto.setName("Tom Clancy's Rainbow Six: Siege");
			dto.setDescription("Tom Clancy's Rainbow Six Siege est un jeu vidéo de tir tactique développé par Ubisoft Montréal et édité par Ubisoft, sorti le 1ᵉʳ décembre 2015 sur PlayStation 4, Xbox One et Windows. Le jeu sort également sur Google Stadia le 30 juin 2021.");
			dto.setReleaseDate(LocalDate.of(2015, 12, 1));
			dto.setClassificationId(5L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(4L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(3L);
			gameService.saveGame(dto);
		}	
	}
}
