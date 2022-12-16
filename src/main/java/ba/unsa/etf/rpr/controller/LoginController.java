package ba.unsa.etf.rpr.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;


public class LoginController  {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordField;

    public void CancelButtonOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(javafx.event.ActionEvent actionEvent) {
        if(usernameTextField.getText().isEmpty() && passwordField.getText().isEmpty()){
            loginMessageLabel.setText("Invalid login.");
        }
        else validateLogin();
    }
    public void validateLogin() {
        String insert = "SELECT count(1) from users where username='" + usernameTextField.getText() + "' AND password='"
                + passwordField.getText() + "'";
        try {
            UserDaoSQLImpl u = new UserDaoSQLImpl();
            PreparedStatement stmt = u.getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { // result set is iterator.
                if (rs.getInt(1) == 1) {
                    loginMessageLabel.setText("top");
                } else {
                    loginMessageLabel.setText("Invalid login.");
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void registerLinkOnAction(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/register.fxml")));
        Stage stage=(Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
