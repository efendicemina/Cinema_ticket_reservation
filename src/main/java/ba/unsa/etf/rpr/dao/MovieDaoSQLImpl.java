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
            movie.setDate_time(rs.getTimestamp("date_time"));
            movie.setDuration(rs.getInt("duration"));
            movie.setR_price(rs.getInt("r_price"));
            movie.setL_price(rs.getInt("l_price"));
            movie.setP_price(rs.getInt("p_price"));
            movie.setR_capacity(rs.getInt("r_capacity"));
            movie.setL_capacity(rs.getInt("l_capacity"));
            movie.setP_capacity(rs.getInt("p_capacity"));
            return movie;
        } catch (Exception e) {
            throw new MovieException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Movie object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("movie_id", object.getId());
        item.put("name", object.getName());
        item.put("genre", object.getGenre());
        item.put("datetime", object.getDate_time());
        item.put("duration", object.getDuration());
        item.put("r_price", object.getR_price());
        item.put("l_price", object.getL_price());
        item.put("p_price", object.getP_price());
        item.put("r_capacity", object.getR_capacity());
        item.put("l_capacity", object.getL_capacity());
        item.put("p_capacity", object.getP_capacity());
        return item;
    }
}
