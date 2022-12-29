package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.exception.MovieException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class DeleteMovieController {
    @FXML
    private ChoiceBox<Integer> idBox;
    @FXML
    private Label deleteMessage;
    private List<Integer> ids=new ArrayList<Integer>() ;
    private MovieManager movieManager=new MovieManager();
    @FXML
   public void initialize() throws MovieException {
        List<Movie> list=movieManager.getAll();
        for(int i=0; i< list.size(); i++){
            Movie movie= list.get(i);
            ids.add(movie.getId());
        }
       idBox.getItems().addAll(ids);
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) throws MovieException {
        try {
            movieManager.validateDeleteFields(idBox.getValue());
            movieManager.delete(idBox.getValue());
            deleteMessage.setText("Movie deleted.");
        } catch (Exception e){
        new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
    }
    }
}
