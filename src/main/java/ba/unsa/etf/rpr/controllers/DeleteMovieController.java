package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exception.MovieException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 *DeleteMovieController class is responsible for deleting movies.
 *It provides functionality for deleting movie for provided id.
 *@author Emina Efendic
 */
public class DeleteMovieController {
    @FXML
    private ChoiceBox<Integer> idBox;
    private final List<Integer> ids= new ArrayList<>() ;
    private final MovieManager movieManager=new MovieManager();

    /**
     * Initializes ChoiceBox with ids of all movies currently in database.
     * @throws MovieException in case of problem with db
     */
    @FXML
   public void initialize() throws MovieException {
        List<Movie> list=movieManager.getAll();
        for (Movie movie : list) {
            ids.add(movie.getId());
        }
       idBox.getItems().addAll(ids);
    }
    /**
     * Handles the 'Delete' button click event, deletes selected movie and displays a confirmation dialog.
     * In case of invalid input, it displays an error message.
     */
    public void deleteButtonOnAction() {
        try {
            movieManager.validateDeleteFields(idBox.getValue());
            movieManager.delete(idBox.getValue());
            new Alert(Alert.AlertType.NONE, "Action successful", ButtonType.OK).show();
            Stage stage=(Stage) idBox.getScene().getWindow();
            stage.close();
        } catch (Exception e){
        new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
    }
    }
}
