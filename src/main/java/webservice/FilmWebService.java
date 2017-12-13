package webservice;

import service.FilmService;
import model.Film;
import service.FilmServiceInterface;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;

@WebService
public class FilmWebService implements FilmWebServiceInterface {

    @Inject
    private FilmServiceInterface filmService;

    @Override
    public List<Film> getFilmsByName(String title) {
        return filmService.getFilmsByTitle(title);
    }

    @Override
    public Boolean importFilms(String filmXml) {
        filmXml = filmXml.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
        return filmService.importFilms(filmXml);
    }

    @Override
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }
}
