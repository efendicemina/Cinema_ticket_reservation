package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.controllers.MyModel;
import ba.unsa.etf.rpr.controllers.RegisterController;
import ba.unsa.etf.rpr.dao.UserDao;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.MovieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for Application, testing some specific situations, using mocking in last two tests.
 * Mocking is used, so we do not have to interact with the real database.
 * @author Emina Efendic
 */
public class AppTest {
    /**
     * Test that the getInstance method returns the single instance of the class
     */
    @Test
    public void getInstanceTest() {
        MyModel model1 = MyModel.getInstance();
        MyModel model2 = MyModel.getInstance();
        assertSame(model1, model2);
    }

    /**
     * Test the setUser and getUser methods
     */
    @Test
    public void setAndGetUserTest() {
        MyModel model = MyModel.getInstance();
        User user = new User();
        model.setUser(user);
        assertSame(user, model.getUser());
    }

    /**
     * Test the setMovie and getMovie methods
     */
    @Test
    public void setAndGetMovieTest() {
        MyModel model = MyModel.getInstance();
        Movie movie = new Movie();
        model.setMovie(movie);
        assertSame(movie, model.getMovie());
    }

    /**
     * Tests if email are valid, all emails in this test are valid.
     */
    @Test
    public void testValidEmails() {
        String[] validEmails = {
                "eefendic1@etf.unsa.ba",
                "tselimovic2@etf.unsa.ba",
                "hmahmutovi3@etf.unsa.ba",
                "nadilovic2@etf.unsa.ba",
                "dkrslak1@etf.unsa.ba",
        };

        for (String email : validEmails) {
            assertTrue((new RegisterController()).checkEmail(email));

        }
    }
    /**
     * Tests if email are invalid, all emails in this test are invalid.
     */
    @Test
    public void testInvalidEmails() {
        String[] invalidEmails = {
                "eefendic1.com",
                "tajra.com",
                "mahmutovic@.",
                "nejra@com",
                "dkrslak1@example..com",
        };

        for (String email : invalidEmails) {
            assertFalse( (new RegisterController()).checkEmail(email));
        }
    }

    /**
     * Test validation of add fields in the app. In this case fields are not properly filled in.
     */
    @Test
    public void validateAddFieldsTest() {
        try {
            new MovieManager().validateAddFields("","", "",null,null, null);
            fail("Problem with validateAddFields method.");
        } catch (MovieException e) {
            assertTrue(e.getMessage().contains("To successfully perform an action, all fields must be filled in!"));
        }
    }
    /**
     * Test validation of delete fields in the app. In this case fields are not properly filled in.
     */
    @Test
    public void validateDeleteFieldsTest() {
        try {
            new MovieManager().validateDeleteFields(null);
            fail("Problem with validateDeleteFields method.");
        } catch (MovieException e) {
            assertTrue(e.getMessage().contains("To successfully perform an action, id field must be filled in!"));
        }
    }
    /**
     * Test validation of seat fields in the app. In this case fields are properly filled in.
     */
    @Test
    public void validateSeatFieldTest() {
        try {
            ReservationManager reservationManager = new ReservationManager();
            reservationManager.validateSeatField("B7");
        } catch (MovieException e) {
            fail("Problem with validateSeatField method.");
        }
    }

    @Mock
    private UserDao userDao;

    public User user = new User();

    /**
     * Set up for tests using mocking.
     */
    @BeforeEach
    public void setUp() {
        user.setId(1);
        user.setUsername("eefendic1");
        user.setPhone("22345786");
        user.setAdmin(true);
        user.setPassword("nekipass");
        user.setEmail("eefendic1@etf.unsa.ba");
        user.setName("Emina Efendic");
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test if add option in the app is working correctly.
     * @throws MovieException in case of problems
     */
    @Test
    public void addTest() throws  MovieException {
        userDao.add(user);
        verify(userDao).add(user);
    }

    /**
     * Test if update option in the app is working correctly.
     * @throws Exception in case of problems
     */
    @Test
    void updateTest() throws Exception {
        user.setPassword("emina123");
        userDao.update(user);
        verify(userDao).update(user);
    }
}
