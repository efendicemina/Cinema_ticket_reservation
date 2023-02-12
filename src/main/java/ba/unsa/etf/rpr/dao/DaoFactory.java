package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 * @author Emina Efendic
 */
public class DaoFactory {

    private static final MovieDao movieDao = MovieDaoSQLImpl.getInstance();
    private static final UserDao userDao = UserDaoSQLImpl.getInstance();
    private static final ReservationDao reservationDao = ReservationDaoSQLImpl.getInstance();

    /**
     *A private constructor to prevent instantiation of this class.
     */
    private DaoFactory(){
    }

    /**
     * A singleton instance of the MovieDao class, which is used to access and manipulate movie data in a database.
     * @return movieDao MovieDao
     */
    public static MovieDao movieDao(){
        return movieDao;
    }
    /**
     * A singleton instance of the UserDao class, which is used to access and manipulate user data in a database.
     * @return userDao UserDao
     */
    public static UserDao userDao(){
        return userDao;
    }
    /**
     * A singleton instance of the ReservationDao class, which is used to access and manipulate reservation data in a database.
     * @return reservationDao ReservationDao
     */
    public static ReservationDao reservationDao(){
        return reservationDao;
    }

}
