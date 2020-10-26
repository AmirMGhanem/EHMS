package Controller;

import DBH.adressDAO;
import DBH.patientDAO;
import DBH.personDAO;
import Model.Address;
import Model.Patient;
import Model.Person;
import Util.IValidations;
import Util.MessageAlerter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AddPatientController implements Initializable, Util.JavafxPaneHandler, IValidations {

    ObservableList GenderList = FXCollections.observableArrayList();
    ObservableList ThreeDigitsList = FXCollections.observableArrayList();
    MessageAlerter ma = new MessageAlerter();

    DBH.adressDAO ado = new adressDAO();
    DBH.personDAO pdo = new personDAO();
    DBH.patientDAO pado = new patientDAO();

    ArrayList<Person> persons = new ArrayList<Person>();

    @FXML
    private Pane parent;
    @FXML
    private TextField TextFieldFirstName;
    @FXML
    private TextField TextFieldLastName;
    @FXML
    private ChoiceBox<String> ChoiceGender;
    @FXML
    private DatePicker DatePickerBirthDate;
    @FXML
    private TextField TextFieldID;
    @FXML
    private TextField TextFieldContactNum;
    @FXML
    private TextField TextFieldCity;
    @FXML
    private TextField TextFieldStreet;
    @FXML
    private TextField TextFieldHouseNum;
    @FXML
    private TextField TextFieldAddressCode;
    @FXML
    private ChoiceBox<?> Choice3DigitsNum;
    @FXML
    private Button BtnAdd;
    @FXML
    private Button BtnClear;

    @FXML
    void OnClickBtnClear(ActionEvent event) {
        TextFieldFirstName.setText("");
        TextFieldLastName.setText("");
        TextFieldID.setText("");
        TextFieldContactNum.setText("");
        TextFieldCity.setText("");
        TextFieldStreet.setText("");
        TextFieldHouseNum.setText("");
        TextFieldAddressCode.setText("");
    }

    @FXML
    void OnClickBtnAdd(ActionEvent event) throws IOException, SQLException {
        Date curr = new Date();
        boolean isExist = false;
        if (!(numValidation(TextFieldID.getText(), 9))) {
            ma.ShowErrorMessage("Error", "ID is incorrect!!", "Please Use The 0-9 NUM PAD IF YOU WANT TO ADD -.-");
        } else if (!(nameValidation(TextFieldFirstName.getText()) && nameValidation(TextFieldLastName.getText()) && numValidation(TextFieldContactNum.getText(), 7))) {
            ma.ShowErrorMessage("Error", "Incorrect Inputs", "Please Make Sure That The Name You \n Inserted Contains Text Only");
        } else if (!(nameValidation(TextFieldCity.getText()) && nameValidation(TextFieldStreet.getText()) && numValidation(TextFieldHouseNum.getText()))) {
            ma.ShowErrorMessage("Error", "Incorrect Inputs", "Please Make Sure Of The Address You Inserted \n it must contain A-Z characters only");
        } else if (DatePickerBirthDate.getValue().getYear() < curr.getYear()) {
            ma.ShowErrorMessage("Warning", "You Are Younger Than Now", "Please Make Sure Of The Date You Picked \n  must be yesterday- ");
        } else {
            Patient p = new Patient();
            p.setID(TextFieldID.getText());
            p.setName(TextFieldFirstName.getText() + " " + TextFieldLastName.getText());
            p.setGender(ChoiceGender.getValue().toString());
            String ContactNum = Choice3DigitsNum.getValue().toString() + TextFieldContactNum.getText();
            p.setContactNo(ContactNum);

            Address address = new Address(Integer.parseInt(TextFieldAddressCode.getText()), TextFieldCity.getText(), TextFieldStreet.getText(), Integer.parseInt(TextFieldHouseNum.getText()));
            p.setAddress(address);

            java.sql.Date sqlDate = java.sql.Date.valueOf(DatePickerBirthDate.getValue());
            p.setDate(sqlDate);



            for (Person pe : persons) {
                if (p.getID().equals(pe.getID()))
                    isExist = true;
            }
            if (!isExist) {
                ado.insertAddress(address);
                pdo.insertperson(p);
                pado.insertPatient(p);
                ma.MessageWithoutHeader("successfully added","the patient  has been added successfully");
            } else
                ma.MessageWithoutHeader("Fail To Add", "This Person ID Already Exist In Our System");
        }

    }

    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CssStyler();
        JavafxChoiceFill();
    }

    //Overrided by implementing JavafxPaneHandler
    @Override
    public void JavafxTableFill() {

    }

    @Override
    public void JavafxChoiceFill() {
        GenderList.removeAll();
        String a = "Male";
        String b = "Female";
        ChoiceGender.setValue("Choose Gender");
        GenderList.addAll(a, b);
        ChoiceGender.getItems().addAll(GenderList);

        //3 Digits Num
        ThreeDigitsList.removeAll();
        String _050 = "050";
        String _052 = "052";
        String _054 = "054";
        ThreeDigitsList.addAll(_050, _052, _054);
        Choice3DigitsNum.getItems().addAll(ThreeDigitsList);
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