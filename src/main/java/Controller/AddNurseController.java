package Controller;

import Controller.TherapistPaneController;
import DBH.adressDAO;
import DBH.personDAO;
import DBH.therapistDAO;
import Model.*;
import Model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddNurseController extends TherapistPaneController implements Initializable, Util.JavafxPaneHandler {
    ObservableList GenderList = FXCollections.observableArrayList();
    ObservableList ThreeDigitsList = FXCollections.observableArrayList();
    static TherapistPaneController therapistPaneController=null;

    @FXML
    private TextField TextFieldFirstName;
    @FXML
    private TextField TextFieldAddressCode;
    @FXML
    private TextField TextFieldLastName;
    @FXML
    private TextField TextFieldID;
    @FXML
    private ChoiceBox<String> ChoiceGender;
    @FXML
    private DatePicker DatePickerBirthdate;
    @FXML
    private DatePicker DatePickerWorkDateStart;
    @FXML
    private TextField TextFieldContactNum;
    @FXML
    private ChoiceBox<?> Choice3DigitsNum;
    @FXML
    private TextField TextFieldCity;
    @FXML
    private TextField TextFieldStreet;
    @FXML
    private TextField TextFieldHouseNum;
    @FXML
    private Button BtnAdd;

    @FXML
    void OnClickAdd(ActionEvent event) throws IOException, SQLException {
        Therapist t = new Therapist();

        t.setID(TextFieldID.getText());
        t.setName(TextFieldFirstName.getText() + " " + TextFieldLastName.getText());
        t.setGender(ChoiceGender.getValue().toString());
        String ContactNum = Choice3DigitsNum.getValue().toString() + TextFieldContactNum.getText();
        t.setContactNo(ContactNum);
        Address address = new Address(Integer.parseInt(TextFieldAddressCode.getText()),TextFieldCity.getText(), TextFieldStreet.getText(), Integer.parseInt(TextFieldHouseNum.getText()));
        t.setAddress(address);

        java.sql.Date sqlDate = java.sql.Date.valueOf(DatePickerBirthdate.getValue());
        t.setDate(sqlDate);

        java.sql.Date sqlWorkDate = java.sql.Date.valueOf(DatePickerWorkDateStart.getValue());
        t.setWorkDateStart(sqlWorkDate);

        System.out.println("Constructor TESTER TOSTRING "+t.toString());
        sendTherapist(t);

        DBH.adressDAO ado = new adressDAO();
        DBH.personDAO pdo = new personDAO();
        DBH.therapistDAO tpo = new therapistDAO();
        ado.insertAddress(address);
        pdo.insertperson(t);
        tpo.insertherapist(t);
    }


    public void sendTherapist(Therapist t) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/TherapistPane.fxml"));
        Parent root = loader.load();
        //Get controller of Therapist
        therapistPaneController = loader.getController();
        therapistPaneController.transferMessage(t); //ERROR
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
