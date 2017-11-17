package webservice;

import model.Film;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface FilmWebServiceInterface {

    List<Film> getFilmsByName(String title);

}
