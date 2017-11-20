package webservice;

import filmservice.FilmService;
import model.Film;
import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;

@WebService
public class FilmWebService implements FilmWebServiceInterface {

    @Inject
    private FilmService filmService;

    @Override
    public List<Film> getFilmsByName(String title) {
        return filmService.getFilmsByTitle(title);
    }

    @Override
    public Boolean importFilms(String filmXml) {
        return filmService.importFilms(filmXml);
    }

    @Override
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }
}
