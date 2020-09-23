package View;
import Network.ApplicationNetwork;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.fxml.FXMLLoader.load;

public class MultipleFxmlHandlingJavaFX extends Application {
    private ApplicationNetwork Server = new ApplicationNetwork();

    public ApplicationNetwork getServer() {
        return Server;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = load(getClass().getResource("/FXML/Main.fxml"));
        Scene scene = new Scene(root,1500, 700);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        Server.start();

    }



    public static void main(String[] args)
    {
        launch(args);
    }
}
