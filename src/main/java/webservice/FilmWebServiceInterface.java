package webservice;

import model.Film;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface FilmWebServiceInterface {

    @WebMethod
    List<Film> getAllFilms();

    @WebMethod
    List<Film> getFilmsByName(String title);

    @WebMethod
    Boolean importFilms(String fimXml);

}
