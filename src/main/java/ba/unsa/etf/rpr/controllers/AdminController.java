package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.MovieException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 *
 *AdminController class is responsible for managing movies, users and reservations.
 *It displays current movies, users, and reservations in the tables.
 *@author Emina Efendic
 */
public class AdminController {
    private final MovieManager movieManager = new MovieManager();
    private final UserManager userManager = new UserManager();
    private final ReservationManager reservationManager = new ReservationManager();
    @FXML
    BorderPane adminPane;

    @FXML
    public TableView<Movie> movieTable;
    @FXML
    public TableColumn<Movie, String> midColumn;
    @FXML
    public TableColumn<Movie, String> mnameColumn;
    @FXML
    public TableColumn<Movie, String> genreColumn;
    @FXML
    public TableColumn<Movie, LocalDate> dateColumn;
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
    /**
     *Initializes the movieTable, userTable, and reservationTable with values from database for movies, users, and reservations.
     *It also sets the cell value factories for each column in the tables to display the appropriate values.
     */
    @FXML
    public void initialize() {
        midColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        mnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date_time"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        refreshMovies();
        uidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        unameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        refreshUsers();
        ridColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        movie_idColumn.setCellValueFactory(new PropertyValueFactory<>("movie"));
        user_idColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        sectorColumn.setCellValueFactory(new PropertyValueFactory<>("sector"));
        refreshReservations();

    }

    /**
     * Refreshes movies table with the current data.
     */
    void refreshMovies() {
        try {
            movieTable.setItems(FXCollections.observableList(movieManager.getAll()));
            movieTable.refresh();
        } catch (MovieException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * Refreshes users table with the current data.
     */
    private void refreshUsers() {
        try {
            userTable.setItems(FXCollections.observableList(userManager.getAll()));
            userTable.refresh();
        } catch (MovieException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * Refreshes reservations table with the current data.
     */
    private void refreshReservations() {
        try {
            reservationTable.setItems(FXCollections.observableList(reservationManager.getAll()));
            reservationTable.refresh();
        } catch (MovieException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * It logs users out, opens the login window.
     * @param mouseEvent MouseEvent
     */
    public void logoutOnAction(MouseEvent mouseEvent) throws IOException {
        closeAllDialogs();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login.fxml")));
        Stage stage = (Stage) ((javafx.scene.Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    /**
     *Opens add movie dialog window
     */
    public void addMovieOnAction() {
        openDialog("Add movie", "/fxml/movie_add.fxml");

    }
    /**
     *Opens delete movie dialog window
     */
    public void deleteMovieOnAction() {
        openDialog("Delete movie", "/fxml/movie_delete.fxml");
    }
    /**
     *Opens update movie dialog window
     */

    public void updateMovieOnAction() {
        openDialog("Update movie", "/fxml/movie_update.fxml");
    }

    /**
     *Opens about window
     */
    public void aboutOnAction() {
        openDialog("About", "/fxml/admin_about.fxml");
    }
    /**
     *Opens dialogs if possible, if not it displays an alert.
     *@param title String
     *@param file String
     */
    private List<Stage> dialogStages = new ArrayList<>();

    private void openDialog(String title, String file) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            Stage stage = new Stage();
            stage.getIcons().add(new Image("images/ticket-icon.jpg"));
            stage.setResizable(false);
            stage.setScene(new Scene(loader.load()));
            stage.show();
            dialogStages.add(stage);
            stage.setOnHiding(event -> {
                ((Stage)adminPane.getScene().getWindow()).show();
                refreshMovies();
            });

        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    private void closeAllDialogs() {
        for (Stage stage : dialogStages) {
            stage.close();
        }
    }



    /**
     *Creates a new movie object using provided name, genre, date and time, and duration.
     *@param name String
     *@param genre String
     *@param date LocalDateTime
     *@param duration Integer
     *@return Movie
     */
    public Movie createMovie(String name, String genre, LocalDateTime date, Integer duration) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setGenre(genre);
        movie.setDuration(duration);
        movie.setDate_time(date);
        return movie;
    }
}