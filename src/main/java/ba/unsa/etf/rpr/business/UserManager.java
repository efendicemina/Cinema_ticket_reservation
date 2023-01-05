package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.MovieException;

import java.util.List;

public class UserManager {
    public void delete(int categoryId) throws MovieException {
        try{
            DaoFactory.userDao().delete(categoryId);
        }catch (MovieException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new MovieException("NO");
            }
            throw e;
        }

    }
    public User getById(Integer id) throws MovieException {
        return DaoFactory.userDao().getById(id);
    }
    public User update(User cat) throws MovieException{
        return DaoFactory.userDao().update(cat);
    }

    public List<User> getAll() throws MovieException{
        return DaoFactory.userDao().getAll();
    }
    public Integer checkUsernamePassword(String usernameTextField, String passwordField) throws MovieException{
        return DaoFactory.userDao().checkUsernamePassword(usernameTextField,passwordField);
    }
    public boolean isAdmin(String usernameField) throws MovieException{
        return DaoFactory.userDao().isAdmin(usernameField);
    }
    public boolean findUsername(String usernameField) throws MovieException{
        return DaoFactory.userDao().findUsername(usernameField);
    }
}
