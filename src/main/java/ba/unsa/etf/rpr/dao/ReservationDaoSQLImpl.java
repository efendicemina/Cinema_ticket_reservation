package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exception.MovieException;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class ReservationDaoSQLImpl extends AbstractDao<Reservation> implements ReservationDao{

    private static ReservationDaoSQLImpl instance = null;
    private ReservationDaoSQLImpl() {
        super("reservations");
    }

    /**
     * @author Emina Efendic
     * @return QuoteDaoSQLImpl
     * We don't need more than one object for CRUD operations on table 'quotes' so we will use Singleton
     * This method will call private constructor in instance==null and then return that instance
     */
    public static ReservationDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new ReservationDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }


    @Override
    public Reservation row2object(ResultSet rs) throws MovieException {
        try {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getInt("id"));
            reservation.setUser(DaoFactory.userDao().getById(rs.getInt("user_id")));
            reservation.setMovie(DaoFactory.movieDao().getById(rs.getInt("movie_id")));
            reservation.setSector(rs.getString("sector"));
            return reservation;
        } catch (Exception e) {
            throw new MovieException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Reservation object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("id", object.getId());
        item.put("user_id", object.getUser().getId());
        item.put("movie_id", object.getMovie().getId());
        item.put("sector", object.getSector());
        return item;
    }
}
