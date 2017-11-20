package filmservice;

import model.Film;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
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
        Film film = null;
        filmXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>    " + filmXml;
        try {
            film = getFilms(filmXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("################# Film = " + film);
        System.out.println("Studio = " + film.getStudio());
        return true;
    }

    private Film getFilms(String filmXml) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Film.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Source source = new StreamSource(new StringReader(filmXml));
        JAXBElement<Film> jaxbElement = unmarshaller.unmarshal(source, Film.class);
        return jaxbElement.getValue();
    }
}
