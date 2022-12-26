package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.dao.MovieDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminController {
    @FXML
    public TableView<Movie> movieTable;
    @FXML
    public TableColumn<Movie, String> midColumn;
    @FXML
    public TableColumn<Movie, String> mnameColumn;
    @FXML
    public TableColumn<Movie, String> genreColumn;
    @FXML
    public TableColumn<Movie, String> dateColumn;
    @FXML
    public TableColumn<Movie, String> durationColumn;
    private ObservableList<Movie> movies;
    private MovieDaoSQLImpl movieDaoSQL;

    public AdminController() {
        movieDaoSQL = new MovieDaoSQLImpl();
        movies = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        midColumn.setCellValueFactory(new PropertyValueFactory<Movie,String>("movie_id"));
        mnameColumn.setCellValueFactory(new PropertyValueFactory<Movie,String>("name"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Movie,String>("genre"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Movie,String>("date_time"));
                movieTable.getItems().clear();
                movies.addAll();
                movieTable.refresh();
                movieTable.setItems(movies);

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
}
