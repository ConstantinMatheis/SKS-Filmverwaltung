package filmservice;

import model.Film;

import java.util.List;

public interface FilmServiceInterface {

    List<Film> getAllFilms();
    List<Film> getFilmsByTitle(String title);
}
