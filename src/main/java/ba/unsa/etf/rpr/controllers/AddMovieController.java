package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exception.MovieException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.scene.layout.Pane;


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
    MovieManager movieManager=new MovieManager();
    @FXML
    public Pane aouMoviePane;
    private Integer[] hour = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
    private Integer[] min = {00, 10, 20, 30, 40, 50};
    private Integer[] duration = {60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220};
    @FXML
    public void initialize(){
        hourBox.getItems().addAll(hour);
        minBox.getItems().addAll(min);
        durationBox.getItems().addAll(duration);
    }
    public void okButtonOnAction(ActionEvent actionEvent) throws MovieException {
        try {
            movieManager.validateAddFields(nameField.getText(), genreField.getText(), dateField.toString(), durationBox.getValue(),
                    minBox.getValue(), hourBox.getValue());
            AdminController admin = new AdminController();
            LocalDate localDate = dateField.getValue();
            LocalDateTime localDateTime = localDate.atTime(hourBox.getValue(), minBox.getValue());
            Movie movie = admin.createMovie(nameField.getText(), genreField.getText(), localDateTime, durationBox.getValue());
            movieManager.add(movie);
            addMovieMessage.setText("Movie successfully added.");
      }catch (Exception e){
        new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
     }
    }
}