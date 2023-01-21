package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.MovieException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 *LoginController class is responsible for verifying and logging in users and admin.
 *It provides functionality for logging in with provided username and password.
 *@author Emina Efendic
 */
public class LoginController  {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordField;

    private UserManager userManager=new UserManager();
    /**
     * Retrieves the stage of the current window and call the close() method to close it.
     * @param actionEvent actionEvent
     */
    public void CancelButtonOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Defining action for login button. Method verifies if username and password are in db.
     * Opens two different windows, admin panel or user panel.
     * @param actionEvent ActionEvent
     * @throws MovieException in case of problems with db
     * @throws IOException
     */
    public void loginButtonOnAction(javafx.event.ActionEvent actionEvent) throws MovieException, IOException {
        if(usernameTextField.getText().trim().isEmpty() && passwordField.getText().trim().isEmpty()){
            loginMessageLabel.setText("Invalid login.");
        }
        else {
            Integer id=userManager.checkUsernamePassword(usernameTextField.getText(),passwordField.getText());
            if (id!=null) {
                User user = userManager.getById(id);
                MyModel model = MyModel.getInstance();
                model.setUser(user);
                if(userManager.isAdmin(usernameTextField.getText())){
                    openDialog(actionEvent,"/fxml/admin.fxml");
                }
                else {
                    openDialog(actionEvent,"/fxml/user.fxml" );
                }
            } else {
                loginMessageLabel.setText("Invalid login.");
            }
        }
    }

    /**
     * Switches window allowing users to register and create a new account.
     * @param event ActionEvent
     * @throws Exception in case of problems with switching windows.
     */
    public void registerLinkOnAction(ActionEvent event)throws Exception{
       openDialog(event,"/fxml/register.fxml");
    }
    /**
     * Opens a dialog window with the provided FXML file path
     *@param fxml The filepath of the FXML file to be loaded in the new window.
     *@throws IOException when there is a problem with loading the FXML file.
     */
    private void openDialog(ActionEvent actionEvent, String fxml) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        Stage stage=(Stage)((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
