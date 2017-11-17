package filmservice;

import model.Film;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class FilmService implements FilmServiceInterface {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Film> getAllFilms() {
        return em.createNamedQuery("model.Film.selectAll", Film.class)
                .getResultList();
    }

    @Override
    public List<Film> getFilmsByTitle(String title) {
        return em.createNamedQuery("model.Film.selectByName", Film.class)
                .setParameter("title", title)
                .getResultList();
    }

}
