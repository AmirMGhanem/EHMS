package Controller;

import DBH.*;
import Model.*;
import Util.JavafxPaneHandler;
import Util.MessageAlerter;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.util.*;


public class MeetingController implements Initializable, JavafxPaneHandler {

    ArrayList<Address> addressArrayList = new ArrayList<Address>();
    ObservableList addressObservable = FXCollections.observableArrayList();
    DBH.adressDAO aDAO = new adressDAO();

    ArrayList<Meeting> meetingArrayList = new ArrayList<Meeting>();
    ObservableList meetingObservable = FXCollections.observableArrayList();
    DBH.meetingDAO mDAO = new meetingDAO();

    ArrayList<Patient> patientArrayList = new ArrayList<Patient>();
    ObservableList patientObservable = FXCollections.observableArrayList();
    DBH.patientDAO pDAO = new patientDAO();

    ArrayList<patient_meeting> patient_meetingArrayList = new ArrayList<patient_meeting>();
    ObservableList patient_meetingObservable = FXCollections.observableArrayList();
    DBH.patient_meetingDAO pmdo = new patient_meetingDAO();

    MessageAlerter ma = new MessageAlerter();

    @FXML private Pane parent;
    @FXML private DatePicker DatePicker;
    @FXML private Label LabelHR;
    @FXML private Button BtnShowAll;
    @FXML private Label LabelMin;
    @FXML private Pane p11AM;
    @FXML private Pane p12PM;
    @FXML private Pane p1PM;
    @FXML private Pane p2PM;
    @FXML private Pane p3PM;
    @FXML private Pane p4PM;
    @FXML private Pane p5PM;
    @FXML private Pane p6PM;
    @FXML private Pane p7PM;
    @FXML private Pane p8PM;
    @FXML private Pane p7Am;
    @FXML private Pane p8AM;
    @FXML private Pane p9AM;
    @FXML private Pane p10AM;
    @FXML private Pane p00;
    @FXML private Pane p15;
    @FXML private Pane p30;
    @FXML private Pane p45;
    @FXML private TableView<Meeting> TableMeeting;
    @FXML private TableColumn<Meeting, Number> ColNum;
    @FXML private TableColumn<Meeting, String> ColName;
    @FXML private TableColumn<Meeting, String> ColCity;
    @FXML private TableColumn<Meeting, String> ColStreet;
    @FXML private TableColumn<Meeting, Number> ColHouseNum;
    @FXML private TableColumn<Meeting, Date> ColDate;
    @FXML private TableColumn<Meeting, String> ColTime;
    @FXML private Button BtnAddMeeting;
    @FXML private ChoiceBox<String> ChoicePatient;
    @FXML private Button BtnDetach;
    @FXML private Label LabelPatientID;
    @FXML private Button BtnAttach;
    @FXML private ListView<String> ListView;
    @FXML private Label LabelUpdate;
    @FXML private Button BtnRemove;
    @FXML private TextField TextFieldAddressCode;
    @FXML private TextField TextFieldCity;
    @FXML private TextField TextFieldStreet;
    @FXML private TextField TextFieldHouseNum;
    @FXML private TextField TextFieldName;

    ArrayList<Pane> paneArrayList = new ArrayList<Pane>();


    @FXML
    void onSelect(ActionEvent event) {
        String Patientname = ChoicePatient.getSelectionModel().getSelectedItem();
        for (Patient p : patientArrayList)
            if (Patientname.equals(p.getName()))
                LabelPatientID.setText(p.getID());

        meetingObservable.removeAll();
        TableMeeting.getItems().clear();
        for (patient_meeting pm : patient_meetingArrayList) {
            for (Meeting m : meetingArrayList) {
                if (pm.getPatientid().equals(LabelPatientID.getText()))
                    if (pm.getMeetingnum() == m.getNum())
                        meetingObservable.add(m);
            }
        }


    }

    @FXML
    void OnClickBtnShowAll(ActionEvent event) throws SQLException {
        TableInit();

    }


    @FXML
    void OnClickBtnAddMeeting(ActionEvent event) throws SQLException {
        Date d = java.sql.Date.valueOf(DatePicker.getValue());
        Address a = new Address();
        a.setAddresscode(Integer.parseInt(TextFieldAddressCode.getText()));
        a.setCity(TextFieldCity.getText());
        a.setStreet(TextFieldStreet.getText());
        a.setHouseNum(Integer.parseInt(TextFieldHouseNum.getText()));
        aDAO.insertAddress(a);
        Meeting m = new Meeting();
        m.setName(TextFieldName.getText());
        m.setAddress(a);
        m.setDate(d);
        m.setTime(Integer.parseInt(LabelHR.getText()), Integer.parseInt(LabelMin.getText()));
        mDAO.insertMeeting(m);
        TableInit();
        ma.MessageWithoutHeader("Added", "Metting Added Successfully :)");
    }

    @FXML
    void OnClickBtnAttach(ActionEvent event) {

        int MeetingNumForAttach = TableMeeting.getSelectionModel().getSelectedItem().getNum();
        String PatientidForAttach = LabelPatientID.getText();
        try {
            if (pmdo.insertToPatient_meeting(PatientidForAttach, MeetingNumForAttach) == 0) {
                LabelUpdate.setText("Unsuccessfully");
            } else {
                LabelUpdate.setText("Successfully Added");
                listinit();
            }
        } catch (SQLException e) {
            ma.ShowErrorMessage("Unexpected Error", "Fail To Add", "Meeting Already Added");
        }
        ManualInit();
    }


    @FXML
    void OnClickBtnDetach(ActionEvent event) throws SQLException {
        int meetingNum = TableMeeting.getSelectionModel().getSelectedItem().getNum();
        String patientid = LabelPatientID.getText();
        for (patient_meeting pm : patient_meetingArrayList) {
            if (pm.getMeetingnum() == meetingNum && pm.getPatientid().equals(patientid)) {
                pmdo.removeByMeetingNum(pm);
                TableMeeting.getItems().remove(TableMeeting.getSelectionModel().getSelectedItem());
                ManualInit();
            }
        }
    }

    @FXML
    void OnClickBtnRemove(ActionEvent event) throws SQLException {
        Meeting m = TableMeeting.getSelectionModel().getSelectedItem();
        TableMeeting.getItems().remove(TableMeeting.getSelectionModel().getSelectedItem());
        for (patient_meeting pm : patient_meetingArrayList)
            if (pm.getMeetingnum() == m.getNum())
                pmdo.removeByMeetingNum(pm);
        mDAO.removeMeeting(m);
        listinit();
    }

    @FXML
    void OnClickp00(MouseEvent event) {

        p00.setStyle("-fx-background-color:  #ff6565");
        LabelMin.setText("00");

        p15.setStyle("-fx-background-color:  #a7a7a7");
        p30.setStyle("-fx-background-color:  #a7a7a7");
        p45.setStyle("-fx-background-color:  #a7a7a7");

    }

    @FXML
    void OnClickp15(MouseEvent event) {

        p15.setStyle("-fx-background-color:  #ff6565");
        LabelMin.setText("15");

        p00.setStyle("-fx-background-color:  #a7a7a7");
        p30.setStyle("-fx-background-color:  #a7a7a7");
        p45.setStyle("-fx-background-color:  #a7a7a7");

    }

    @FXML
    void OnClickp30(MouseEvent event) {

        p30.setStyle("-fx-background-color:  #ff6565");
        LabelMin.setText("30");
        p00.setStyle("-fx-background-color:  #a7a7a7");
        p15.setStyle("-fx-background-color:  #a7a7a7");
        p45.setStyle("-fx-background-color:  #a7a7a7");
    }

    @FXML
    void OnClickp45(MouseEvent event) {

        p45.setStyle("-fx-background-color:  #ff6565");
        LabelMin.setText("45");

        p00.setStyle("-fx-background-color:  #a7a7a7");
        p15.setStyle("-fx-background-color:  #a7a7a7");
        p30.setStyle("-fx-background-color:  #a7a7a7");

    }

    @FXML
    void OnClickp10AM(MouseEvent event) {

        p10AM.setStyle("-fx-background-color:  #59b7ff");
        LabelHR.setText("10");

        for (Pane p : paneArrayList)
            if (!p.equals(p10AM))
                p.setStyle("-fx-background-color:  #a7a7a7");

    }

    @FXML
    void OnClickp11AM(MouseEvent event) {
        p11AM.setStyle("-fx-background-color:  #59b7ff");
        LabelHR.setText("11");

        for (Pane p : paneArrayList)
            if (!p.equals(p11AM))
                p.setStyle("-fx-background-color:  #a7a7a7");
    }

    @FXML
    void OnClickp12PM(MouseEvent event) {
        p12PM.setStyle("-fx-background-color:  #59b7ff");
        LabelHR.setText("12");

        for (Pane p : paneArrayList)
            if (!p.equals(p12PM))
                p.setStyle("-fx-background-color:  #a7a7a7");
    }

    @FXML
    void OnClickp1PM(MouseEvent event) {
        p1PM.setStyle("-fx-background-color:  #59b7ff");
        LabelHR.setText("13");

        for (Pane p : paneArrayList)
            if (!p.equals(p1PM))
                p.setStyle("-fx-background-color:  #a7a7a7");
    }

    @FXML
    void OnClickp2PM(MouseEvent event) {
        p2PM.setStyle("-fx-background-color:  #59b7ff");
        LabelHR.setText("14");

        for (Pane p : paneArrayList)
            if (!p.equals(p2PM))
                p.setStyle("-fx-background-color:  #a7a7a7");
    }

    @FXML
    void OnClickp3PM(MouseEvent event) {
        p3PM.setStyle("-fx-background-color:  #59b7ff");
        LabelHR.setText("15");

        for (Pane p : paneArrayList)
            if (!p.equals(p3PM))
                p.setStyle("-fx-background-color:  #a7a7a7");
    }

    @FXML
    void OnClickp4PM(MouseEvent event) {
        p4PM.setStyle("-fx-background-color:  #59b7ff");
        LabelHR.setText("16");

        for (Pane p : paneArrayList)
            if (!p.equals(p4PM))
                p.setStyle("-fx-background-color:  #a7a7a7");
    }

    @FXML
    void OnClickp5PM(MouseEvent event) {
        p5PM.setStyle("-fx-background-color:  #59b7ff");
        LabelHR.setText("17");

        for (Pane p : paneArrayList)
            if (!p.equals(p5PM))
                p.setStyle("-fx-background-color:  #a7a7a7");
    }

    @FXML
    void OnClickp6PM(MouseEvent event) {
        p6PM.setStyle("-fx-background-color:  #59b7ff");
        LabelHR.setText("18");

        for (Pane p : paneArrayList)
            if (!p.equals(p6PM))
                p.setStyle("-fx-background-color:  #a7a7a7");
    }

    @FXML
    void OnClickp7AM(MouseEvent event) {
        p7Am.setStyle("-fx-background-color:  #59b7ff");
        LabelHR.setText("7");

        for (Pane p : paneArrayList)
            if (!p.equals(p7Am))
                p.setStyle("-fx-background-color:  #a7a7a7");
    }

    @FXML
    void OnClickp7PM(MouseEvent event) {
        p7PM.setStyle("-fx-background-color:  #59b7ff");
        LabelHR.setText("19");

        for (Pane p : paneArrayList)
            if (!p.equals(p7PM))
                p.setStyle("-fx-background-color:  #a7a7a7");
    }

    @FXML
    void OnClickp8AM(MouseEvent event) {
        p8AM.setStyle("-fx-background-color:  #59b7ff");
        LabelHR.setText("8");

        for (Pane p : paneArrayList)
            if (!p.equals(p8AM))
                p.setStyle("-fx-background-color:  #a7a7a7");
    }

    @FXML
    void OnClickp8PM(MouseEvent event) {
        p8PM.setStyle("-fx-background-color:  #59b7ff");
        LabelHR.setText("20");

        for (Pane p : paneArrayList)
            if (!p.equals(p8PM))
                p.setStyle("-fx-background-color:  #a7a7a7");
    }

    @FXML
    void OnClickp9AM(MouseEvent event) {
        p9AM.setStyle("-fx-background-color:  #59b7ff");
        LabelHR.setText("9");

        for (Pane p : paneArrayList)
            if (!p.equals(p9AM))
                p.setStyle("-fx-background-color:  #a7a7a7");
    }


    public void TableInit() throws SQLException {


        ColCity.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getAddress().getCity()));
        ColStreet.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getAddress().getStreet()));
        ColHouseNum.setCellValueFactory(CellData -> new SimpleIntegerProperty(CellData.getValue().getAddress().getHouseNum()));
        ColDate.setCellValueFactory(new PropertyValueFactory<Meeting, Date>("date"));
        ColTime.setCellValueFactory(new PropertyValueFactory<Meeting, String>("Time"));
        ColNum.setCellValueFactory(new PropertyValueFactory<Meeting, Number>("num"));
        ColName.setCellValueFactory(new PropertyValueFactory<Meeting, String>("name"));
        //add your data to the table here.
        JavafxTableFill();
        TableMeeting.setItems(meetingObservable);


    }


    public void listinit() throws SQLException {
        patient_meetingArrayList = pmdo.selectAll();
        patient_meetingObservable.setAll(patient_meetingArrayList);
        ListView.getItems().removeAll();
        ListView.getItems().setAll(patient_meetingObservable);

    }


    //INIT AND JAVAFX HANDLERER IMPLEMENETATION
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            TableInit();
            JavafxChoiceFill();
            listinit();
            CssStyler();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        LabelMin.setText("00");

        paneArrayList.add(p1PM);
        paneArrayList.add(p2PM);
        paneArrayList.add(p3PM);
        paneArrayList.add(p4PM);
        paneArrayList.add(p5PM);
        paneArrayList.add(p6PM);
        paneArrayList.add(p7PM);
        paneArrayList.add(p8PM);
        paneArrayList.add(p12PM);
        paneArrayList.add(p9AM);
        paneArrayList.add(p10AM);
        paneArrayList.add(p11AM);
        paneArrayList.add(p7Am);
        paneArrayList.add(p8AM);


        ManualInit();

    }

    private void ManualInit() {
        try {
            patient_meetingArrayList = pmdo.selectAll();
            patient_meetingObservable.setAll(patient_meetingArrayList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void JavafxTableFill() throws SQLException {
        meetingArrayList = mDAO.selectAll();
        meetingObservable.setAll(meetingArrayList);

    }

    @Override
    public void JavafxChoiceFill() throws SQLException {

        patientArrayList = pDAO.selectAll();
        for (Patient p : patientArrayList)
            patientObservable.addAll(p.getName());
        ChoicePatient.getItems().clear();
        ChoicePatient.getItems().addAll(patientObservable);


    }

    @Override
    public void JavafxDiagramFill() throws IOException {

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
