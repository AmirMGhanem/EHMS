package Controller;
import DBH.adressDAO;
import DBH.personDAO;
import DBH.therapistDAO;
import Model.*;
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
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AddNurseController extends TherapistPaneController implements Initializable, Util.JavafxPaneHandler, IValidations {
    ObservableList GenderList = FXCollections.observableArrayList();
    ObservableList ThreeDigitsList = FXCollections.observableArrayList();
    static TherapistPaneController therapistPaneController = null;
    MessageAlerter ma = new MessageAlerter();
    ArrayList<Person> persons = new ArrayList<>();
    DBH.adressDAO ado = new adressDAO();
    DBH.personDAO pdo = new personDAO();
    DBH.therapistDAO tpo = new therapistDAO();
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
        persons = pdo.selectAll();
        boolean isExist = false;
        Date curr = new Date();
        if (!(numValidation(TextFieldID.getText(), 9))) {
            ma.ShowErrorMessage("Error", "ID is incorrect!!", "Please Use The 0-9 NUM PAD IF YOU WANT TO ADD -.-");
        } else if (!(nameValidation(TextFieldFirstName.getText()) && nameValidation(TextFieldLastName.getText()) && numValidation(TextFieldContactNum.getText(),7))) {
            ma.ShowErrorMessage("Error", "Incorrect Inputs", "Please Make Sure That The Name You \n Inserted Contains Text Only");
        } else if (!(nameValidation(TextFieldCity.getText()) && nameValidation(TextFieldStreet.getText()) && numValidation(TextFieldHouseNum.getText()))) {
            ma.ShowErrorMessage("Error", "Incorrect Inputs", "Please Make Sure Of The Address You Inserted \n it must contain A-Z characters only");
        } else if (DatePickerBirthdate.getValue().getYear() < curr.getYear()) {
            ma.ShowErrorMessage("Warning", "You Are Younger Than Now", "Please Make Sure Of The Date You Picked \n  must be yesterday- ");
        } else if (DatePickerWorkDateStart.getValue().getYear() < curr.getYear()) {
            ma.ShowErrorMessage("Warning", "You Cant Gain Experience Tomorrow", "Please Make Sure Of The Work Start Date You Picked \n  must be yesterday- ");
        } else {
            Therapist t = new Therapist();
            t.setID(TextFieldID.getText());
            t.setName(TextFieldFirstName.getText() + " " + TextFieldLastName.getText());
            t.setGender(ChoiceGender.getValue().toString());
            String ContactNum = Choice3DigitsNum.getValue().toString() + TextFieldContactNum.getText();
            t.setContactNo(ContactNum);
            Address address = new Address(Integer.parseInt(TextFieldAddressCode.getText()), TextFieldCity.getText(), TextFieldStreet.getText(), Integer.parseInt(TextFieldHouseNum.getText()));
            t.setAddress(address);
            java.sql.Date sqlDate = java.sql.Date.valueOf(DatePickerBirthdate.getValue());
            t.setDate(sqlDate);
            java.sql.Date sqlWorkDate = java.sql.Date.valueOf(DatePickerWorkDateStart.getValue());
            t.setWorkDateStart(sqlWorkDate);
            System.out.println("Constructor TESTER TOSTRING " + t.toString());
            for (Person pe : persons) {
                if (t.getID().equals(pe.getID()))
                    isExist = true;
            }
            if (!isExist) {
                ado.insertAddress(address);
                pdo.insertperson(t);
                tpo.insertherapist(t);
            } else
                ma.MessageWithoutHeader("Fail To Add", "This Person ID Already Exist In Our System");
        }
    }

    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JavafxChoiceFill();
        CssStyler();
    }

    //Overrided by implementing JavafxPaneHandler
    @Override
    public void JavafxTableFill() {

    }

    @Override
    public void JavafxChoiceFill() {
        String a = "Male";
        String b = "Female";
        ChoiceGender.setValue("Choose Gender");
        GenderList.setAll(a, b);
        ChoiceGender.setItems(GenderList);
        //3 Digits Num
        String _050 = "050";
        String _052 = "052";
        String _054 = "054";
        ThreeDigitsList.setAll(_050, _052, _054);
        Choice3DigitsNum.setItems(ThreeDigitsList);
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