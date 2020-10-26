package View;

import DBH.adressDAO;
import DBH.patientDAO;
import DBH.personDAO;
import Model.Address;
import Network.ApplicationNetwork;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.fxml.FXMLLoader.load;

public class MultipleFxmlHandlingJavaFX extends Application {
    private ApplicationNetwork Server = new ApplicationNetwork();

    public ApplicationNetwork getServer() {
        return Server;
    }

    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        MultipleFxmlHandlingJavaFX.primaryStage = primaryStage;
    }


    public static void restartStage(){
       primaryStage.close();
    }


    @Override
    public void start(Stage stage) throws Exception {
        setPrimaryStage(stage);
        Parent root = load(getClass().getResource("/FXML/splash.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        DBH.adressDAO ado = new adressDAO();
        DBH.personDAO pdo = new personDAO();
        DBH.patientDAO pado = new patientDAO();

        Server.start();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
