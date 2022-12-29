package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exception.MovieException;
import ba.unsa.etf.rpr.exception.MovieException;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

import java.util.List;


public class MovieManager {


    public void delete(int movieId) throws MovieException{
        try{
            DaoFactory.movieDao().delete(movieId);
        }catch (MovieException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new MovieException("Cannot delete this movie since tickets have already been reserved.");
            }
            throw e;
        }

    }

    public Movie update(Movie cat) throws MovieException{
        return DaoFactory.movieDao().update(cat);
    }

    public List<Movie> getAll() throws MovieException{
        return DaoFactory.movieDao().getAll();
    }
    public void validateAddFields(String name, String genre, String datetime, Integer duration,
                                  Integer min, Integer hour) throws MovieException {
        if(name.isEmpty() || genre.isEmpty() || datetime.isEmpty() || duration==null
        || min==null || hour==null)
            throw  new MovieException("To successfully perform an action, all fields must be filled in!");
    }
     public void validateDeleteFields(Integer id) throws MovieException {
        if(id==null)
            throw  new MovieException("To successfully perform an action, id field must be filled in!");
    }
    public Movie add(Movie m) throws MovieException{
        return DaoFactory.movieDao().add(m);
    }
    public  Movie getById(int id) throws MovieException{
        return DaoFactory.movieDao().getById(id);
    }
}
