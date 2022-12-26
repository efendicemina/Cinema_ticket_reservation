package ba.unsa.etf.rpr.domain;

import java.util.Objects;

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation reservation = (Reservation) o;
        return id == reservation.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, movie, sector, tickets);
    }
}
