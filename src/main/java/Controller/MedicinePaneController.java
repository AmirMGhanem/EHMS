package Controller;

import Model.Medicine;
import Model.Patient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MedicinePaneController implements Initializable,Util.JavafxPaneHandler {
    public ArrayList<Medicine> ALMEDICINE = new ArrayList<Medicine>();

    @FXML private Button BtnLoadData;
    @FXML private ChoiceBox<?> ChoicePatient;
    @FXML private Label LabelPatientID;
    @FXML private TableView<?> MedTable;
    @FXML private TableView<?> AllrgyTable;


    @FXML
    void OnLoadDataClick(ActionEvent event) {

    }

    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    //Overrided by implementing JavafxPaneHandler
    @Override
    public void JavafxTableFill() {

    }

    @Override
    public void JavafxChoiceFill() {

    }

    @Override
    public void JavafxDiagramFill() {

    }
}
