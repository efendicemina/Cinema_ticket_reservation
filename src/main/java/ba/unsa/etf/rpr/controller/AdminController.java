package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.dao.MovieDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.MovieException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ba.unsa.etf.rpr.business.MovieManager;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AdminController {
    private final MovieManager movieManager = new MovieManager();
    private final UserManager userManager = new UserManager();
    private final ReservationManager reservationManager = new ReservationManager();
    @FXML
    public TableView<Movie> movieTable;
    @FXML
    public TableColumn<Movie, String> midColumn;
    @FXML
    public TableColumn<Movie, String> mnameColumn;
    @FXML
    public TableColumn<Movie, String> genreColumn;
    @FXML
    public TableColumn<Movie, Timestamp> dateColumn;
    @FXML
    public TableColumn<Movie, String> durationColumn;

    @FXML
    public TableView<User> userTable;
    @FXML
    public TableColumn<User, String> uidColumn;
    @FXML
    public TableColumn<User, String> unameColumn;
    @FXML
    public TableColumn<User, String> emailColumn;
    @FXML
    public TableColumn<User, String> usernameColumn;
    @FXML
    public TableColumn<User, String> passwordColumn;
    @FXML
    public TableView<Reservation> reservationTable;
    @FXML
    public TableColumn<Reservation, String> ridColumn;
    @FXML
    public TableColumn<Reservation, String> movie_idColumn;
    @FXML
    public TableColumn<Reservation, String> user_idColumn;
    @FXML
    public TableColumn<Reservation, String> sectorColumn;
    @FXML
    public TableColumn<Reservation, String> ticketColumn;

    @FXML
    public void initialize() {
        midColumn.setCellValueFactory(new PropertyValueFactory<Movie,String>("id"));
        mnameColumn.setCellValueFactory(new PropertyValueFactory<Movie,String>("name"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Movie,String>("genre"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Movie, Timestamp>("date_time"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("duration"));
        refreshMovies();
        uidColumn.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
        unameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        refreshUsers();
        ridColumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("id"));
        movie_idColumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("movie_id"));
        user_idColumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("user_id"));
        sectorColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("sector"));
        ticketColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("ticket"));
        refreshReservations();

    }
    private void refreshMovies(){
        try {
            movieTable.setItems(FXCollections.observableList(movieManager.getAll()));
            movieTable.refresh();
        } catch (MovieException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    private void refreshUsers(){
        try {
            userTable.setItems(FXCollections.observableList(userManager.getAll()));
            userTable.refresh();
        } catch (MovieException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    private void refreshReservations(){
        try {
            reservationTable.setItems(FXCollections.observableList(reservationManager.getAll()));
            reservationTable.refresh();
        } catch (MovieException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void logoutOnAction(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login.fxml")));
        Stage stage=(Stage)((javafx.scene.Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addMovieOnAction(ActionEvent actionEvent) {

    }

    public void addUserOnAction(ActionEvent actionEvent) {
    }

    public void deleteMovieOnAction(ActionEvent actionEvent) {
    }

    public void deleteUserOnAction(ActionEvent actionEvent) {
    }

    public void updateMovieOnAction(ActionEvent actionEvent) {
    }

    public void updateUserOnAction(ActionEvent actionEvent) {
    }

    public void aboutOnAction(ActionEvent actionEvent) throws IOException {
        openDialog("about", "/fxml/admin_about.fxml", null);
    }
    private void openDialog(String title, String file, Object controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene((Parent) loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
