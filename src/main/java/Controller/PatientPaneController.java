package Controller;

import Model.Address;
import Model.Patient;
import Model.Therapist;
import Util.FilesHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

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
    void OnClickLoadPatient(ActionEvent event) {

        for (Patient p : Patients) {
            if (p.getName().equals(ChoicePatient.getValue().toString()))
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
        ;

        for (Patient p : Patients)
            list.add(p.getName());
        ChoicePatient.setValue("Choose Patient");
        ChoicePatient.getItems().addAll(list);
    }

    @Override
    public void JavafxDiagramFill() {
        String[] month = {"January","Last-Month","Current"};

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(month));
        NumberAxis yAxis = new NumberAxis("Units", 0.0d, 3000.0d, 1000.0d);
        ObservableList<BarChart.Series> barChartData = FXCollections.observableArrayList(
                new BarChart.Series("Medicines", FXCollections.observableArrayList(

                        new BarChart.Data(month[0], 5),
                        new BarChart.Data(month[1], 0d),
                        new BarChart.Data(month[2], 3d)


                )),
                new BarChart.Series("Allergies", FXCollections.observableArrayList(
                        new BarChart.Data(month[0], 2d),
                        new BarChart.Data(month[1], 1d),
                        new BarChart.Data(month[2], 0d)


                )),
                new BarChart.Series("Meetings", FXCollections.observableArrayList(
                        new BarChart.Data(month[0], 1d),
                        new BarChart.Data(month[1], 1d),
                        new BarChart.Data(month[2], 2d)


                )),
                new BarChart.Series("Requests", FXCollections.observableArrayList(
                        new BarChart.Data(month[0], 8d),
                        new BarChart.Data(month[1], 1d),
                        new BarChart.Data(month[2], 0d)


                ))
        );
        BarChart chart = new BarChart(xAxis, yAxis, barChartData, 25.0d);
        PatientBarChart.getData().addAll(chart.getData());


    }


}
