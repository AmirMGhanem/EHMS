package Controller;

import DBH.therapistDAO;
import Model.Address;
import Model.Therapist;
import Util.IValidations;
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

public class EditNurseController implements Initializable, Util.JavafxPaneHandler, IValidations {

    DBH.therapistDAO tbh = new DBH.therapistDAO();
    ArrayList<Therapist> list = new ArrayList<Therapist>();
    MessageAlerter messageAlerter = new MessageAlerter();
    @FXML
    private Pane parent;
    @FXML
    private Button BtnClear;
    @FXML
    private Button BtnSaSave;
    @FXML
    private TextField TextFieldNurseID;
    @FXML
    private TextField TextFieldFirstName;
    @FXML
    private TextField TextFieldLastName;
    @FXML
    private TextField TextFieldContactNum;
    @FXML
    private TextField TextFieldCity;
    @FXML
    private TextField TextFieldStreet;
    @FXML
    private TextField TextFieldHouseNum;
    @FXML
    private ChoiceBox<String> ChoiceNurseEdit;

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

        list = tbh.selectAll();

        if (!(nameValidation(TextFieldFirstName.getText()) && nameValidation(TextFieldLastName.getText()) && numValidation(TextFieldContactNum.getText(), 10)))
            messageAlerter.ShowErrorMessage("Error", "Incorrect Inputs", "Please Make Sure That The Name You \n Inserted Contains Text Only");
        else if (!(nameValidation(TextFieldCity.getText()) && nameValidation(TextFieldStreet.getText()) && numValidation(TextFieldHouseNum.getText())))
            messageAlerter.ShowErrorMessage("Error", "Incorrect Inputs", "Please Make Sure Of The Address You Inserted \n it must contain A-Z characters only");
        else {
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
            ChoiceNurseEdit.getItems().clear();
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
        for (int i = 0; i < Therapists.size(); i++) {
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