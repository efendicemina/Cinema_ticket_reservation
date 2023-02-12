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

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *RegisterController class is responsible for verifying and registering in users.
 *It provides functionality for register with provided all needed information.
 *@author Emina Efendic
 */
public class RegisterController {
    @FXML
    private Button cancelButton;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField nameField;

    @FXML
    private Label registerMessage;
    @FXML
    private Label emptyMessage;
    @FXML
    private Label usernameMessage;
    @FXML
    private Label emailMessage;
    private final UserManager userManager=new UserManager();
    /**
     * Retrieves the stage of the current window and call the close() method to close it.
     */
    public void cancelButtonOnAction() {
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
/**
 *The checkEmail method checks if a given string is a valid email address.
 *The method uses a regular expression to validate the email address.
 *@param emailField The string to be checked if it is a valid email address.
 *@return Returns true if the given string is a valid email address, false otherwise.
 */
    public boolean checkEmail(String emailField){
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailField);
        return matcher.matches();
    }
    /**
     *The registerButtonOnAction method is called when the register button is pressed.
     *It validates the input fields and if they are all filled and valid, it creates a new User object and adds it to the system.
     *If any of the fields are empty or the email address is not in valid format or the username is already taken, it shows an error message.
     *@throws MovieException when there is any problem with the registration process.
     */
    public void registerButtonOnAction() throws MovieException {
        if (usernameField.getText().isEmpty()  || passwordField.getText().isEmpty()  || emailField.getText().isEmpty() ||
                phoneField.getText().isEmpty() || nameField.getText().isEmpty() ) {
            emptyMessage.setText("Please fill the empty fields.");
            if(usernameField.getText().isEmpty()) usernameMessage.setText("");
            if(emailField.getText().isEmpty()) emailMessage.setText("");
        }
        else{
            emptyMessage.setText("");
            boolean emailOk=checkEmail(emailField.getText());
            boolean usernameFound=userManager.findUsername(usernameField.getText());
            if(!emailOk) {
                emailMessage.setText("Invalid e-mail format.");
            }
            else{
                emailMessage.setText("");
            }
            if (usernameFound) {
                usernameMessage.setText("Username already taken.");
            } else {
                usernameMessage.setText("");
            }
            if(!usernameFound && emailOk) {
                User user = new User();
                user.setPhone(phoneField.getText());
                user.setUsername(usernameField.getText());
                user.setAdmin(false);
                user.setPassword(passwordField.getText());
                user.setName(nameField.getText());
                user.setEmail(emailField.getText());
                MyModel model = MyModel.getInstance();
                model.setUser(user);
                    userManager.add(user);
                    registerMessage.setText("You are registered. Click the link below.");
            }
            else{
                registerMessage.setText("");
            }
        }
    }
    /**
     * Switches window allowing users to log in with their account.
     * @param event ActionEvent
     * @throws Exception in case of problems with switching windows.
     */
    public void loginLinkOnAction(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login.fxml")));
        Stage stage=(Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
