package filmservice;

import model.Film;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class FilmService implements FilmServiceInterface {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Film> getAllFilms() {
        return em.createNamedQuery("Film.selectAll", Film.class)
                .getResultList();
    }

    @Override
    public List<Film> getFilmsByTitle(String title) {
        return em.createNamedQuery("Film.selectByName", Film.class)
                .setParameter("title", title)
                .getResultList();
    }

}
