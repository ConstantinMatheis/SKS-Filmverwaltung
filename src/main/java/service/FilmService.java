package service;

import model.Actor;
import model.Film;
import model.Films;
import model.Studio;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SecurityDomain("FilmManagementSD")
@RolesAllowed({"MSRead", "MSWrite"})
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

    @Transactional
    @Override
    public Boolean importFilms(String filmXml) {
        try {
            Films xmlFilms = getFilmsFromXml(filmXml);
            persistFilms(xmlFilms);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Successfully imported movie(s).");
        return true;
    }

    private Films getFilmsFromXml(String filmXml) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Films.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Source source = new StreamSource(new StringReader(filmXml));
        JAXBElement<Films> jaxbElement = unmarshaller.unmarshal(source, Films.class);
        return jaxbElement.getValue();
    }

    private void persistFilms(Films xmlFilms) {
        for (Film xmlFilm : xmlFilms.getFilms()) {
            xmlFilm.setActors(getDbActors(xmlFilm));
            xmlFilm.setStudio(getDbStudio(xmlFilm));
            em.persist(xmlFilm);
        }
    }

    private Set<Actor> getDbActors(Film film) {
        Set<Actor> dbActors = new HashSet<>();
        for (Actor xmlActor : film.getActors()) {
            Actor dbActor = getDbActorFromXml(xmlActor);
            dbActors.add(dbActor);
        }
        return dbActors;
    }

    private Actor getDbActorFromXml(Actor xmlActor) {
        try {
            return em.createNamedQuery("model.Actor.selectByName", Actor.class)
                    .setParameter("last_name", xmlActor.getLast_name())
                    .setParameter("first_name", xmlActor.getFirst_name())
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Actor '" + xmlActor.getFirst_name() + " " + xmlActor.getLast_name() + "' not found!");
        }
    }

    private Studio getDbStudio(Film film) {
        try {
            return em.createNamedQuery("model.Studio.selectByName", Studio.class)
                    .setParameter("name", film.getStudio().getName())
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Studio '" + film.getStudio().getName() + "' not found!");
        }
    }
}
