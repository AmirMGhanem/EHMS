package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportsController implements Initializable,Util.JavafxPaneHandler {

    @FXML
    private TextField TextFieldID;

    @FXML
    private RadioButton RadioTherapist;

    @FXML
    private RadioButton RadioPatient;

    @FXML
    private RadioButton RadioStaff;

    @FXML
    private Button BtnPreview;

    @FXML
    private Button BtnClear;

    @FXML
    private CheckBox CheckBoxMedicine;

    @FXML
    private CheckBox CheckBoxAllergies;

    @FXML
    private CheckBox CheckBoxMeals;

    @FXML
    private CheckBox CheckBoxRequests;

    @FXML
    private CheckBox CheckBoxPresonalInfo;

    @FXML
    private CheckBox CheckBoxAddress;

    @FXML
    private CheckBox CheckBoxHR;

    @FXML
    private CheckBox CheckBoxSchedule;

    @FXML
    private Button BtnDeSelectAll;

    @FXML
    private Button BtnSelectAll;

    @FXML
    private TextArea TextAreaFile;

    @FXML
    private TextArea TextAreaXML;

    @FXML
    private TextArea TextAreaHTML;

    @FXML
    void OnClickClear(ActionEvent event) {

    }

    @FXML
    void OnClickDeSelectAll(ActionEvent event) {

    }

    @FXML
    void OnClickPreview(ActionEvent event) {

    }

    @FXML
    void OnClickSelectAll(ActionEvent event) {

    }

    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
