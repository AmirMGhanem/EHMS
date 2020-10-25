package Controller;

import Util.DatabaseConnector;
import Util.MessageAlerter;
import Util.SQLExporter;
import Util.Service;
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
        String url = "jdbc:mysql://localhost:" + TextfieldDBport.getText() + "/" + TextfieldName.getText();
        String user = TextfieldDBuser.getText();
        String pass = TextfieldPassword.getText();
        DatabaseConnector.setUser(user);
        DatabaseConnector.setPass(pass);
        DatabaseConnector.setUrl(url);
    }

    @FXML
    void onClickBtnClose(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }


    @FXML
    void onClickBtnChooseDir1(ActionEvent event) {

        try {
            SQLExporter sqlExporter = new SQLExporter();
            sqlExporter.export();
        }catch(Exception e )
        {
            messageAlerter.ShowErrorMessage("ERROR!!!", "Directory Path is null", "***Please Choose A Directory in order\n to save the SQL Template ");
        }

    }
    @FXML
    void onClickBtnChooseDir(ActionEvent event) throws IOException {
        Service s = new Service();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        File source = new File("ehms.sql");
        File dest = new File(selectedDirectory.getPath() + "/TemplateEHMS.sql");
        if (selectedDirectory != null) {
            s.copyFileUsingChannel(source, dest);
        } else {
            messageAlerter.ShowErrorMessage("ERROR!!!", "Directory Path is null", "***Please Choose A Directory in order\n to save the SQL Template ");
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
