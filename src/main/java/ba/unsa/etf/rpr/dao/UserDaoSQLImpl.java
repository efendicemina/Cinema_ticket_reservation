package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.MovieException;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of the DAO
 * @author Emina Efendic
 */
public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao{

    private static UserDaoSQLImpl instance = null;
    /**
     * Private constructor for the UserDaoSQLImpl class.
     * This constructor initializes the parent class  with the table name.
     */
    private UserDaoSQLImpl() {
        super("users");
    }

    /**
     * @author Emina Efendic
     * @return UserDaoSQLImpl
     * We don't need more than one object for CRUD operations on table 'users' so we will use Singleton
     * This method will call private constructor in instance==null and then return that instance
     */
    public static UserDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new UserDaoSQLImpl();
        return instance;
    }
    /**
     * Removes the singleton instance of the UserDaoSQLImpl class.
     */
    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }
    /**
     *Maps a row from the result set to a Movie object
     *@param rs The result set from the database query
     *@return A Movie object with properties set according to the values in the result set
     *@throws MovieException if there is an error when retrieving values from the result set
     */
    @Override
    public User row2object(ResultSet rs) throws MovieException {
        try {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            user.setAdmin(rs.getBoolean("admin"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        } catch (SQLException e) {
            throw new MovieException(e.getMessage(), e);
        }
    }
    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(User object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("id", object.getId());
        item.put("name", object.getName());
        item.put("phone", object.getPhone());
        item.put("email", object.getEmail());
        item.put("admin", object.isAdmin());
        item.put("username", object.getUsername());
        item.put("password", object.getPassword());
        return item;
    }
    /**
     * Finds given username in users table if it already exists.
     * @param usernameField String
     * @return boolean
     * @throws MovieException thrown in case of problem with db
     */
    @Override
    public boolean findUsername(String usernameField) throws MovieException{
        String insert = "SELECT count(username) from users where username='" + usernameField +"'";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) { // result set is iterator.
                return rs.getInt(1) != 0;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Checks if admin is logging in.
     * @param usernameField String
     * @return boolean
     * @throws MovieException thrown in case of problem with db
     */
    @Override
    public boolean isAdmin(String usernameField) throws MovieException {
        String insert = "SELECT username from users where admin=1";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) { // result set is iterator.
                if(rs.getString(1).equals(usernameField)) return true;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Checks if username and password matches the ones in users table.
     * @param usernameTextField String
     * @param passwordField String
     * @return Integer id if it matches, null if not
     * @throws MovieException thrown in case of problem with db
     */
    @Override
    public Integer checkUsernamePassword(String usernameTextField, String passwordField) throws MovieException{
        String insert = "SELECT id from users where username='" + usernameTextField + "' AND password='"
                + passwordField + "'";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { // result set is iterator.
                return rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
