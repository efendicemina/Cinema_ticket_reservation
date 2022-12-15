package ba.unsa.etf.rpr.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
public class LoginController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File file=new File("resources/images/movie.jpg");
        Image image=new Image(file.toURI().toString());
        image1.setImage(image);
    }
    public void CancelButtonOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(javafx.event.ActionEvent actionEvent) {
        if(usernameTextField.getText().isEmpty()!=false && passwordField.getText().isEmpty()!=false){
            loginMessageLabel.setText("Invalid login.");
        }
        //else validateLogin();
    }

}
