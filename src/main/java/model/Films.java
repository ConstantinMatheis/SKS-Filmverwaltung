package model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "movies")
@XmlAccessorType(XmlAccessType.FIELD)
public class Films {

    @XmlElement(name = "movie")
    private List<Film> films;

    public Films() { }

    public Films(List<Film> films) {
        this.films = films;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
