package Controller;

import DBH.*;
import Model.Address;
import Model.Medicine;
import Model.Therapist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MedicineCRUDController implements Initializable,Util.JavafxPaneHandler {

    @FXML private TextField TextFieldAddPatientID;
    @FXML private TextField TextFieldAddName;
    @FXML private ChoiceBox<String> ChoiceAddType;
    @FXML private Spinner<?> AddTimesPerDay;
    @FXML private Button BtnAdd;
    @FXML private ChoiceBox<?> ChoiceEditCurrName;
    @FXML private Spinner<?> EditCurrTimesPerDay;
    @FXML private TextField EditNewName;
    @FXML private ChoiceBox<?> ChoiceEditCurrType;
    @FXML private ChoiceBox<?> ChoiceEditNewType;
    @FXML private Spinner<?> EditNewTimesPerDay;
    @FXML private Button BtnEditSubmit;
    @FXML private ChoiceBox<?> ChoiceRemoveName;
    @FXML private Button BtnRemove;
    @FXML private TextArea TextArea;


    @FXML
    void OnClickBtnAdd(ActionEvent event) throws SQLException {

        Medicine m = new Medicine();

        m.setName(TextFieldAddName.getText());
        m.setType(ChoiceAddType.getValue());
        m.setTimesPerDay(1);

        DBH.medicineDAO mdo = new medicineDAO();
        mdo.insertMedicine(m);

        DBH.patient_medicineDAO pmdo = new patient_medicineDAO();
        pmdo.insertToPatient_Medicine(TextFieldAddPatientID.getText() , m.getName());
    }

    @FXML
    void OnClickBtnEditSubmit(ActionEvent event) {

    }

    @FXML
    void OnClickBtnRemove(ActionEvent event) {

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
        ObservableList TypeList = FXCollections.observableArrayList();

        String o = "Ointment";
        String c = "Capsules";

        TypeList.addAll(o, c);
        ChoiceAddType.getItems().addAll(TypeList);
    }


    @Override
    public void JavafxDiagramFill() {

    }
}