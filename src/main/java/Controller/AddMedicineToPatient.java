package Controller;

import DBH.medicineDAO;
import DBH.patientDAO;
import DBH.patient_medicineDAO;
import Model.Medicine;
import Model.Patient;
import Model.patient_medicine;
import Util.MessageAlerter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AddMedicineToPatient implements Initializable {
    MessageAlerter messageAlerter = new MessageAlerter();
    ArrayList<Patient> patientArrayList = new ArrayList<Patient>();
    ArrayList<Medicine> medicineArraylist = new ArrayList<Medicine>();
    ArrayList<Model.patient_medicine> patient_medicineArrayList = new ArrayList<patient_medicine>();

    DBH.patientDAO pdao = new patientDAO();
    DBH.medicineDAO mdao = new medicineDAO();
    DBH.patient_medicineDAO pmdao = new patient_medicineDAO();


    @FXML
    private Spinner<Integer> AddTimesPerDay;

    @FXML
    private Spinner<Integer> AddDuration;

    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnCancel;

    @FXML
    void onClickBtnAdd(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.load(getClass().getResource("/FXML/MedicinePane.fxml").openStream());
        MedicinePaneController medicinePaneController = loader.getController();
        int mednum = MedicinePaneController.getMednum();
        String patientid = MedicinePaneController.getPatientID();
        Model.patient_medicine pm = null;

        for (Patient p : patientArrayList)
            if (p.getID().equals(patientid))
                for (Medicine m : medicineArraylist)
                    if (m.getMedicineNum() == mednum)
                        pm = new patient_medicine(patientid, mednum, AddTimesPerDay.getValue(), AddDuration.getValue());
        if (pmdao.insertToPatient_Medicine(pm) == 0)
            messageAlerter.MessageWithoutHeader("Unsuccessfully!!!", "Cannot be attached");
        else
            messageAlerter.MessageWithoutHeader("Attach succeed", "Successfully Attached");

        ((Node)(event.getSource())).getScene().getWindow().hide();

    }

    @FXML
    void onClickBtnCancel(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            patientArrayList = pdao.selectAll();
            medicineArraylist = mdao.selectAll();
            patient_medicineArrayList = pmdao.selectAll();
        } catch (Exception e) {
            messageAlerter.ShowErrorMessage("Error", e.toString(), "SQL Exception");
        }

    }
}
