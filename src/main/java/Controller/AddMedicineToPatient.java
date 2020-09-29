package Controller;

import Model.Medicine;
import Model.Patient;
import Model.patient_medicine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddMedicineToPatient {

    ArrayList<Patient> patientArrayList = new ArrayList<Patient>();
    ArrayList<Medicine> medicineArraylist = new ArrayList<Medicine>();
    ArrayList<Model.patient_medicine> patient_medicineArrayList= new ArrayList<patient_medicine>();



    @FXML
    private Spinner<Integer> AddTimesPerDay;

    @FXML
    private Spinner<Integer> AddDuration;

    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnCancel;

    @FXML
    void onClickBtnAdd(ActionEvent event) {


    }

    @FXML
    void onClickBtnCancel(ActionEvent event) {

    }

}
