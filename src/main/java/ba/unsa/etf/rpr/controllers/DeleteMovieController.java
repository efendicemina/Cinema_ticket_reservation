package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.domain.Movie;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
/**
 *DeleteMovieController class is responsible for deleting movies.
 *It provides functionality for deleting movie for provided id.
 *@author Emina Efendic
 */
public class DeleteMovieController {
    @FXML
    private ChoiceBox<Integer> idBox;
    @FXML
    private Label deleteMessage;
    private List<Integer> ids=new ArrayList<Integer>() ;
    private MovieManager movieManager=new MovieManager();

    /**
     * Initializes ChoiceBox with ids of all movies currently in database.
     * @throws MovieException in case of problem with db
     */
    @FXML
   public void initialize() throws MovieException {
        List<Movie> list=movieManager.getAll();
        for(int i=0; i< list.size(); i++){
            Movie movie= list.get(i);
            ids.add(movie.getId());
        }
       idBox.getItems().addAll(ids);
    }
    /**
     * Handles the 'Delete' button click event, deletes selected movie and displays a confirmation dialog.
     * In case of invalid input, it displays an error message.
     */
    public void deleteButtonOnAction(ActionEvent actionEvent) throws MovieException {
        try {
            movieManager.validateDeleteFields(idBox.getValue());
            movieManager.delete(idBox.getValue());
            openDialog("Information", "/fxml/information.fxml");
            Stage stage=(Stage) idBox.getScene().getWindow();
            stage.close();
        } catch (Exception e){
        new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
    }
    }
    /**
     * Opens a dialog window with the provided title and FXML file path
     */
    private void openDialog(String title,String file ) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setTitle(title);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();

    }
}
