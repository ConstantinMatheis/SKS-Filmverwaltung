package filmservice;

import com.sun.org.apache.xpath.internal.SourceTree;
import model.Actor;
import model.Film;
import model.Films;
import model.Studio;

import javax.persistence.EntityManager;
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
        Films films = null;
        filmXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>    " + filmXml;
        try {
            films = getFilms(filmXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("################# Film = " + films);
        for(Film film : films.getFilms()) {
            System.out.println("Studio = " + film.getStudio());

//            if(!filmHasActorsAndStudio(film)) {
//                return false;
//            }

            Set<Actor> dbActors = new HashSet<>();
            for(Actor xmlActor : film.getActors()) {
                Actor dbActor = em.createNamedQuery("model.Actor.selectByName", Actor.class)
                        .setParameter("last_name", xmlActor.getLast_name())
                        .setParameter("first_name", xmlActor.getFirst_name())
                        .getSingleResult();
                if(dbActor == null) {
                    throw new RuntimeException("Actor not found!");
                }
                dbActors.add(dbActor);

            }
            Studio studio = em.createNamedQuery("model.Studio.selectByName", Studio.class)
                        .setParameter("name", film.getStudio().getName())
                        .getSingleResult();
            if(studio == null) {
                throw new RuntimeException("Studio not found!");
            }
            film.setActors(dbActors);
            film.setStudio(studio);

//            em.persist(studio);
//            em.getTransaction().begin();
            em.persist(film);
//            em.getTransaction().commit();

        }

        return true;
    }

//    private boolean filmHasActorsAndStudio(Film film) {
//        return film.getStudio() != null && !film.getActors().isEmpty();
//    }


    private Films getFilms(String filmXml) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Films.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Source source = new StreamSource(new StringReader(filmXml));
        JAXBElement<Films> jaxbElement = unmarshaller.unmarshal(source, Films.class);
        return jaxbElement.getValue();
    }
}
