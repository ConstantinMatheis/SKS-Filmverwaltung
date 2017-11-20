package filmservice;

import com.sun.org.apache.xpath.internal.SourceTree;
import model.Film;
import model.Films;
import model.Studio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
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

    @Override
    public Boolean importFilms(String filmXml) {
        Films films = null;
        filmXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>    " + filmXml;
        try {
            films = getFilms(filmXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("################# Film = " + films);
        for(Film film : films.getFilms()) {
            System.out.println("Studio = " + film.getStudio());

            if(!filmHasActorsAndStudio(film)) {
                return false;
            }

//            Studio studio = film.getStudio();

            Long actorId = em.createNamedQuery("model.Actor.getActorKey", Long.class)
                    .setParameter("first_name", "Ted")
                    .setParameter("last_name", "Ericson")
                    .getSingleResult();

            Long studioId = em.createNamedQuery("model.Studio.getStudioKey", Long.class)
                    .setParameter("name", "Berlin Works")
                    .getSingleResult();

            System.out.println("actorId: " + Long.toString(actorId));
            System.out.println("studioId: " + Long.toString(studioId));

        }



        return true;
    }

    private boolean filmHasActorsAndStudio(Film film) {
        return film.getStudio() != null && !film.getActors().isEmpty();
    }


    private Films getFilms(String filmXml) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Films.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Source source = new StreamSource(new StringReader(filmXml));
        JAXBElement<Films> jaxbElement = unmarshaller.unmarshal(source, Films.class);
        return jaxbElement.getValue();
    }
}
