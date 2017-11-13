import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "t_films")
@NamedQuery(name = "Film.selectAll", query = "SELECT n FROM Film n") // als Name kann man irgendwas nehmen
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id") -> um Spaltennamen zu ändern
    private Long pk_film_id;
    private Date release_year;
    private String title;
    private Integer running_time;
    private String language;
    private Double budget;

    public Film() {}

    public Film(Date release_year, String title, Integer running_time, String language, Double budget, Long fk_studio_id) {
        this.release_year = release_year;
        this.title = title;
        this.running_time = running_time;
        this.language = language;
        this.budget = budget;
        this.fk_studio_id = fk_studio_id;
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

    public Long getFk_studio_id() {
        return fk_studio_id;
    }

    public void setFk_studio_id(Long fk_studio_id) {
        this.fk_studio_id = fk_studio_id;
    }

    private Long fk_studio_id;

    public Long getPk_film_id() {
        return pk_film_id;
    }

    public void setPk_film_id(Long pk_film_id) {
        this.pk_film_id = pk_film_id;
    }

    public Date getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Date release_year) {
        this.release_year = release_year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRunning_time() {
        return running_time;
    }

    public void setRunning_time(Integer running_time) {
        this.running_time = running_time;
    }
}
