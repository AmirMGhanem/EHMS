package Controller;

import Util.DatabaseConnector;
import Util.MessageAlerter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;


import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.util.ResourceBundle;

public class DatabaseInformationController implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private TextField TextfieldPassword;

    @FXML
    private TextField TextfieldDBuser;

    @FXML
    private TextField TextfieldName;

    @FXML
    private TextField TextfieldURL;

    @FXML
    private Button BtnChooseDir;

    @FXML
    private TextField TextfieldDBport;
    MessageAlerter messageAlerter = new MessageAlerter();

    @FXML
    void onClickBtnSave(ActionEvent event) {
        //String url = "jdbc:mysql://localhost:" + TextfieldDBport.getText() + "/" + TextfieldName.getText();
        //String user = TextfieldDBuser.getText();
        //String pass = TextfieldPassword.getText();
        //DatabaseConnector.setUser(user);
        //DatabaseConnector.setPass(pass);
        //DatabaseConnector.setUrl(url);
    }

    @FXML
    void onClickBtnClose(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void onClickBtnChooseDir(ActionEvent event) throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        File source = new File("ehms.sql");
        File dest = new File(selectedDirectory.getPath() + "/ehmsCopy.sql");
        if (selectedDirectory != null) {
            copyFileUsingChannel(source, dest);
        } else {
            messageAlerter.ShowErrorMessage("ERROR!!!", "Directory Path is null", "***Please Choose A Directory in order\n to save the SQL Template ");
        }
    }


    private static void copyFileUsingChannel(File source, File dest) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } finally {
            sourceChannel.close();
            destChannel.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextfieldDBport.setText(DatabaseConnector.getPort());
        TextfieldDBuser.setText(DatabaseConnector.getUser());
        TextfieldPassword.setText(DatabaseConnector.getPass());
        TextfieldURL.setText(DatabaseConnector.getUrl());
        TextfieldName.setText(DatabaseConnector.getName());
    }
}
