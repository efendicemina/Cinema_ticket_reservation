package ba.unsa.etf.rpr.domain;
/**
 * Class that contains information about reservations made for movie tickets.
 * @author Emina Efendic
 */
public class Reservation implements Idable{
    private int id;
    private User user;
    private Movie movie;
    private String sector;
    private int tickets;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }
}
