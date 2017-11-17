package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_films")
//@NamedQuery(name = "model.Film.selectAll", query = "SELECT n FROM model.Film n") // als Name kann man irgendwas nehmen
//@NamedQuery(name = "model.Film.selectByName", query = "SELECT * FROM model.Film WHERE model.Film.Name == ") // als Name kann man irgendwas nehmen

@NamedQueries({
        @NamedQuery(
                name = "model.Film.selectAll",
                query = "SELECT n FROM Film n"
        ),
        @NamedQuery(
                name = "model.Film.selectByName",
                query = "FROM Film film WHERE film.title LIKE CONCAT('%', :title, '%')"
        )
})
public class Film {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    // @Column(name = "id") -> um Spaltennamen zu ändern
    private Long pk_film_id;
    private Integer release_year;
    private String title;
    private String description;
    private Integer running_time;
    private String language;
    private Double budget;
    private Genre genre;

    private Set<Actor> actors = new HashSet<Actor>(0);

    private Studio studio;

    public Film() {
    }

    public Film(Long pk_film_id, Integer release_year, String title, Integer running_time, String language, Double budget) {
        this.pk_film_id = pk_film_id;
        this.release_year = release_year;
        this.title = title;
        this.running_time = running_time;
        this.language = language;
        this.budget = budget;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(nullable = false, unique = true)
    public Long getPk_film_id() {
        return pk_film_id;
    }

    public void setPk_film_id(Long pk_film_id) {
        this.pk_film_id = pk_film_id;
    }

    public Integer getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Integer release_year) {
        this.release_year = release_year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRunning_time() {
        return running_time;
    }

    public void setRunning_time(Integer running_time) {
        this.running_time = running_time;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "film_actor",
            joinColumns = {
                    @JoinColumn(name = "fk_film_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "fk_actor_id", nullable = false, updatable = false)})
    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    @ManyToOne
    @JoinColumn(name = "fk_studio_id", nullable = true)
    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    @Enumerated(EnumType.STRING)
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public enum Genre {
        ACTION, ANIMATION, COMEDY, DRAMA, HORROR, SCIENCE_FICTION, THRILLER, WESTERN
    }
}
