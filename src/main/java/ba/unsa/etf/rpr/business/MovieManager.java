package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exception.MovieException;

import java.util.List;
/**
 *The MovieManager class is a business layer class that handles operations related to movies.
 *It communicates with the data access layer (DaoFactory) to perform CRUD operations on the movie entities.
 *The class provides methods for adding, updating, deleting and retrieving movies.
 *@author Emina Efendic
 */

public class MovieManager {

    /**
     * Deletes movie, from db table movies, with a given id.
     * @param movieId int
     * @throws MovieException thrown in case of problem with db
     */
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

    /**
     * Updates movie in db table movies.
     * @param cat Movie
     * @return Movie that is updated
     * @throws MovieException thrown in case of problem with db
     */
    public Movie update(Movie cat) throws MovieException{
        return DaoFactory.movieDao().update(cat);
    }

    /**
     *Fetches all Movie objects from table movies and stores it in a list.
     * @return List of all movies
     * @throws MovieException thrown in case of problem with db
     */
    public List<Movie> getAll() throws MovieException{
        return DaoFactory.movieDao().getAll();
    }

    /**
     * Validates if all fields are filled in properly.
     * @param name String
     * @param genre String
     * @param datetime String
     * @param duration Integer
     * @param min Integer
     * @param hour Integer
     * @throws MovieException in case of invalid fields
     */
    public void validateAddFields(String name, String genre, String datetime, Integer duration,
                                  Integer min, Integer hour) throws MovieException {
        if(name.isEmpty() || genre.isEmpty() || datetime.isEmpty() || duration==null
        || min==null || hour==null)
            throw  new MovieException("To successfully perform an action, all fields must be filled in!");
    }

    /**
     * Validates if id field is properly filled in.
     * @param id Integer
     * @throws MovieException in case of invalid fields
     */
     public void validateDeleteFields(Integer id) throws MovieException {
        if(id==null)
            throw  new MovieException("To successfully perform an action, id field must be filled in!");
    }

    /**
     * Adds Movie object to table movies.
     * @param m Movie
     * @return added Movie
     * @throws MovieException in case of problems with db
     */
    public Movie add(Movie m) throws MovieException{
        return DaoFactory.movieDao().add(m);
    }

    /**
     * Fetches Movie object form table movies defined by given id.
     * @param id int
     * @return Movie object defined by given id
     * @throws MovieException in case of problems with db
     */
    public  Movie getById(int id) throws MovieException{
        return DaoFactory.movieDao().getById(id);
    }
}
