package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.User;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MovieDaoSQLImpl implements MovieDao{
   private Connection connection;
    public MovieDaoSQLImpl(){
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
    public Movie getById(int id) {
        String query = "SELECT * FROM movies WHERE movie_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Movie movie = new Movie();
                movie.setId(rs.getInt("movie_id"));
                movie.setName(rs.getString("name"));
                movie.setGenre(rs.getString("genre"));
                movie.setDate_time(rs.getTimestamp("date_time"));
                movie.setDuration(rs.getInt("duration"));
                movie.setR_price(rs.getInt("r_price"));
                movie.setL_price(rs.getInt("l_price"));
                movie.setP_price(rs.getInt("p_price"));
                movie.setR_capacity(rs.getInt("r_duration"));
                movie.setL_capacity(rs.getInt("l_duration"));
                movie.setP_capacity(rs.getInt("p_duration"));
                rs.close();
                return movie;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    @Override
    public Movie add(Movie item) {
        String insert = "INSERT INTO movies(name,genre,date_time,duration,r_price,l_price,p_price" +
                ",r_capacity,l_capacity, p_capacity) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getGenre());
            stmt.setTimestamp(3, item.getDate_time());
            stmt.setInt(4, item.getDuration());
            stmt.setInt(5, item.getR_price());
            stmt.setInt(6, item.getL_price());
            stmt.setInt(7, item.getP_price());
            stmt.setInt(8, item.getR_capacity());
            stmt.setInt(9, item.getL_capacity());
            stmt.setInt(10, item.getP_capacity());
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
    public Movie update(Movie item) {
        String insert = "UPDATE movies SET name = ?,genre=?,date_time=?,duration=?,r_price=?,l_price=?," +
                "p_price=?, r_capacity=?, l_capacity=?, p_capacity=? " +
                "WHERE movie_id=? ";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getName());
            stmt.setObject(2, item.getGenre());
            stmt.setObject(3, item.getDate_time());
            stmt.setObject(4, item.getDuration());
            stmt.setObject(5, item.getR_price());
            stmt.setObject(6, item.getL_price());
            stmt.setObject(7, item.getP_price());
            stmt.setObject(8, item.getR_capacity());
            stmt.setObject(9, item.getL_capacity());
            stmt.setObject(10, item.getP_capacity());
            stmt.setObject(11, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM movies WHERE movie_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Movie> getAll() {
        String query = "SELECT * FROM movies";
        List<Movie> movies= new ArrayList<>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Movie movie = new Movie();
                movie.setId(rs.getInt("movie_id"));
                movie.setName(rs.getString("name"));
                movie.setGenre(rs.getString("genre"));
                movie.setDate_time(rs.getTimestamp("date_time"));
                movie.setDuration(rs.getInt("duration"));
                movie.setR_price(rs.getInt("r_price"));
                movie.setL_price(rs.getInt("l_price"));
                movie.setP_price(rs.getInt("p_price"));
                movie.setR_capacity(rs.getInt("r_capacity"));
                movie.setL_capacity(rs.getInt("l_capacity"));
                movie.setP_capacity(rs.getInt("p_capacity"));
                movies.add(movie);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return movies;
    }
}
