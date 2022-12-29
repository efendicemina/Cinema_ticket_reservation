package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exception.MovieException;

import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

public class MovieDaoSQLImpl extends AbstractDao<Movie> implements MovieDao{
    public MovieDaoSQLImpl(){
      super("movies");
    }

    @Override
    public Movie row2object(ResultSet rs) throws MovieException {
        try {
            Movie movie = new Movie();
            movie.setId(rs.getInt("movie_id"));
            movie.setName(rs.getString("name"));
            movie.setGenre(rs.getString("genre"));
            movie.setDate_time(rs.getTimestamp("date_time").toLocalDateTime());
            movie.setDuration(rs.getInt("duration"));
            return movie;
        } catch (Exception e) {
            throw new MovieException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Movie object) throws MovieException {
        try {
            Map<String, Object> item = new TreeMap<>();
            item.put("movie_id", object.getId());
            item.put("name", object.getName());
            item.put("genre", object.getGenre());
            item.put("date_time", object.getDate_time());
            item.put("duration", object.getDuration());
            return item;
        } catch (Exception e) {
            throw new MovieException(e.getMessage(), e);
        }
    }
}
