package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exception.MovieException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 * @author Emina Efendic
 */
public class MovieDaoSQLImpl extends AbstractDao<Movie> implements MovieDao {

    private static MovieDaoSQLImpl instance = null;
    private MovieDaoSQLImpl() {
        super("movies");
    }

    /**
     * @author Emina Efendic
     * @return QuoteDaoSQLImpl
     * We don't need more than one object for CRUD operations on table 'quotes' so we will use Singleton
     * This method will call private constructor in instance==null and then return that instance
     */
    public static MovieDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new MovieDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Movie row2object(ResultSet rs) throws MovieException{
        try {
            Movie movie = new Movie();
            movie.setId(rs.getInt("id"));
            movie.setName(rs.getString("name"));
            movie.setGenre(rs.getString("genre"));
            movie.setDate_time(rs.getTimestamp("date_time").toLocalDateTime());
            movie.setDuration(rs.getInt("duration"));
            return movie;
        } catch (Exception e) {
            throw new MovieException(e.getMessage(), e);
        }
    }

    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(Movie object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("name", object.getName());
        item.put("genre", object.getGenre());
        item.put("date_time", object.getDate_time());
        item.put("duration", object.getDuration());
        return item;
    }
}
