package Util;

import View.MultipleFxmlHandlingJavaFX;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class FxmlLoader {
    private Pane view;

    public Pane getPage(String fileName) throws IOException {

        try {
            URL fileUrl = MultipleFxmlHandlingJavaFX.class.getResource("/FXML/" + fileName + ".fxml");

            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("FXML file can't be found!");
            }

            view = new FXMLLoader().load(fileUrl);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return view;
    }

}

