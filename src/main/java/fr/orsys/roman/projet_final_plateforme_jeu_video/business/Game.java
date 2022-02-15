package fr.orsys.roman.projet_final_plateforme_jeu_video.business;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le nom du jeu ne peut pas être vide")
    @NotBlank(message = "Le nom du jeu doit être complété")
    private String name;

    @Length(max = 10000, message = "La description est maximum de 10000 caractères")
    private String description;

    private LocalDate releaseDate;

    private String image;

    @ToString.Exclude
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Reviews> reviews;

    @ManyToOne
    private Classification classification;

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Editor editor;

    /*@ManyToOne
    private Platform platform;
*/
    @ToString.Exclude
    @ManyToMany
    private List<Platform> platforms;
    
    @ManyToOne
    private BusinessModel businessModel;

    public Game(
            @NotNull(message = "Le nom du jeu ne peut pas être vide") @NotBlank(message = "Le nom du jeu doit être complété") String name,
            @Length(max = 10000, message = "La description est maximum de 10000 caractères") String description,
            LocalDate releaseDate, String image, List<Reviews> reviews, Classification classification, Genre genre,
            Editor editor, List<Platform>platforms, BusinessModel businessModel) {
        super();
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.image = image;
        this.reviews = reviews;
        this.classification = classification;
        this.genre = genre;
        this.editor = editor;
        this.platforms = platforms;
        this.businessModel = businessModel;
    }



}