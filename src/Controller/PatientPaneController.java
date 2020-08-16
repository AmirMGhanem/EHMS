package Controller;

import Model.Address;
import Model.Patient;
import Model.Therapist;
import Util.JavafxPaneHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class PatientPaneController implements Initializable,Util.JavafxPaneHandler {

    public ArrayList<Patient> ALPATIENT = new ArrayList<Patient>();
    private ObservableList<Model.Patient> Patients = FXCollections.observableArrayList();



    @FXML private Button BtnPrint;
    @FXML private TableView<Patient> PatientTable;
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

    }

    @FXML
    void OnClickPatientXML(ActionEvent event) {

    }

    @FXML
    void OnClickPrint(ActionEvent event) {

    }

    @FXML
    void OnClickRemovePatient(ActionEvent event) {

    }

    @FXML
    void OnClickToFile(ActionEvent event) {
    System.out.println(1);
    }


    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        JavafxChoiceFill();

        Patient p = new Patient();
        p.setID("123");
        p.setName("Alam");
        p.setGender("Male");
        p.setAddress(new Address("Haifa"));
        p.setDate(new Date());
        ALPATIENT.add(p);
        JavafxTableFill();
    }






    private void TableInit()
    {
        //Table Init
        ColID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ColGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        ColBdate.setCellValueFactory(new PropertyValueFactory<>("date"));

        //add your data to the table here.
        PatientTable.setItems(Patients);
    }

    //Overrided by implementing JavafxPaneHandler
    @Override
    public void JavafxTableFill() {

        TableInit();
        Patients.addAll(ALPATIENT);

    }

    @Override
    public void JavafxChoiceFill() {
        list.removeAll();
        String a="Amir";
        String b="Alam";
    
        list.addAll(a,b);
        ChoicePatient.setValue("Choose Patient");
        ChoicePatient.getItems().addAll(list);
    }

    @Override
    public void JavafxDiagramFill() {

    }







}
