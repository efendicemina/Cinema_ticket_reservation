package ba.unsa.etf.rpr.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import javafx.stage.StageStyle;

public class LoginController  {
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
    @FXML
    private Hyperlink registerLink;
  /*  @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File file=new File("resources/images/movie.jpg");
        Image image=new Image(file.toURI().toString());
        image1.setImage(image);
    }
      */
    public void CancelButtonOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(javafx.event.ActionEvent actionEvent) {
        if(usernameTextField.getText().isEmpty()!=false && passwordField.getText().isEmpty()!=false){
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
    public void registerLinkOnAction()throws Exception{
        Stage registerStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        registerStage.initStyle(StageStyle.UNDECORATED);
        registerStage.setScene(new Scene(root, 520,593));
        registerStage.setResizable(false);
        registerStage.show();
        Stage stage=(Stage) registerLink.getScene().getWindow();
        stage.close();
    }
}
