package Controller;

import DBH.*;
import Model.Medicine;
import Model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class MedicineCRUDController implements Initializable,Util.JavafxPaneHandler {

    @FXML private ChoiceBox<String> ChoiceAddPatientsID;
    @FXML private TextField TextFieldAddName;
    @FXML private ChoiceBox<String> ChoiceAddType;
    @FXML private Spinner<Integer> AddTimesPerDay;
    @FXML private Button BtnAdd;
    @FXML private ChoiceBox<String> ChoiceEditCurrName;
    @FXML private Spinner<Integer> EditCurrTimesPerDay;
    @FXML private TextField EditNewName;
    @FXML private ChoiceBox<String> ChoiceEditCurrType;
    @FXML private ChoiceBox<?> ChoiceEditNewType;
    @FXML private Spinner<Integer> EditNewTimesPerDay;
    @FXML private Button BtnEditSubmit;
    @FXML private ChoiceBox<String> ChoiceRemoveName;
    @FXML private Button BtnRemove;
    @FXML private TextArea TextArea;
    @FXML private TextField TextFieldMedicineNum;
    @FXML private TextField TextFieldEditMedicineNum;
    @FXML private ChoiceBox<String> ChoiceEditPatientID;
    @FXML private ChoiceBox<String> ChoiceNewEditPatientID;
    @FXML private TextField TextFieldEditNewMedicineNum;
    @FXML private TextField TextFieldRemoveMedicineNum;
    @FXML private ChoiceBox<String> ChoiceRemovePatientID;

    DBH.medicineDAO mbh = new DBH.medicineDAO();

    @FXML public void onEnterE(ActionEvent ae) throws SQLException {

        ArrayList<Medicine> mlist = new ArrayList<Medicine>();
        int medicineNum = Integer.parseInt(TextFieldEditMedicineNum.getText());
        ObservableList list = FXCollections.observableArrayList();

        mlist = mbh.selectAll();

        for(Medicine m : mlist){
            if(medicineNum == m.getMedicineNum()) {
                list.add(m.getName());
                ChoiceEditCurrType.getItems().add(m.getType());
            }
        }

        ChoiceEditCurrName.getItems().addAll(list);

        ChoiceNewEditPatientID.getItems().add(ChoiceEditPatientID.getValue());
        TextFieldEditNewMedicineNum.setText(TextFieldEditMedicineNum.getText());
    }


    @FXML
    void onEnterR(ActionEvent event) throws SQLException {

        ArrayList<Medicine> mlist = new ArrayList<Medicine>();
        int medicineNum = Integer.parseInt(TextFieldRemoveMedicineNum.getText());
        ObservableList list = FXCollections.observableArrayList();

        mlist = mbh.selectAll();

        for(Medicine m : mlist){
            if(medicineNum == m.getMedicineNum()){
                ChoiceRemoveName.getItems().add(m.getName());
            }
        }
    }


    @FXML
    void OnClickBtnAdd(ActionEvent event) throws SQLException {

        Medicine m = new Medicine();

        m.setMedicineNum(Integer.parseInt(TextFieldMedicineNum.getText()));
        m.setName(TextFieldAddName.getText());
        m.setType(ChoiceAddType.getValue());
        m.setTimesPerDay(AddTimesPerDay.getValue());

        DBH.medicineDAO mdo = new medicineDAO();
        mdo.insertMedicine(m);

        DBH.patient_medicineDAO pmdo = new patient_medicineDAO();


        pmdo.insertToPatient_Medicine(ChoiceAddPatientsID.getValue().toString() , m.getMedicineNum());

        System.out.println(m.toString());
    }

    @FXML
    void OnClickBtnEditSubmit(ActionEvent event) throws SQLException {
        Medicine m = new Medicine();

        m.setMedicineNum(Integer.parseInt(TextFieldEditMedicineNum.getText()));
        m.setName(EditNewName.getText());
        m.setType(ChoiceEditNewType.getValue().toString());
        m.setTimesPerDay(EditNewTimesPerDay.getValue());

        DBH.medicineDAO mdo = new medicineDAO();
        mdo.UpdateMedicine(m);
    }

    @FXML
    void OnClickBtnRemove(ActionEvent event) throws SQLException {

        DBH.patient_medicineDAO pmdo = new DBH.patient_medicineDAO();
        pmdo.removeByMedicineNum(Integer.parseInt(TextFieldRemoveMedicineNum.getText()));

        DBH.medicineDAO mdo = new medicineDAO();
        mdo.removeMedicineByID(Integer.parseInt(TextFieldRemoveMedicineNum.getText()));
    }

    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            JavafxChoiceFill();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Overrided by implementing JavafxPaneHandler
    @Override
    public void JavafxTableFill() {

    }

    @Override
    public void JavafxChoiceFill() throws SQLException {
        ObservableList TypeList = FXCollections.observableArrayList();

        String oi = "Ointment";
        String ca = "Capsules";
        String li = "Liquid";
        String po = "Powder";

        TypeList.addAll(oi, ca, li, po);
        ChoiceAddType.getItems().addAll(TypeList);
        ChoiceEditNewType.getItems().addAll(TypeList);

        ObservableList IDList = FXCollections.observableArrayList();
        DBH.patientDAO pdao = new DBH.patientDAO();
        IDList = pdao.selectPatients();

        Patient p = new Patient();

        for(int i=0 ; i<IDList.size() ; i++){
            p = (Patient) IDList.get(i);
            ChoiceAddPatientsID.getItems().add(p.getID());
            ChoiceEditPatientID.getItems().add(p.getID());
            ChoiceRemovePatientID.getItems().add(p.getID());
        }
    }


    @Override
    public void JavafxDiagramFill() {

    }
}