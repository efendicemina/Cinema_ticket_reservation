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
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

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
    private Movie movie;
    public void initialize(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Movie, LocalDateTime>("date_time"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("duration"));
        bookingColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));

        bookingColumn.setCellFactory(new ButtonCellFactory(editEvent -> {
            int movieId = Integer.parseInt(((Button)editEvent.getSource()).getUserData().toString());
                bookScene(movieId);

        }));

        refreshMovies();
    }
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
            stage.show();
            stage.setOnHiding(event -> {
                ((Stage)userScreen.getScene().getWindow()).show();
                refreshMovies();
            });
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    private void refreshMovies(){
        try {
            movieTable.setItems(FXCollections.observableList(movieManager.getAll()));
            movieTable.refresh();
        } catch (MovieException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void logoutOnAction(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login.fxml")));
        Stage stage = (Stage) ((javafx.scene.Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void aboutOnAction(ActionEvent actionEvent) {
        openDialog("About", "/fxml/user_about.fxml");
    }
    private void openDialog(String title, String file) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            Stage stage = new Stage();
            stage.setScene(new Scene((Parent) loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHiding(event -> {
                ((Stage) userScreen.getScene().getWindow()).show();
                refreshMovies();
            });
        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
