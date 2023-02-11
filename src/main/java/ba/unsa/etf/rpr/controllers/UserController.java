package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.controllers.components.ButtonCellFactory;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exception.MovieException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



/**
 * The UserController class is responsible for the user interface of the application
 * it contains methods that handle user interactions with the application,
 * such as populating tables, responding to button clicks, and displaying dialogs.
 * It allows users to browse the available movies and book the tickets for the selected movie
 * @author Emina Efendic
 */
public class UserController {
    private final MovieManager movieManager = new MovieManager();
    @FXML
    BorderPane userScreen;
    @FXML
    public TableView movieTable;
    @FXML
    public TableColumn<Movie, String> nameColumn;
    @FXML
    public TableColumn<Movie, String> genreColumn;
    @FXML
    public TableColumn<Movie, LocalDateTime> dateColumn;
    @FXML
    public TableColumn<Movie, Integer> durationColumn;
    @FXML
    public TableColumn<Movie, Integer> bookingColumn;
    private List<Stage> dialogStages = new ArrayList<>();
    private Movie movie;
    /**
     *It sets up the UI elements such as the movie table, populating the table with data, setting cell value factories and creating button cell factory.
     *Specifically, it initializes the table columns with the properties of the Movie class and sets the button cell factory for the booking column.
     *It also calls the refreshMovies method to populate the table with all available movies.
     */
    public void initialize(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Movie, LocalDateTime>("date_time"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("duration"));
        bookingColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));

        bookingColumn.setCellFactory(new ButtonCellFactory(editEvent -> {
            try {
                int movieId = Integer.parseInt(((Button) editEvent.getSource()).getUserData().toString());
                bookScene(movieId);
            }
            catch(Exception e){

            }

        }));

        refreshMovies();
    }
    /**
     *The bookScene method is used to handle the booking of a movie.
     *it retrieves the movie that matches the given id, sets it to the MyModel instance and opens the book.fxml.
     *@param movieId the id of the movie that user wants to book
     *@throws Exception if there is a problem with loading the fxml file or getting the movie by id.
     */
    public void bookScene(Integer movieId){
        try{
            movie=movieManager.getById(movieId);
            MyModel model = MyModel.getInstance();
            model.setMovie(movie);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/book.fxml"));
            Stage stage = new Stage();
            stage.getIcons().add(new Image("images/ticket-icon.jpg"));
            stage.setResizable(false);
            stage.setScene(new Scene(loader.load()));
            dialogStages.add(stage);
            stage.show();
            stage.setOnHiding(event -> {
                ((Stage)userScreen.getScene().getWindow()).show();
                refreshMovies();
            });
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * The refreshMovies method is used to refresh the movie table.
     */
    private void refreshMovies(){
        try {
            movieTable.setItems(FXCollections.observableList(movieManager.getAll()));
            movieTable.refresh();
        } catch (MovieException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     *The logoutOnAction method is used to handle the logout button click event.
     *It loads the login.fxml file and sets it as the new scene of the current stage and shows it.
     *@param mouseEvent the mouse event that triggers the logout button click
     *@throws IOException if there is a problem with loading the fxml file
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
     *The aboutOnAction method is used to handle the about button click event.
     *It opens a dialog box for about information by calling the openDialog method.
     *@param actionEvent the action event that triggers the about button click
     */
    public void aboutOnAction(ActionEvent actionEvent) {
        openDialog("About", "/fxml/user_about.fxml");
    }
    /**
     *The openDialog method opens a new window with the given title and FXML file.
     *@param title The title of the new window.
     *@param file The filepath of the FXML file to be loaded in the new window.
     *@throws IOException when there is a problem with loading the FXML file.
     */
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
                ((Stage)userScreen.getScene().getWindow()).show();
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
}
