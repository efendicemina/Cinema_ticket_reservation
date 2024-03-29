package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exception.MovieException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *UpdateMovieController class is responsible for updating existing movies.
 *It provides functionality for updating existing movie object by providing its id, name, genre, date and time of showing, and duration of the movie.
 *@author Emina Efendic
 */
public class UpdateMovieController {
    @FXML
    private ChoiceBox<Integer> idBox;
    private final List<Integer> ids= new ArrayList<>() ;
    private final MovieManager movieManager=new MovieManager();
    @FXML
    private ChoiceBox<Integer> hourBox;
    @FXML
    private ChoiceBox<Integer> minBox;
    @FXML
    private ChoiceBox<Integer> durationBox;
    @FXML
    private TextField nameField;
    @FXML
    private TextField genreField;
    @FXML
    DatePicker dateBox;


    private final Integer[] hour = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
    private final Integer[] min = {0, 10, 20, 30, 40, 50};
    private final Integer[] duration = {60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220};
    /**
     *The initialize method is called when the controller is created.
     *It sets up the UI elements such as populating the choice boxes with data and adding listeners to certain elements.
     *Specifically it populates the movie id choice box with all available movie ids and when user select a movie id, it shows the details of that movie
     *@throws MovieException when there is any problem with getting the list of movies from the system.
     */
    @FXML
    public void initialize() throws MovieException {
        dateBox.setEditable(false);
        List<Movie> list=movieManager.getAll();
        for (Movie movie : list) {
            ids.add(movie.getId());
        }
        idBox.getItems().addAll(ids);
        hourBox.getItems().addAll(hour);
        minBox.getItems().addAll(min);
        durationBox.getItems().addAll(duration);
        idBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Get the selected movie's information
            Movie movie;
            try {
                movie = movieManager.getById(newValue);
                // Populate the text boxes and choice boxes with the movie's information
                nameField.setText(movie.getName());
                genreField.setText(movie.getGenre());
                Timestamp timestamp = Timestamp.valueOf(movie.getDate_time());
                LocalDate localDate = timestamp.toLocalDateTime().toLocalDate();
                dateBox.setValue(localDate);
                durationBox.setValue(movie.getDuration());
                hourBox.setValue(timestamp.toLocalDateTime().getHour());
                minBox.setValue(timestamp.toLocalDateTime().getMinute());

            } catch (MovieException e) {
                throw new RuntimeException(e);
            }
        });
    }
    /**
     *The updateButtonOnAction method updates a movie with new information.
     *It first validates the input fields, creates a new movie object with the new information,
     *sets its id to the selected id, and updates the movie in the system.
     *If there is any problem with the input fields or updating the movie, an error message will be shown.
     */
    public void updateButtonOnAction() {
        try {
            movieManager.validateAddFields(nameField.getText(), genreField.getText(),
                    dateBox.toString(), hourBox.getValue(), minBox.getValue(), durationBox.getValue());
            movieManager.validateDeleteFields(idBox.getValue());
            AdminController admin = new AdminController();
            LocalDate localDate = dateBox.getValue();
            LocalDateTime localDateTime = localDate.atTime(hourBox.getValue(), minBox.getValue());
            Movie movie = admin.createMovie(nameField.getText(), genreField.getText(), localDateTime, durationBox.getValue());
            movie.setId(idBox.getValue());
            movieManager.update(movie);
            new Alert(Alert.AlertType.NONE, "Action successful", ButtonType.OK).show();
            Stage stage=(Stage) idBox.getScene().getWindow();
            stage.close();
        } catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
