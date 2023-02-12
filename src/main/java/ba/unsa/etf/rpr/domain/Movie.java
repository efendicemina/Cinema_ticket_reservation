package ba.unsa.etf.rpr.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class that contains information about movie tickets available for booking.
 * @author Emina Efendic
 */
public class Movie implements Idable{
    private int id;
    private String name;
    private String genre;
    private LocalDateTime date_time;
    private Integer duration;

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
     * Gets name
     * @return name String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets genre
     * @return genre String
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets genre
     * @param genre String
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets datetime
     * @return date_time LocaleDateTime
     */
    public LocalDateTime getDate_time() {
        return date_time;
    }

    /**
     * Sets datetime
     * @param date_time LocaleDateTime
     */
    public void setDate_time(LocalDateTime date_time) {
        this.date_time = date_time;
    }

    /**
     * Gets duration
     * @return duration Integer
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Sets duration
     * @param duration Integer
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
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
        Movie movie = (Movie) o;
        return id == movie.id;
    }

    /**
     * Gives hash code of the object
     * @return int hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name,genre, date_time, duration);
    }

    /**
     * Converts object to a String
     * @return name String
     */
    @Override
    public String toString() {
        return name;
    }
}
