import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "t_studios")
@NamedQuery(name = "Studio.selectAll", query = "SELECT n FROM Studio n")
public class Studio {

    private Long pk_studio_id;
    private String headquarters;
    private Integer founded_year;

    public Studio() {
    }

    public Studio(Long pk_studio_id, String headquarters, Integer founded_year) {

        this.pk_studio_id = pk_studio_id;
        this.headquarters = headquarters;
        this.founded_year = founded_year;
    }

    public Long getPk_studio_id() {
        return pk_studio_id;
    }

    public void setPk_studio_id(Long pk_studio_id) {
        this.pk_studio_id = pk_studio_id;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public Integer getFounded_year() {
        return founded_year;
    }

    public void setFounded_year(Integer founded_year) {
        this.founded_year = founded_year;
    }
}
