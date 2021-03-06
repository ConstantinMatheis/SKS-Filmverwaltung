package service;

import model.Film;

import java.util.List;

public interface FilmServiceInterface {

    List<Film> getAllFilms();
    List<Film> getFilmsByTitle(String title);
    Boolean importFilms(String fimXml);
}
