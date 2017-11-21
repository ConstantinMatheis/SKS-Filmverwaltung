package model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "t_actor")

@NamedQueries({
    @NamedQuery(
        name = "model.Actor.selectByName",
        query = "SELECT n FROM Actor n WHERE n.last_name = :last_name " +
                "AND n.first_name = :first_name "
    )
})

@NamedQuery(name = "model.Actor.selectAll", query = "SELECT n FROM Actor n")
@XmlRootElement(name = "actor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Actor {

    private Long pk_actor_id;
    @XmlAttribute(name = "first_name")
    private String first_name;
    @XmlAttribute(name = "last_name")
    private String last_name;
    @XmlAttribute(name = "birthday")
    private Date birthday;
    @XmlAttribute(name = "gender")
    private Gender gender;
    @XmlTransient
    private Set<Film> films = new HashSet<Film>(0);

    public Actor() {
    }

    public Actor(Long pk_actor_id, String first_name, String last_name, Date birthday) {
        this.pk_actor_id = pk_actor_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(nullable = false, unique = true)
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

    @XmlTransient
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "actors")
    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public enum Gender {
        MALE,
        FEMALE
    }
}
