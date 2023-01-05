package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.MovieException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookController {
    ReservationManager reservationManager=new ReservationManager();
    @FXML
    private ChoiceBox<String> seatBox;
    @FXML
    private Label nameLabel;
    @FXML
    private Label genreLabel;
    @FXML
    private Label durationLabel;
    private String[] seats={"A1","A2","A3","A4","A5","A6","A7","A8","A9","A10"
    ,"B1","B2","B3","B4","B5","B6","B7","B8","B9","B10"
            ,"C1","C2","C3","C4","C5","C6","C7","C8","C9","C10"
    ,"D1","D2","D3","D4","D5","D6","D7","D8","D9","D10"};
    @FXML
    public void initialize() throws MovieException {
        MyModel model = MyModel.getInstance();
        Movie movie = model.getMovie();
        nameLabel.setText(movie.getName());
        genreLabel.setText(movie.getGenre());
        durationLabel.setText(movie.getDuration().toString());
     List<String> booked= reservationManager.getBookedSeats(movie.getId());
     List<String> avalible=new ArrayList<>();
     for(int i=0; i< seats.length; i++){
         if(!booked.contains(seats[i])) avalible.add(seats[i]);
     }
     seatBox.getItems().addAll(avalible);
    }
    public void cancelButtonOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage=(Stage) seatBox.getScene().getWindow();
        stage.close();
    }

    public void bookingButtonOnAction(ActionEvent actionEvent) throws MovieException {
        try {
            reservationManager.validateSeatField(seatBox.getValue());
            Reservation reservation = new Reservation();
            MyModel model = MyModel.getInstance();
            User user = model.getUser();
            Movie movie = model.getMovie();
            reservation.setMovie(movie);
            reservation.setSector(seatBox.getValue());
            reservation.setUser(user);
            reservationManager.add(reservation);
        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
