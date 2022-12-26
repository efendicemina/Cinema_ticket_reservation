package ba.unsa.etf.rpr.domain;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Class that contains information about movie tickets available for purchase.
 * @author Emina Efendic
 */
public class Movie implements Idable{
    private int id;
    private String name;
    private String genre;
    private Timestamp date_time;
    private int duration;
    private int r_price;
    private int l_price;
    private int p_price;
    private int r_capacity;
    private int l_capacity;
    private int p_capacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getR_price() {
        return r_price;
    }

    public void setR_price(int r_price) {
        this.r_price = r_price;
    }

    public int getL_price() {
        return l_price;
    }

    public void setL_price(int l_price) {
        this.l_price = l_price;
    }

    public int getP_price() {
        return p_price;
    }

    public void setP_price(int p_price) {
        this.p_price = p_price;
    }

    public int getR_capacity() {
        return r_capacity;
    }

    public void setR_capacity(int r_capacity) {
        this.r_capacity = r_capacity;
    }

    public int getL_capacity() {
        return l_capacity;
    }

    public void setL_capacity(int l_capacity) {
        this.l_capacity = l_capacity;
    }

    public int getP_capacity() {
        return p_capacity;
    }

    public void setP_capacity(int p_capacity) {
        this.p_capacity = p_capacity;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name,genre, date_time, duration, r_price, l_price, p_price, r_capacity, l_capacity, p_capacity);
    }
}
