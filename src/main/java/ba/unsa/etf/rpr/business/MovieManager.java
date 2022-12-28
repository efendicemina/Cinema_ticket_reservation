package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exception.MovieException;
import ba.unsa.etf.rpr.exception.MovieException;

import java.util.List;


public class MovieManager {


    public void delete(int categoryId) throws MovieException{
        try{
            DaoFactory.movieDao().delete(categoryId);
        }catch (MovieException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new MovieException("NO");
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

}
