package ba.unsa.etf.rpr;


import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.controllers.DeleteMovieController;
import ba.unsa.etf.rpr.controllers.MyModel;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.MovieException;
import javafx.scene.control.ChoiceBox;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for simple App.
 * @author Emina Efendic
 */
public class AppTest 
{
    @Test
    public void getInstanceTest() {
        // Test that the getInstance method returns the single instance of the class
        MyModel model1 = MyModel.getInstance();
        MyModel model2 = MyModel.getInstance();
        assertSame(model1, model2);
    }

    @Test
    public void setAndGetUserTest() {
        // Test the setUser and getUser methods
        MyModel model = MyModel.getInstance();
        User user = new User();
        model.setUser(user);
        assertSame(user, model.getUser());
    }

    @Test
    public void setAndGetMovieTest() {
        // Test the setMovie and getMovie methods
        MyModel model = MyModel.getInstance();
        Movie movie = new Movie();
        model.setMovie(movie);
        assertSame(movie, model.getMovie());
    }

}
