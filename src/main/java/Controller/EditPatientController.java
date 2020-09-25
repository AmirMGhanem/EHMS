package Controller;

import Model.Address;
import Model.Patient;
import Model.Therapist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditPatientController implements Initializable, Util.JavafxPaneHandler {

    @FXML
    private Pane parent;
    @FXML
    private TextField TextFieldFirstName;
    @FXML
    private TextField TextFieldLastName;
    @FXML
    private TextField TextFieldCity;
    @FXML
    private TextField TextFieldContactNum;
    @FXML
    private ChoiceBox<?> Choice3Digits;
    @FXML
    private TextField TextFieldStreet;
    @FXML
    private TextField TextFieldHouseNum;
    @FXML
    private Button BtnClear;
    @FXML
    private Button BtnSave;
    @FXML
    private ChoiceBox<?> ChoiceNurse;
    @FXML
    private Label LblNurseID;
    @FXML
    private TextField TextFieldPatientID;

    DBH.patientDAO pbh = new DBH.patientDAO();
    ArrayList<Patient> list = new ArrayList<Patient>();

    @FXML
    void OnClickBtnClear(ActionEvent event) {

    }

    @FXML
    void OnClickBtnSave(ActionEvent event) throws SQLException {
        list = pbh.selectAll();
        for (Patient p : list) {
            if (p.getID().equals(TextFieldPatientID.getText())) {
                p.setName(TextFieldFirstName.getText() + " " + TextFieldLastName.getText());
                String ContactNum = TextFieldContactNum.getText();
                p.setContactNo(ContactNum);
                Address address = new Address(TextFieldCity.getText(), TextFieldStreet.getText(), Integer.parseInt(TextFieldHouseNum.getText()));
                p.setAddress(address);
                pbh.UpdatePatient(p);

            }

        }

    }

    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FXMLLoader loader = new FXMLLoader();

        try {
            loader.load(getClass().getResource("/FXML/Settings.fxml").openStream());

            SettingsController settingsController = loader.getController();

            if (settingsController.getToggleMode()) {
                String css = this.getClass().getResource("/Css/darkmode.css").toExternalForm();
                parent.getStylesheets().add(css);
            } else {
                String css = this.getClass().getResource("/Css/lightmode.css").toExternalForm();
                parent.getStylesheets().add(css);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void onEnter(ActionEvent ae) throws SQLException {
        String id = TextFieldPatientID.getText();

        list = pbh.selectAll();

        for (Patient p : list) {
            if (p.getID().equals(id)) {
                TextFieldFirstName.setText(p.getFirstName());
                TextFieldLastName.setText(p.getLasttName());
                TextFieldContactNum.setText(p.getContactNo());
                TextFieldCity.setText(p.getAddress().getCity());
                TextFieldStreet.setText(p.getAddress().getStreet());
                TextFieldHouseNum.setText(Integer.toString(p.getAddress().getHouseNum()));
            }

        }
    }

    //Overrided by implementing JavafxPaneHandler
    @Override
    public void JavafxTableFill() {

    }

    @Override
    public void JavafxChoiceFill() {

    }

    @Override
    public void JavafxDiagramFill() {

    }
}
