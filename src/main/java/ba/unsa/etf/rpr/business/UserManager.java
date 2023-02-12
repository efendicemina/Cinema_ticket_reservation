package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.MovieException;

import java.util.List;

/**
 * This is a Java class called "UserManager" that provides a set of methods for managing User objects.
 * The class uses the DaoFactory to access the User DAO (Data Access Object) and perform CRUD (Create, Read, Update, Delete) operations on User objects.
 * It also throws a MovieException in case of an error.
 * @author Emina Efendic
 */
public class UserManager {
    /**
     * Deletes user, from db table users, with a given id.
     * @param userId int
     * @throws MovieException thrown in case of problem with db
     */
    public void delete(int userId) throws MovieException {
        try{
            DaoFactory.userDao().delete(userId);
        }catch (MovieException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new MovieException("NO");
            }
            throw e;
        }

    }
    /**
     * Adds User object to table users.
     * @param u User
     * @return added User
     * @throws MovieException in case of problems with db
     */
    public User add(User u) throws MovieException{
        return DaoFactory.userDao().add(u);
    }
    /**
     * Fetches User object form table users defined by given id.
     * @param id int
     * @return User object defined by given id
     * @throws MovieException in case of problems with db
     */
    public User getById(Integer id) throws MovieException {
        return DaoFactory.userDao().getById(id);
    }
    /**
     * Updates user in db table users.
     * @param cat User
     * @return User that is updated
     * @throws MovieException thrown in case of problem with db
     */
    public User update(User cat) throws MovieException{
        return DaoFactory.userDao().update(cat);
    }
    /**
     *Fetches all User objects from table users and stores it in a list.
     * @return List of all users
     * @throws MovieException thrown in case of problem with db
     */
    public List<User> getAll() throws MovieException{
        return DaoFactory.userDao().getAll();
    }

    /**
     * Checks if username and password matches the ones in users table.
     * @param usernameTextField String
     * @param passwordField String
     * @return Integer id if it matches, null if not
     * @throws MovieException thrown in case of problem with db
     */
    public Integer checkUsernamePassword(String usernameTextField, String passwordField) throws MovieException{
        return DaoFactory.userDao().checkUsernamePassword(usernameTextField,passwordField);
    }

    /**
     * Checks if admin is logging in.
     * @param usernameField String
     * @return boolean
     * @throws MovieException thrown in case of problem with db
     */
    public boolean isAdmin(String usernameField) throws MovieException{
        return DaoFactory.userDao().isAdmin(usernameField);
    }

    /**
     * Finds given username in users table if it already exists.
     * @param usernameField String
     * @return boolean
     * @throws MovieException thrown in case of problem with db
     */
    public boolean findUsername(String usernameField) throws MovieException{
        return DaoFactory.userDao().findUsername(usernameField);
    }
}
