package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import javafx.scene.control.Label;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDaoSQLImpl implements UserDao{
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public UserDaoSQLImpl(){
        try {
            FileReader reader = new FileReader("src//main//resources//db.properties");
            Properties p = new Properties();
            p.load(reader);
            String s1=p.getProperty("user");
            String s2=p.getProperty("password");
            String s3=p.getProperty("url");
            this.connection = DriverManager.getConnection(s3,s1, s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public User getById(int id) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setAdmin(rs.getBoolean("admin"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                rs.close();
                return user;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }
    @Override
    public User add(User item) {
        String insert = "INSERT INTO users(name, phone,email,admin,username,password) VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getPhone());
            stmt.setString(3, item.getEmail());
            stmt.setBoolean(4, item.isAdmin());
            stmt.setString(5, item.getUsername());
            stmt.setString(6, item.getPassword());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(User item) {
        String insert = "UPDATE users SET name = ?,phone=?,email=?,admin=?,username=?,password=? " +
                "WHERE user_id=? ";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getName());
            stmt.setObject(2, item.getPhone());
            stmt.setObject(3, item.getEmail());
            stmt.setObject(4, item.isAdmin());
            stmt.setObject(5, item.getUsername());
            stmt.setObject(6, item.getPassword());
            stmt.setObject(7, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void delete(int id) {
        String insert = "DELETE FROM users WHERE user_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users";
        List<User> users= new ArrayList<>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setAdmin(rs.getBoolean("admin"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return users;
    }
    public boolean validateEmail(String emailField){
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailField);
        return matcher.matches();
    }
    public boolean validateUsername(String usernameField){
        String insert = "SELECT count(username) from users where username='" + usernameField +"'";
        try {
            UserDaoSQLImpl u = new UserDaoSQLImpl();
            PreparedStatement stmt = u.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) { // result set is iterator.
                if (rs.getInt(1) != 0) {
                    return false;
                } else {
                    return true;
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean validateLogin(String usernameTextField, String passwordField) {
        String insert = "SELECT count(1) from users where username='" + usernameTextField + "' AND password='"
                + passwordField + "'";
        try {
            UserDaoSQLImpl u = new UserDaoSQLImpl();
            PreparedStatement stmt = u.getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { // result set is iterator.
                if (rs.getInt(1) == 1) {
                    return true;
                } else {
                    return false;
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
