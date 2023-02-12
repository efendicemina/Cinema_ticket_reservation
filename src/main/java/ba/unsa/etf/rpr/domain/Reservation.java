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

    /**
     * Gets id
     * @return id int
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets user
     * @return user User
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user
     * @param user User
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets movie
     * @return Movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Sets movie
     * @param movie Movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Gets sector
     * @return sector String
     */
    public String getSector() {
        return sector;
    }

    /**
     * Sets sector
     * @param sector String
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
     * Checks if objects are equal
     * @param o Object
     * @return boolean, true if they are equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation reservation = (Reservation) o;
        return id == reservation.id;
    }
    /**
     * Gives hash code of the object
     * @return int hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, user, movie, sector);
    }
}
