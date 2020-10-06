package Controller;

import Model.Address;
import Model.Patient;
import Util.IValidations;
import Util.MessageAlerter;
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

public class EditPatientController implements Initializable, Util.JavafxPaneHandler, IValidations {

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
    @FXML
    private ChoiceBox<String> ChoicePatientName;

    MessageAlerter ma = new MessageAlerter();
    DBH.patientDAO pbh = new DBH.patientDAO();
    ArrayList<Patient> list = new ArrayList<Patient>();

    @FXML
    void OnClickBtnClear(ActionEvent event) {
        TextFieldPatientID.setText("");
        TextFieldFirstName.setText("");
        TextFieldLastName.setText("");
        TextFieldContactNum.setText("");
        TextFieldCity.setText("");
        TextFieldStreet.setText("");
        TextFieldHouseNum.setText("");
        String MessageInformation = "All Fields Cleared";
        ma.MessageWithoutHeader("Cleared", MessageInformation);
    }

    @FXML
    void OnClickBtnSave(ActionEvent event) throws SQLException {
        if (!(nameValidation(TextFieldFirstName.getText()) && nameValidation(TextFieldLastName.getText()) && numValidation(TextFieldContactNum.getText(), 10)))
            ma.ShowErrorMessage("Error", "Incorrect Inputs", "Please Make Sure That The Name You \n Inserted Contains Text Only");
        else if (!(nameValidation(TextFieldCity.getText()) && nameValidation(TextFieldStreet.getText()) && numValidation(TextFieldHouseNum.getText())))
            ma.ShowErrorMessage("Error", "Incorrect Inputs", "Please Make Sure Of The Address You Inserted \n it must contain A-Z characters only");
        else {
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
            ChoicePatientName.getItems().clear();
            JavafxChoiceFill();
        }
    }

    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            JavafxChoiceFill();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CssStyler();
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
    public void JavafxChoiceFill() throws SQLException {
        list = pbh.selectAll();
        for (int i = 0; i < list.size(); i++) {
            ChoicePatientName.getItems().add(list.get(i).getName());
        }
    }

    @Override
    public void JavafxDiagramFill() {

    }

    private void CssStyler() {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.load(getClass().getResource("/FXML/Settings.fxml").openStream());
            SettingsController settingsController = loader.getController();
            parent.getStylesheets().removeAll();
            if (settingsController.isCustomeDesignFlag()) {
                String css = this.getClass().getResource("/Css/UserCustomDesign.css").toExternalForm();
                parent.getStylesheets().add(css);
            } else {
                if (settingsController.getToggleMode()) {
                    String css = this.getClass().getResource("/Css/darkmode.css").toExternalForm();
                    parent.getStylesheets().add(css);
                } else if (!settingsController.getToggleMode()) {
                    String css = this.getClass().getResource("/Css/lightmode.css").toExternalForm();
                    parent.getStylesheets().add(css);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSelectPatient(ActionEvent event) throws SQLException {
        String name = ChoicePatientName.getValue();
        list = pbh.selectAll();

        for (Patient p : list) {
            if (p.getName().equals(name)) {
                TextFieldPatientID.setText(p.getID());
                TextFieldFirstName.setText(p.getFirstName());
                TextFieldLastName.setText(p.getLasttName());
                TextFieldContactNum.setText(p.getContactNo());
                TextFieldCity.setText(p.getAddress().getCity());
                TextFieldStreet.setText(p.getAddress().getStreet());
                TextFieldHouseNum.setText(Integer.toString(p.getAddress().getHouseNum()));
            }
        }
    }
}