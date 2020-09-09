package Controller;

import Model.Address;
import Model.Patient;
import Model.Therapist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;

import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class PatientPaneController implements Initializable,Util.JavafxPaneHandler {

    public ArrayList<Patient> ALPATIENT = new ArrayList<Patient>();
    private ObservableList<Model.Patient> Patients = FXCollections.observableArrayList(ALPATIENT);

    DBH.patientDAO PDH = new DBH.patientDAO();


    @FXML private Button BtnPrint;
    @FXML public TableView<Patient> PatientTable;



    @FXML private TableColumn<Patient,String> ColID;
    @FXML private TableColumn<Patient,String> ColName;
    @FXML private TableColumn<Patient,String> ColAddress;
    @FXML private TableColumn<Patient, String> ColGender;
    @FXML private TableColumn<Patient, Date> ColBdate;

    @FXML
    private ChoiceBox<String> ChoicePatient;

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private BarChart<?, ?> PatientBarChart;

    @FXML
    private Button BtnPatientFile;

    @FXML
    private Button BtnPatientXML;

    @FXML
    private Button BtnRemovePatient;

    @FXML
    private Button BtnLodPatient;

    @FXML
    private Label LblPatientID;


    @FXML
    void OnClickLoadPatient(ActionEvent event) {

        for(Patient p : Patients)
        {
                if(p.getName().equals(ChoicePatient.getValue().toString()))
                    LblPatientID.setText(p.getID());

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

        PDH.removePatientByID(id , addressCode);

        PatientTable.getItems().removeAll(PatientTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    void OnClickToFile(ActionEvent event) {
    System.out.println(1);
    }


    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        try {
            TableInit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JavafxChoiceFill();

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

        Patients.addAll(ALPATIENT);
        Patients=PDH.selectPatients();

    }

    @Override
    public void JavafxChoiceFill() {
        list.removeAll();
 ;

    for(Patient p : Patients)
            list.add(p.getName());
        ChoicePatient.setValue("Choose Patient");
        ChoicePatient.getItems().addAll(list);
    }

    @Override
    public void JavafxDiagramFill() {

       // ObservableList<PieChart.Data> pielist = FXCollections.observableArrayList();
        //pielist.add(new PieChart.Data("Medicine",5));
        //pielist.add(new PieChart.Data("allergy",3));
        //pielist.add(new PieChart.Data("meetings",1));
        //PieChart patientchart = new PieChart(list);
        //patientchart.setTitle("Daily Patient Statics");

    }







}
