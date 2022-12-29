package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exception.MovieException;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class ReservationDaoSQLImpl extends AbstractDao<Reservation> implements ReservationDao{

    public ReservationDaoSQLImpl(){
        super("reservations");
    }

    @Override
    public Reservation row2object(ResultSet rs) throws MovieException {
        try {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getInt("id"));
            reservation.setUser(DaoFactory.userDao().getById(rs.getInt("user_id")));
            reservation.setMovie(DaoFactory.movieDao().getById(rs.getInt("movie_id")));
            reservation.setSector(rs.getString("sector"));
            reservation.setTickets(rs.getInt("tickets"));
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
        item.put("tickets", object.getTickets());
        return item;
    }
}
