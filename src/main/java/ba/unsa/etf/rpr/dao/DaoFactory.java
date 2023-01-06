package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Emina Efendic
 */
public class DaoFactory {

    private static final MovieDao movieDao = MovieDaoSQLImpl.getInstance();
    private static final UserDao userDao = UserDaoSQLImpl.getInstance();
    private static final ReservationDao reservationDao = ReservationDaoSQLImpl.getInstance();


    private DaoFactory(){
    }

    public static MovieDao movieDao(){
        return movieDao;
    }

    public static UserDao userDao(){
        return userDao;
    }

    public static ReservationDao reservationDao(){
        return reservationDao;
    }

}
