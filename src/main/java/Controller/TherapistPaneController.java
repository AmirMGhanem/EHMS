package Controller;

import Model.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class TherapistPaneController implements Initializable, Util.JavafxPaneHandler {

    private ArrayList<Therapist> ALTHERAPIST = new ArrayList<Therapist>();
    private ObservableList<Therapist> Therapist = FXCollections.observableArrayList();

    public ObservableList<Model.Therapist> getTherapist() {
        return Therapist;
    }

    public void setTherapist(ObservableList<Model.Therapist> therapist) {
        Therapist = therapist;
    }
//Getters and setters and add methods



    @FXML
    public TableView<Therapist> NurseTable;
    @FXML
    private TableColumn<Therapist, String> ColID;
    @FXML
    private TableColumn<Therapist, String> ColName;
    @FXML
    private TableColumn<Therapist, String> ColAddress;
    @FXML
    private TableColumn<Therapist, String> ColGender;
    @FXML
    private TableColumn<Therapist, Date> ColBdate;
    @FXML
    private TableColumn<Model.Therapist, Double> ColExperience;
    @FXML
    private Button BtnRemoveNurse;
    @FXML
    private Button BtnNurseInvest;
    @FXML
    private Button BtnNurseFile;
    @FXML
    private Button BtnNurseXML;



    @FXML
    void OnClickInvestigation(ActionEvent event) {

    }

    @FXML
    void OnClickRemove(ActionEvent event) {

    }

    @FXML
    void OnClickToFile(ActionEvent event) {
    }

    @FXML
    void OnClickToXML(ActionEvent event) {
    }




    public void transferMessage(Therapist t)
    {
        ALTHERAPIST.add(t);
        NurseTable.setItems(Therapist);
        for(Therapist t1 : ALTHERAPIST)
            System.out.println("TESTTTTTTTTTT"+t1.toString());
        JavafxTableFill();
    }



    //Overrided by implementing Initializable

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableInit();
    }

    private void TableInit() {
        //Table Init
        ColID.setCellValueFactory(new PropertyValueFactory<Model.Therapist, String>("ID"));
        ColName.setCellValueFactory(new PropertyValueFactory<Model.Therapist, String>("Name"));
        ColGender.setCellValueFactory(new PropertyValueFactory<Model.Therapist, String>("Gender"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<Model.Therapist, String>("Address"));
        ColBdate.setCellValueFactory(new PropertyValueFactory<Model.Therapist, Date>("date"));
        ColExperience.setCellValueFactory(new PropertyValueFactory<Model.Therapist, Double>("Experience"));
        ColExperience.setEditable(true);
        //add your data to the table here.
        NurseTable.setItems(Therapist);
    }
    //Overrided by implementing JavafxPaneHandler
    @Override
    public void JavafxTableFill() {
        Therapist.addAll(ALTHERAPIST);
    }
    @Override
    public void JavafxChoiceFill() {
    }

    @Override
    public void JavafxDiagramFill() {

    }
}
