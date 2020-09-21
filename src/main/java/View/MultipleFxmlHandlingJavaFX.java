package View;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.fxml.FXMLLoader.load;

public class MultipleFxmlHandlingJavaFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = load(getClass().getResource("/FXML/Main.fxml"));

        Scene scene = new Scene(root,1500, 700);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }



    public static void main(String[] args)
    {
        launch(args);
    }
}
