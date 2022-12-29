package ba.unsa.etf.rpr.domain;

import java.text.SimpleDateFormat;
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
    private Integer duration;


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

    public String getDate_time() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/mm/yyyy HH:mm");
       return simpleDateFormat.format(date_time);
    }
    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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
        return Objects.hash(id, name,genre, date_time, duration);
    }
}
