package Controller;

import DBH.adressDAO;
import DBH.patientDAO;
import DBH.personDAO;
import DBH.therapistDAO;
import Model.Address;
import Model.Patient;
import com.mysql.cj.exceptions.CJConnectionFeatureNotAvailableException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddPatientController implements Initializable, Util.JavafxPaneHandler {

    ObservableList GenderList = FXCollections.observableArrayList();
    ObservableList ThreeDigitsList = FXCollections.observableArrayList();


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

        System.out.println("TEST " + p.toString());

        DBH.adressDAO ado = new adressDAO();
        DBH.personDAO pdo = new personDAO();
        DBH.patientDAO papo = new patientDAO();
        ado.insertAddress(address);
        pdo.insertperson(p);
        papo.insertPatient(p);
    }

    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
}
