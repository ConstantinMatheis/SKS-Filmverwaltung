package webservice;

import filmservice.FilmService;
import model.Film;
import webservice.FilmWebServiceInterface;

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
}