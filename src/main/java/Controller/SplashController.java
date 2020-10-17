package Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class SplashController implements Initializable {

    @FXML
    private StackPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new SplashScreen().start();

    }

    class SplashScreen extends Thread {

        @Override
        public void run() {
            try{
                Thread.sleep(1000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Parent root = null;
                        try {
                            root = load(getClass().getResource("/FXML/Main.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root, 1500, 700);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setMaximized(true);
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.show();
                        rootPane.getScene().getWindow().hide();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
