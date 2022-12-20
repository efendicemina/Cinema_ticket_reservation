package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    public void cancelButtonOnAction(ActionEvent actionEvent) {
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void registerButtonOnAction(javafx.event.ActionEvent actionEvent) {
        if (usernameField.getText().isEmpty()  || passwordField.getText().isEmpty()  || emailField.getText().isEmpty() ||
                phoneField.getText().isEmpty() || nameField.getText().isEmpty() ) {
            emptyMessage.setText("Please fill the empty fields.");
            if(usernameField.getText().isEmpty()) usernameMessage.setText("");
            if(emailField.getText().isEmpty()) emailMessage.setText("");
        }
        else{
            emptyMessage.setText("");
            UserDaoSQLImpl userDaoSQL = new UserDaoSQLImpl();
            boolean emailOk=userDaoSQL.validateEmail(emailField.getText());
            boolean usernameOk=userDaoSQL.validateUsername(usernameField.getText());
            if(!emailOk) {
                emailMessage.setText("Invalid e-mail format.");
            }
            else{
                emailMessage.setText("");
            }
            if (!usernameOk) {
                usernameMessage.setText("Username already taken.");
            } else {
                usernameMessage.setText("");
            }
            if(usernameOk && emailOk) {
                User user = new User();
                user.setPhone(phoneField.getText());
                user.setUsername(usernameField.getText());
                user.setAdmin(false);
                user.setPassword(passwordField.getText());
                user.setName(nameField.getText());
                user.setEmail(emailField.getText());
                    userDaoSQL.add(user);
                    registerMessage.setText("You are registered. Click the link below.");
                    usernameMessage.setText("Username already taken.");
            }
        }
    }
    public void loginLinkOnAction(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login.fxml")));
        Stage stage=(Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
