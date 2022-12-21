package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.MovieException;

/**
 * Dao interface for User domain bean
 *
 * @author Emina Efendic
 */
public interface UserDao extends Dao<User> {
    public boolean checkUsernamePassword(String usernameTextField, String passwordField) throws MovieException;
    public boolean findUsername(String usernameField) throws MovieException;
    public boolean isAdmin(String usernameField) throws MovieException;
}
