package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.MovieException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller for booking tickets.
 * It allows users to choose ticket they want and to book it very easily.
 * @author Emina Efendic
 */
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
    @FXML
    private Label soldOut;
    private final String[] seats={"A1","A2","A3","A4","A5","A6","A7","A8","A9","A10"
    ,"B1","B2","B3","B4","B5","B6","B7","B8","B9","B10"
            ,"C1","C2","C3","C4","C5","C6","C7","C8","C9","C10"
    ,"D1","D2","D3","D4","D5","D6","D7","D8","D9","D10"};

    /**
     * Initializes the MyModel class and ChoiceBox where all available seats are displayed.
     */
    @FXML
    public void initialize() throws MovieException {
        MyModel model = MyModel.getInstance();
        Movie movie = model.getMovie();
        nameLabel.setText(movie.getName());
        genreLabel.setText(movie.getGenre());
        durationLabel.setText(movie.getDuration().toString());
     List<String> booked= reservationManager.getBookedSeats(movie.getId());
     List<String> available=new ArrayList<>();
        for (String seat : seats) {
            if (!booked.contains(seat)) available.add(seat);
        }
        if(available.isEmpty()) soldOut.setText("SOLD OUT!");
     seatBox.getItems().addAll(available);
    }
    /**
     * Action defined for cancel button. It takes you back to user panel.
     */
    public void cancelButtonOnAction() {
        Stage stage=(Stage) seatBox.getScene().getWindow();
        stage.close();
    }
    /**
     * Action defined for book button.
     * It checks if the reservation can be made, if so it adds the reservation to database.
     * On the other hand if the reservation cannot be made it displays an alert.
     */
    public void bookingButtonOnAction() {
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
            new Alert(Alert.AlertType.NONE, "Action successful", ButtonType.OK).show();
            Stage stage=(Stage) nameLabel.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

}
