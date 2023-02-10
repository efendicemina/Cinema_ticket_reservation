package ba.unsa.etf.rpr;
import ba.unsa.etf.rpr.dao.AbstractDao;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * class that extends Application
 *
 */
public class AppFX extends Application
{
    public static void main( String[] args )
    {
        Application.launch(args);
        AbstractDao.closeConnection();
        Platform.exit();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login.fxml")));
        primaryStage.getIcons().add(new Image("images/ticket-icon.jpg"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
