package fr.orsys.roman.projet_final_plateforme_jeu_video.http.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.CreateGameDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.CreateReviewsDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserGamerDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.dto.UserModeratorDto;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB.GamerAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.existInDB.ModeratorAlreadyExistInDbException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.GameNotFoundException;
import fr.orsys.roman.projet_final_plateforme_jeu_video.business.exception.notFoundInDb.GamerNotFoundException;
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
	private void init() throws ModeratorAlreadyExistInDbException, GamerAlreadyExistInDbException, GameNotFoundException, GamerNotFoundException {
		initClassifications();
		initPlatforms();
		initGenres();
		initBusinessModel();
		initModerator();
		initEditors();
		initGames();
		initGamers();
		initReviews();
	}
	
	/**
	 * Generates 10 reviews
	 * @throws GamerNotFoundException 
	 * @throws GameNotFoundException 
	 */
	private void initReviews() throws GameNotFoundException, GamerNotFoundException {
		if(reviewsService.findAllReviews().size() < 1) {
			CreateReviewsDto dto = new CreateReviewsDto();
			dto.setDescription("Un tr??s bon jeu !");
			dto.setGameId(1L);
			dto.setRating(12F);
			dto.setGamerId(1L);
			reviewsService.saveOneReviews(dto);
			
			dto.setDescription("Un tr??s bon jeu !");
			dto.setGameId(1L);
			dto.setRating(12F);
			dto.setGamerId(2L);
			reviewsService.saveOneReviews(dto);
			
			dto.setDescription("Un tr??s bon jeu !");
			dto.setGameId(1L);
			dto.setRating(12F);
			dto.setGamerId(1L);
			reviewsService.saveOneReviews(dto);
			
			dto.setDescription("Un jeu m??diocre !!!");
			dto.setGameId(2L);
			dto.setRating(1F);
			dto.setGamerId(3L);
			reviewsService.saveOneReviews(dto);
			
			dto.setDescription("Un jeu bof bof!");
			dto.setGameId(4L);
			dto.setRating(10F);
			dto.setGamerId(2L);
			reviewsService.saveOneReviews(dto);
			
			dto.setDescription("Un tr??s bon jeu !");
			dto.setGameId(5L);
			dto.setRating(12F);
			dto.setGamerId(1L);
			reviewsService.saveOneReviews(dto);
			
			dto.setDescription("Un tr??s bon jeu !");
			dto.setGameId(5L);
			dto.setRating(12F);
			dto.setGamerId(3L);
			reviewsService.saveOneReviews(dto);
			
			dto.setDescription("Un jeu qui passe!");
			dto.setGameId(4L);
			dto.setRating(11F);
			dto.setGamerId(1L);
			reviewsService.saveOneReviews(dto);
			
			dto.setDescription("Un tr??s bon jeu !");
			dto.setGameId(4L);
			dto.setRating(12F);
			dto.setGamerId(2L);
			reviewsService.saveOneReviews(dto);
			
			dto.setDescription("Un tr??s bon jeu !");
			dto.setGameId(5L);
			dto.setRating(12F);
			dto.setGamerId(6L);
			reviewsService.saveOneReviews(dto);
			
			dto.setDescription("GTFO est un jeu coop ultra exigeant, ultra difficile, avec une ambiance extr??mement bien travaill??e : tr??s claustrophobe, tr??s inspir??e de l'univers d'Alien et m??me une vibe comparable ?? SCP sur le principe d'un complexe scientifique verrouill?? car infest?? de monstres pouvant mettre en p??ril le reste de l'humanit??. Globalement si vous ??tes un peu ?? court de jeu coop ?? jouer avec des potes, foncez parce que ??a en vaut vraiment le coup.\r\n"
					+ "\r\n"
					+ "Sur quelques points n??gatifs que d'autres ont pu souligner :\r\n"
					+ "\r\n"
					+ "- Le jeu est infaisable en solo ou ?? moins de quatre car trop difficile : Ce n'est plus le cas gr??ce ?? l'ajout des bots dans la 1.0. Bots qui sont d'ailleurs vraiment bons pendant les fights, ils ont une meilleures vis??e que la plupart des joueurs et ne feront pas non plus foirer vos phases furtives. Quand bien m??me vous ne voulez pas avoir recours aux bots, je pense que la plupart des missions sont faisables ?? trois ?? condition de bien conna??tre les m??caniques. Si vous ??tes un dieu vivant au jeu vous pouvez essayer en solo, personnellement jamais r??ussi mais il existe pas mal de vid??o sur Youtube de gens qui y arrive donc allez voir, c'est assez impressionnant.\r\n"
					+ "\r\n"
					+ "- ?? la moindre erreur c'est l'??chec assur?? : Pareil ce n'est plus le cas depuis l'ajout des points de contr??le de la 1.0, m??me si personnellement je rechigne un peu ?? les utiliser. Pour remettre dans le contexte, quand vous lancez une mission vous ??tes litt??ralement un prisonnier qu'on force ?? plonger dans un complexe sous-terrain ultra-dangereux o?? vos chances de survivre sont proches de z??ro. Donc ??chouer c'est un peu la \"norme\" durant vos premi??re partie, ??a ira mieux au fur et ?? mesure que vous apprendrez de vos erreurs. Il faut juste insister un peu et pas se laisser d??courager.\r\n"
					+ "\r\n"
					+ "- Pas assez d'armes et d'??quipement disponible : Le jeu fonctionne avec des rotations de Rundown et ?? chaque fois que cette-derni??re change, une partie des ??quipement disponible change avec elle. On se retrouve pour une Rundown avec g??n??ralement une dizaine d'arme principale, une dizaine d'armes secondaire, 6 ou 7 gadgets et 4 armes de m??l??e. On se retrouve du coup ?? faire le tour de tout ??a assez rapidement, c'est vrai.\r\n"
					+ "\r\n"
					+ "- Une mission prend 2 heures ?? ??tre compl??t??e : Oui ou 1h30 si vous n'explorez pas toute la carte et que vous vous concentrez sur l'objectif. C'est long, c'est vrai. Moi ??a ne me d??range pas mais ??a peu en rebuter certains.\r\n"
					+ "\r\n"
					+ "Voil?? mon avis sur le jeu, j'esp??re qu'il aura suffit ?? convaincre certains de le prendre au moins pour tester. ");
			dto.setGameId(1L);
			dto.setRating(12F);
			dto.setGamerId(6L);
			reviewsService.saveOneReviews(dto);
			
			dto.setDescription("Tr??s bon jeu coop. Auparavant relativement hardcore mais s'est beaucoup casualis?? (notamment par l'ajout de checkpoints) lors de la sortie officielle du jeu. C'est tr??s positif pour les nouveaux joueurs potentiels, gros ajouts de narration.\r\n"
					+ "Je conseillais le jeu ?? tout le monde avant la sortie du jeu, dor??navant encore plus.\r\n"
					+ "C'est un excellent revamp de la recette L4D / coop ?? 4. Exigeant et grisant.");
			dto.setGameId(1L);
			dto.setRating(12F);
			dto.setGamerId(5L);
			reviewsService.saveOneReviews(dto);
		}
	}
	
	/**
	 * Generates 6 Gamers
	 * @throws GamerAlreadyExistInDbException
	 */
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

	/**
	 * Generates 3 Moderators
	 * @throws ModeratorAlreadyExistInDbException
	 */
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
	
	/**
	 * Generates the 5 PEGI Classifications
	 */
	private void initClassifications() {
		if (classificationService.getClassifications().size() < 1) {
			classificationService.createClassification("PEGI 3");
			classificationService.createClassification("PEGI 7");
			classificationService.createClassification("PEGI 12");
			classificationService.createClassification("PEGI 16");
			classificationService.createClassification("PEGI 18");
		}
	}

	/**
	 * Generates 13 different platforms
	 */
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

	/**
	 * Generates 7 Game Genres
	 */
	private void initGenres() {
		if (genreService.count() < 1) {
			genreService.addGenre("Sandbox");
			genreService.addGenre("Action");
			genreService.addGenre("Adventure");
			genreService.addGenre("FPS");
			genreService.addGenre("MMORPG");
			genreService.addGenre("TPS");
			genreService.addGenre("RPG");
		}
	}

	/**
	 * Generates 2 BusinessModels
	 */
	private void initBusinessModel() {
		if(businessModelService.getAll().size() < 1) {
			businessModelService.createModel("Free to play");
			businessModelService.createModel("Pay to play");
		}
	}
	
	/**
	 * Generates 7 Publishers
	 */
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
	
	
	/**
	 * Generates 10 different games
	 */
	private void initGames() {
		if(gameService.getAll().size() < 1) {
			CreateGameDto gtfo = new CreateGameDto();
			List<Long> platforms = new ArrayList<>(Arrays.asList(1L));
			gtfo.setName("GTFO");
			gtfo.setDescription("GTFO est un jeu vid??o coop??ratif de tir ?? la premi??re personne et de type survival horror d??velopp?? par le studio ind??pendant su??dois 10 Chambers Collective. GTFO est sorti en acc??s anticip?? le 9 d??cembre 2019 sur Windows et a re??u des r??actions positives des utilisateurs");
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
			dto.setDescription("Escape from Tarkov est un jeu vid??o de tir tactique ?? la premi??re personne cr???? par le studio russe Battlestate Games. Le jeu est sorti en version alpha ferm??e le 4 ao??t 2016. En juin 2017, le jeu passe en b??ta. Le jeu est actuellement toujours en B??ta.");
			dto.setReleaseDate(LocalDate.of(2016, 8, 4));
			dto.setClassificationId(5L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(4L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(6L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(12L, 13L));
			dto.setName("The Legend of Zelda: Breath of the Wild");
			dto.setDescription("The Legend of Zelda: Breath of the Wild est un jeu d'action-aventure d??velopp?? par la division Nintendo EPD, assist?? par Monolith Soft, et publi?? par Nintendo le 3 mars 2017 sur Nintendo Switch lors du lancement de la console, ainsi que sur Wii U dont il est le dernier jeu produit par Nintendo");
			dto.setReleaseDate(LocalDate.of(2017, 3, 3));
			dto.setClassificationId(3L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(7L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(7L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(1L, 5L, 9L));
			dto.setName("For Honor");
			dto.setDescription("For Honor est un jeu vid??o m??lant combat et beat them all d??velopp?? par Ubisoft Montr??al et ??dit?? par Ubisoft sur PlayStation 4, Xbox One et Microsoft Windows, disponible depuis le 14 f??vrier 2017");
			dto.setReleaseDate(LocalDate.of(2017, 2, 14));
			dto.setClassificationId(4L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(2L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(3L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(1L, 5L, 9L));
			dto.setName("Tom Clancy's The Division 2");
			dto.setDescription("Tom Clancy???s The Division 2 est un jeu vid??o en ligne en monde ouvert de tir tactique et d'action-RPG d??velopp?? par Massive Entertainment et ??dit?? par Ubisoft, sorti le 15 mars 2019 sur PlayStation 4, Xbox One et Windows. Il fait suite ?? Tom Clancy's The Division");
			dto.setReleaseDate(LocalDate.of(2019, 2, 7));
			dto.setClassificationId(5L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(6L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(3L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(1L, 5L, 9L));
			dto.setName("Watch Dogs");
			dto.setDescription("Watch Dogs est un jeu vid??o d'action-aventure et d'infiltration d??velopp?? par les studios Ubisoft Montr??al et Ubisoft Reflections. Il est annonc?? par Ubisoft lors de sa conf??rence ?? l'E3 2012");
			dto.setReleaseDate(LocalDate.of(2014, 5, 27));
			dto.setClassificationId(5L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(6L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(3L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(1L, 5L, 6L, 9L, 10L, 11L));
			dto.setName("Assassin's Creed Valhalla");
			dto.setDescription("Assassin's Creed Valhalla est un jeu vid??o d'action-aventure et de r??le, d??velopp?? par Ubisoft Montr??al et ??dit?? par Ubisoft, sorti en novembre 2020 sur Microsoft Windows, PlayStation 4, Xbox One, Xbox Series, Google Stadia et PlayStation 5");
			dto.setReleaseDate(LocalDate.of(2020, 11, 10));
			dto.setClassificationId(4L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(3L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(3L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(1L, 5L, 9L));
			dto.setName("Far Cry 5");
			dto.setDescription("Far Cry 5 est un jeu vid??o de tir ?? la premi??re personne, d'action-aventure ??dit?? par Ubisoft et d??velopp?? conjointement par les studios Ubisoft Montr??al et Ubisoft Toronto. Apr??s un premier teaser le 22 mai 2017, il est annonc?? officiellement le 26 mai 2017, et sa sortie a lieu le 27 mars 2018.");
			dto.setReleaseDate(LocalDate.of(2018, 3, 26));
			dto.setClassificationId(5L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(4L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(3L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(1L, 5L, 6L, 9L, 10L, 11L));
			dto.setName("Tom Clancy's Rainbow Six: Extraction");
			dto.setDescription("Tom Clancy's Rainbow Six: Extraction est un jeu vid??o survival horror en coop??ration de un ?? trois joueurs, d??velopp?? par Ubisoft Montr??al et ??dit?? par Ubisoft, sorti le 20 janvier 2022 sur PlayStation 4, Xbox One, Amazon Luna, Stadia, Windows, PlayStation 5 et Xbox Series");
			dto.setReleaseDate(LocalDate.of(2018, 3, 26));
			dto.setClassificationId(5L);
			dto.setBusinessModelId(2L);
			dto.setGenreId(4L);
			dto.setPlatformIds(platforms);
			dto.setEditorId(3L);
			gameService.saveGame(dto);
			
			platforms = new ArrayList<>(Arrays.asList(1L, 5L, 6L, 9L, 10L, 11L));
			dto.setName("Tom Clancy's Rainbow Six: Siege");
			dto.setDescription("Tom Clancy's Rainbow Six Siege est un jeu vid??o de tir tactique d??velopp?? par Ubisoft Montr??al et ??dit?? par Ubisoft, sorti le 1????? d??cembre 2015 sur PlayStation 4, Xbox One et Windows. Le jeu sort ??galement sur Google Stadia le 30 juin 2021.");
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
