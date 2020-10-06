package Controller;

import DBH.therapistDAO;
import Model.Address;
import Model.Therapist;
import Util.InputsValidations;
import Util.MessageAlerter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditNurseController implements Initializable, Util.JavafxPaneHandler {

    DBH.therapistDAO tbh = new DBH.therapistDAO();
    ArrayList<Therapist> list = new ArrayList<Therapist>();

    @FXML private Pane parent;
    @FXML private Button BtnClear;
    @FXML private Button BtnSave;
    @FXML private TextField TextFieldNurseID;
    @FXML private TextField TextFieldFirstName;
    @FXML private TextField TextFieldLastName;
    @FXML private TextField TextFieldContactNum;
    @FXML private TextField TextFieldCity;
    @FXML private TextField TextFieldStreet;
    @FXML private TextField TextFieldHouseNum;
    @FXML private ChoiceBox<String> ChoiceNurseEdit;

    MessageAlerter ma = new MessageAlerter();
    ArrayList<Therapist> Therapists = new ArrayList<Therapist>();
    DBH.therapistDAO tdao = new therapistDAO();

    @FXML
    void OnClickClear(ActionEvent event) {
        TextFieldNurseID.setText("");
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
    public void onEnter(ActionEvent ae) throws SQLException {
        String id = TextFieldNurseID.getText();

        list = tbh.selectAll();

        for (Therapist t : list) {
            if (t.getID().equals(id)) {
                TextFieldFirstName.setText(t.getFirstName());
                TextFieldLastName.setText(t.getLasttName());
                TextFieldContactNum.setText(t.getContactNo());
                TextFieldCity.setText(t.getAddress().getCity());
                TextFieldStreet.setText(t.getAddress().getStreet());
                TextFieldHouseNum.setText(Integer.toString(t.getAddress().getHouseNum()));
            }
        }
    }

    @FXML
    void OnClickSave(ActionEvent event) throws SQLException {
        Util.InputsValidations iv = new InputsValidations();
        String MessageInformation = "";

        if (TextFieldNurseID.getLength() == 0) {
            MessageInformation += "You Have To Choose Nurse To Edit :) \n";
            ma.ShowErrorMessage("Unexpected Error", "Missing Information", MessageInformation);
        }
        else if ((TextFieldFirstName.getLength() == 0) || (TextFieldLastName.getLength() == 0) || (TextFieldContactNum.getLength() == 0) || (TextFieldCity.getLength() == 0) || (TextFieldStreet.getLength() == 0) || (TextFieldHouseNum.getLength() == 0)) {
            MessageInformation += "Messing Information : \n";
            if (TextFieldFirstName.getLength() == 0) MessageInformation += "* First Name \n";
            if (TextFieldLastName.getLength() == 0) MessageInformation += "* Last Name \n";
            if (TextFieldContactNum.getLength() == 0) MessageInformation += "* Contact Number \n";
            if (TextFieldCity.getLength() == 0) MessageInformation += "* City \n";
            if (TextFieldStreet.getLength() == 0) MessageInformation += "* Street \n";
            if (TextFieldHouseNum.getLength() == 0) MessageInformation += "* House Number \n";
            ma.ShowErrorMessage("Unexpected Error", "Missing Information", MessageInformation);
        }
        else {
            boolean isValid = iv.isNurseEditInputsValid(TextFieldFirstName.getText(), TextFieldLastName.getText(), TextFieldContactNum.getText(), TextFieldCity.getText(), TextFieldStreet.getText(), TextFieldHouseNum.getText());

            if(isValid==true) {
                MessageInformation += "Nursing Edited Successfully :)";
                list = tbh.selectAll();
                for (Therapist t : list) {
                    if (t.getID().equals(TextFieldNurseID.getText())) {
                        t.setName(TextFieldFirstName.getText() + " " + TextFieldLastName.getText());
                        String ContactNum = TextFieldContactNum.getText();
                        t.setContactNo(ContactNum);
                        Address address = new Address(TextFieldCity.getText(), TextFieldStreet.getText(), Integer.parseInt(TextFieldHouseNum.getText()));
                        t.setAddress(address);
                        tbh.Updateherapist(t);
                    }
                }
                ma.MessageWithoutHeader("Added", MessageInformation);
                ChoiceNurseEdit.getItems().clear();
                JavafxChoiceFill();
            }
            else{
                ma.MessageWithoutHeader("Unsuccessful", "Incorrect Inputs");
            }
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

    //Overrided by implementing JavafxPaneHandler
    @Override
    public void JavafxTableFill() {

    }

    public void onSelectTherapist(ActionEvent event) throws SQLException {
        String name = ChoiceNurseEdit.getValue();

        list = tbh.selectAll();

        for (Therapist t : list) {
            if (t.getName().equals(name)) {
                TextFieldNurseID.setText(t.getID());
                TextFieldFirstName.setText(t.getFirstName());
                TextFieldLastName.setText(t.getLasttName());
                TextFieldContactNum.setText(t.getContactNo());
                TextFieldCity.setText(t.getAddress().getCity());
                TextFieldStreet.setText(t.getAddress().getStreet());
                TextFieldHouseNum.setText(Integer.toString(t.getAddress().getHouseNum()));
            }
        }
    }

    @Override
    public void JavafxChoiceFill() throws SQLException {
        Therapists = tdao.selectAll();
        for(int i=0 ; i<Therapists.size(); i++){
            ChoiceNurseEdit.getItems().add(Therapists.get(i).getName());
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
}