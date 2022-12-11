package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Reservation;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ReservationDaoSQLImpl implements ReservationDao{
    private Connection connection;
    public ReservationDaoSQLImpl(){
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
    public Reservation getById(int id) {
        String query = "SELECT * FROM reservations WHERE reservation_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt(1));
                reservation.setUser(new UserDaoSQLImpl().getById(rs.getInt(2)));
                reservation.setMovie(new MovieDaoSQLImpl().getById(rs.getInt(3)));
                reservation.setSector(rs.getString(4));
                reservation.setTickets(rs.getInt(5));
                rs.close();
                return reservation;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    @Override
    public Reservation add(Reservation item) {
        String insert = "INSERT INTO reservations(user_id,movie_id,sector,tickets)" +
                " VALUES(?,?,?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, item.getUser().getId());
            stmt.setInt(2, item.getMovie().getId());
            stmt.setString(3, item.getSector());
            stmt.setInt(4, item.getTickets());
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
    public Reservation update(Reservation item) {
        String insert = "UPDATE reservations SET user_id= ?,movie_id=?,sector=?,tickets=?" +
                "WHERE reservation_id=? ";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getUser().getId());
            stmt.setObject(2, item.getMovie().getId());
            stmt.setObject(3, item.getSector());
            stmt.setObject(4, item.getTickets());
            stmt.setObject(5, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        try{
            PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM reservations WHERE reservation_id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM reservations");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("reservation_id"));
                reservation.setUser(new UserDaoSQLImpl().getById(rs.getInt("user_id")));
                reservation.setMovie(new MovieDaoSQLImpl().getById(rs.getInt("movie_id")));
                reservation.setSector(rs.getString("sector"));
                reservation.setTickets(rs.getInt("tickets"));
                reservations.add(reservation);
            }
            rs.close();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return reservations;

    }

}
