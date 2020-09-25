package Controller;


import DBH.patient_allergyDAO;
import DBH.patient_mealDAO;
import DBH.patient_medicineDAO;
import DBH.patient_meetingDAO;
import Model.*;

import Util.FilesHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class PatientPaneController implements Initializable, Util.JavafxPaneHandler {

    public ArrayList<Patient> ALPATIENT = new ArrayList<Patient>();
    private ObservableList<Model.Patient> Patients = FXCollections.observableArrayList(ALPATIENT);

    DBH.patientDAO PDH = new DBH.patientDAO();

    ArrayList<Model.patient_allergy> patient_allergyArrayList = new ArrayList<patient_allergy>();
    ArrayList<Model.patient_meal> patient_mealArrayList = new ArrayList<patient_meal>();
    ArrayList<Model.patient_medicine> patient_medicineArrayList = new ArrayList<patient_medicine>();
    ArrayList<Model.patient_meeting> patient_meetingArrayList = new ArrayList<patient_meeting>();


    DBH.patient_mealDAO pmealDAO = new patient_mealDAO();

    DBH.patient_meetingDAO pmeetingDAO = new patient_meetingDAO();
    DBH.patient_medicineDAO pmedDAO = new patient_medicineDAO();
    DBH.patient_allergyDAO pallergyDAO = new patient_allergyDAO();


    BarChart.Data medicinedata;
    BarChart.Data allergydata;
    BarChart.Data meetingdata;
    BarChart.Data mealdata;


    @FXML
    private Pane parent;

    @FXML
    private Button BtnPrint;
    @FXML
    public TableView<Patient> PatientTable;


    @FXML
    private TableColumn<Patient, String> ColID;
    @FXML
    private TableColumn<Patient, String> ColName;
    @FXML
    private TableColumn<Patient, String> ColAddress;
    @FXML
    private TableColumn<Patient, String> ColGender;
    @FXML
    private TableColumn<Patient, Date> ColBdate;

    @FXML
    private ChoiceBox<String> ChoicePatient;

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private BarChart<?, ?> PatientBarChart;

    @FXML
    private CategoryAxis y;

    @FXML
    private NumberAxis x;
    @FXML
    private Button BtnPatientFile;

    @FXML
    private Button BtnSpecPatientFile;

    @FXML
    private Button BtnPatientXML;

    @FXML
    private Button BtnRemovePatient;

    @FXML
    private Button BtnLodPatient;

    @FXML
    private Label LblPatientID;

    @FXML
    void onSelectPatient(ActionEvent event) throws SQLException {
        for (Patient p : Patients) {
            if (p.getName().equals(ChoicePatient.getValue())) {
                LblPatientID.setText(p.getID());
                meetingdata.setYValue(pmeetingDAO.getCount(LblPatientID.getText()));
                allergydata.setYValue(pallergyDAO.getCount(LblPatientID.getText()));
                mealdata.setYValue(pmealDAO.getCount(LblPatientID.getText()));
                medicinedata.setYValue(pmedDAO.getCount(LblPatientID.getText()));


            }
        }
    }


    @FXML
    void OnClickPatientXML(ActionEvent event) {

    }

    @FXML
    void OnClickPrint(ActionEvent event) {

    }

    @FXML
    void OnClickRemovePatient(ActionEvent event) throws SQLException {
        String id = PatientTable.getSelectionModel().getSelectedItem().getID();
        int addressCode = PatientTable.getSelectionModel().getSelectedItem().getAddress().getAddresscode();

        System.out.println(id);
        System.out.println(addressCode);

        PDH.removePatientByID(id, addressCode);
        PatientTable.getItems().removeAll(PatientTable.getSelectionModel().getSelectedItem());

    }


    @FXML
    void OnClickSpecToFile(ActionEvent event) {
        Util.FilesHandler fh = new FilesHandler();
        String id = PatientTable.getSelectionModel().getSelectedItem().getID();
        for (Model.Patient p : ALPATIENT)
            if (p.getID().equals(id)) {
                fh.SaveSpecificPatient(p);
            }
    }


    @FXML
    void OnClickToFile(ActionEvent event) throws IOException {
        Util.FilesHandler fh = new FilesHandler();
        fh.SavePatient();
    }


    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            TableInit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JavafxDiagramFill();
        JavafxChoiceFill();

        try {
            patient_meetingArrayList = pmeetingDAO.selectAll();
            patient_mealArrayList = pmealDAO.selectAll();
            patient_medicineArrayList = pmedDAO.selectAll();
            patient_allergyArrayList = pallergyDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CssStyler();

    }


    private void TableInit() throws SQLException {
        //Table Init
        ColID.setCellValueFactory(new PropertyValueFactory<Patient, String>("ID"));
        ColName.setCellValueFactory(new PropertyValueFactory<Patient, String>("Name"));
        ColGender.setCellValueFactory(new PropertyValueFactory<Patient, String>("Gender"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<Patient, String>("Address"));
        ColBdate.setCellValueFactory(new PropertyValueFactory<Patient, Date>("date"));
        JavafxTableFill();
        PatientTable.setItems(Patients);


        /*
        //Table Init

        JavafxTableFill();
        NurseTable.setItems(Therapist);
         */
    }

    //Overrided by implementing JavafxPaneHandler
    @Override
    public void JavafxTableFill() throws SQLException {

        Patients = PDH.selectPatients();
        ALPATIENT = PDH.selectAll();

    }

    @Override
    public void JavafxChoiceFill() {
        list.removeAll();
        for (Patient p : Patients)
            list.add(p.getName());
        ChoicePatient.setValue("Choose Patient");
        ChoicePatient.getItems().addAll(list);
    }
    @Override
    public void JavafxDiagramFill() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis("Units",0,10,1);
        NumberAxis asd = new NumberAxis();
        yAxis.setLabel("Count");

        medicinedata = new BarChart.Data(" Type", 0);
        allergydata = new BarChart.Data(" Type", 0);
        meetingdata = new BarChart.Data(" Type", 0);
        mealdata = new BarChart.Data(" Type", 0);

        ObservableList<BarChart.Series> barChartData = FXCollections.observableArrayList(
                new BarChart.Series("Medicines", FXCollections.observableArrayList(
                        medicinedata
                )),
                new BarChart.Series("Allergies", FXCollections.observableArrayList(
                        allergydata
                )),
                new BarChart.Series("Meetings", FXCollections.observableArrayList(
                        meetingdata
                )),
                new BarChart.Series("Meals", FXCollections.observableArrayList(
                        mealdata
                ))
        );
        BarChart chart = new BarChart(xAxis, yAxis, barChartData, 25.0d);
        PatientBarChart.getData().addAll(chart.getData());


    }


    private void CssStyler()
    {
        FXMLLoader loader = new FXMLLoader();

        try {
            loader.load(getClass().getResource("/FXML/Settings.fxml").openStream());

            SettingsController settingsController = loader.getController();

            if (settingsController.getToggleMode()) {
                String css = this.getClass().getResource("/Css/darkmode.css").toExternalForm();
                parent.getStylesheets().add(css);
            } else {
                String css = this.getClass().getResource("/Css/lightmode.css").toExternalForm();
                parent.getStylesheets().add(css);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
