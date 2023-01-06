package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;



public class InformationController {
    @FXML
    private Label infoMessage;
    public void closeButtonOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) infoMessage.getScene().getWindow();
        stage.close();
    }
}
