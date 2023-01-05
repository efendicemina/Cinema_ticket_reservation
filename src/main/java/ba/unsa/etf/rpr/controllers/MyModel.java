package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.User;

public class MyModel {
    private static MyModel instance;

    private User user;
    private Movie movie;

    private MyModel() {}

    public static MyModel getInstance() {
        if (instance == null) {
            instance = new MyModel();
        }
        return instance;
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
}

