package model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "t_films")

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
@XmlRootElement(name = "film")
@XmlAccessorType(XmlAccessType.FIELD)
public class Film {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    // @Column(name = "id") -> um Spaltennamen zu ändern
    private Long pk_film_id;
    @XmlAttribute(name = "release_year")
    private Integer release_year;
    @XmlAttribute(name = "title")
    private String title;
    @XmlAttribute(name = "description")
    private String description;
    @XmlAttribute(name = "running_time")
    private Integer running_time;
    @XmlAttribute(name = "language")
    private String language;
    @XmlAttribute(name = "budget")
    private Double budget;
    @XmlAttribute(name = "genre")
    private Genre genre;

    @XmlElementWrapper(name = "actors")
    @XmlElement(name = "actor")
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Film{");
        sb.append("pk_film_id=").append(pk_film_id);
        sb.append(", release_year=").append(release_year);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", running_time=").append(running_time);
        sb.append(", language='").append(language).append('\'');
        sb.append(", budget=").append(budget);
        sb.append(", genre=").append(genre);
        sb.append(", actors=").append(actors);
        sb.append(", studio=").append(studio);
        sb.append('}');
        return sb.toString();
    }
}
