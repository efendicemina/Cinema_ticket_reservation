package ba.unsa.etf.rpr.dao;

import java.sql.Connection;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Emina Efendic
 */
public class DaoFactory {

    private static final MovieDao movieDao = new MovieDaoSQLImpl();
    private static final UserDao userDao = new UserDaoSQLImpl();
    private static final ReservationDao reservationDao = new ReservationDaoSQLImpl();


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
