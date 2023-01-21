package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.User;

/**
 * Helper class called MyModel that contains information about user and movie currently needed for making a reservation.
 * @author Emina Efendic
 */
public class MyModel {
    private static MyModel instance;

    private User user;
    private Movie movie;
    /**
     *Private constructor to prevent instantiation of the class.
     */
    private MyModel() {}
    /**
     *Returns the single instance of the MyModel class. If the instance does not exist, it is created.
     *@return The single instance of the MyModel class.
     */
    public static MyModel getInstance() {
        if (instance == null) {
            instance = new MyModel();
        }
        return instance;
    }
    /**
     *Returns the current user information.
     *@return The current user information.
     */
    public User getUser() {
        return user;
    }
    /**
     *Sets the current user information.
     *@param user The user information to be set.
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     *Returns the current movie information.
     *@return The current movie information.
     */
    public Movie getMovie() {
        return movie;
    }
    /**
     *Sets the current movie information.
     *@param movie The movie information to be set.
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}

