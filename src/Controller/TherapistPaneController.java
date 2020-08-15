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
import java.util.Date;
import java.util.ResourceBundle;

public class TherapistPaneController implements Initializable,Util.JavafxPaneHandler {

    @FXML
    private TableView<Therapist> NurseTable;

    @FXML
    private TableColumn<Therapist,String> ColID;

    @FXML
    private TableColumn<Therapist,String> ColName;

    @FXML
    private TableColumn<Therapist,String> ColAddress;

    @FXML
    private TableColumn<Therapist, String> ColGender;

    @FXML
    private TableColumn<Therapist, Date> ColBdate;

    @FXML
    private TableColumn<Therapist ,Double> ColExperience;

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




    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        JavafxTableFill();
    }
        

    //Overrided by implementing JavafxPaneHandler
    @Override
    public void JavafxTableFill() {
        ObservableList<Therapist> Therapist = FXCollections.observableArrayList();
        Model.Therapist t = new Therapist();
        t.setID("666");
        t.setName("Amir");
        t.setGender("Male");
        t.setDate(new Date());
        t.setAddress(new Address("Mughar"));
        Therapist.add(t);

        //Table Init
        ColID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ColGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        ColBdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        ColExperience.setCellValueFactory(new PropertyValueFactory<>("Experience"));
        //add your data to the table here.
        NurseTable.setItems(Therapist);
    }

    @Override
    public void JavafxChoiceFill() {

    }

    @Override
    public void JavafxDiagramFill() {

    }
}
