import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "t_actor")
@NamedQuery(name = "Actor.selectAll", query = "SELECT n FROM Actor n")
public class Actor {

    private Long pk_actor_id;
    private String first_name;
    private String last_name;
    private Date birthday;

    public Actor() {
    }

    public Actor(Long pk_actor_id, String first_name, String last_name, Date birthday) {
        this.pk_actor_id = pk_actor_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
    }

    public Long getPk_actor_id() {
        return pk_actor_id;
    }

    public void setPk_actor_id(Long pk_actor_id) {
        this.pk_actor_id = pk_actor_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
