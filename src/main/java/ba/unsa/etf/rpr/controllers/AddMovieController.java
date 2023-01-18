package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.domain.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 *
 AddMovieController class is responsible for adding new movies to the system.
 It provides functionality for creating new movie object by providing its name, genre, date and time of showing, and duration of the movie.
 It also performs validation of user input and displays error messages in case of invalid input.
 @author Emina Efendic
 */
public class AddMovieController {

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
    DatePicker dateField;
    @FXML
    Label addMovieMessage;
    MovieManager movieManager = new MovieManager();
    private final Integer[] hour = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
    private final Integer[] min = {0, 10, 20, 30, 40, 50};
    private final Integer[] duration = {60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220};

    /**
     * Initializes the hourBox, minBox, and durationBox with predefined values for hours, minutes, and duration of the movie.
     */
    @FXML
    public void initialize() {
        hourBox.getItems().addAll(hour);
        minBox.getItems().addAll(min);
        durationBox.getItems().addAll(duration);
    }
    /**
     * Handles the 'OK' button click event, creates a new movie object using user input, adds it to the system and displays a confirmation dialog.
     * In case of invalid input, it displays an error message.
     *
     */
    public void okButtonOnAction() {
        try {
            movieManager.validateAddFields(nameField.getText(), genreField.getText(), dateField.toString(), durationBox.getValue(),
                    minBox.getValue(), hourBox.getValue());
            AdminController admin = new AdminController();
            LocalDate localDate = dateField.getValue();
            LocalDateTime localDateTime = localDate.atTime(hourBox.getValue(), minBox.getValue());
            Movie movie = admin.createMovie(nameField.getText(), genreField.getText(), localDateTime, durationBox.getValue());
            movieManager.add(movie);
            openDialog();
            Stage stage=(Stage) hourBox.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * Opens a dialog window with the provided title and FXML file path
     */
    private void openDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/information.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setTitle("Information");
        stage.initStyle(StageStyle.UTILITY);
        stage.show();

    }
}