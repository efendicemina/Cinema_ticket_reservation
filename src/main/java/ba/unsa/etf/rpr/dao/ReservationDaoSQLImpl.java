package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exception.MovieException;
import java.sql.*;
import java.util.*;
/**
 * MySQL Implementation of the DAO
 * @author Emina Efendic
 */
public class ReservationDaoSQLImpl extends AbstractDao<Reservation> implements ReservationDao{

    private static ReservationDaoSQLImpl instance = null;

    /**
     * Private constructor for the ReservationDaoSQLImpl class.
     * This constructor initializes the parent class  with the table name.
     */
    private ReservationDaoSQLImpl() {
        super("reservations");
    }

    /**
     * @author Emina Efendic
     * @return ReservationDaoSQLImpl
     * We don't need more than one object for CRUD operations on table 'reservations' so we will use Singleton
     * This method will call private constructor in instance==null and then return that instance
     */
    public static ReservationDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new ReservationDaoSQLImpl();
        return instance;
    }
    /**
     * Removes the singleton instance of the ReservationDaoSQLImpl class.
     */
    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    /**
     *Maps a row from the result set to a Reservation object
     *@param rs The result set from the database query
     *@return A Reservation object with properties set according to the values in the result set
     *@throws MovieException if there is an error when retrieving values from the result set
     */
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
    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
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
