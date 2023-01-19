package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.MovieException;

/**
 * Dao interface for User domain bean
 *
 * @author Emina Efendic
 */
public interface UserDao extends Dao<User> {
    /**
     * Checks if username and password matches the ones in users table.
     * @param usernameTextField String
     * @param passwordField String
     * @return Integer id if it matches, null if not
     * @throws MovieException thrown in case of problem with db
     */
    Integer checkUsernamePassword(String usernameTextField, String passwordField) throws MovieException;
    /**
     * Finds given username in users table if it already exists.
     * @param usernameField String
     * @return boolean
     * @throws MovieException thrown in case of problem with db
     */
    boolean findUsername(String usernameField) throws MovieException;
    /**
     * Checks if admin is logging in.
     * @param usernameField String
     * @return boolean
     * @throws MovieException thrown in case of problem with db
     */
    boolean isAdmin(String usernameField) throws MovieException;
}
