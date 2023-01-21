package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This is a JavaFX controller class for a scene that displays an information message to the user.
 * @author Emina Efendic
 */

public class InformationController {
    @FXML
    private Label infoMessage;

    /**
     * Retrieves the stage of the current window and call the close() method to close it.
     * @param actionEvent actionEvent
     */
    public void closeButtonOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) infoMessage.getScene().getWindow();
        stage.close();
    }
}
