package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
public class MedicinePaneController {

    @FXML
    private Button BtnLoadData;
    @FXML
    private ChoiceBox<?> ChoicePatient;

    @FXML
    private Label LabelPatientID;

    @FXML
    private TableView<?> MedTable;

    @FXML
    private TableView<?> AllrgyTable;


    @FXML
    void OnLoadDataClick(ActionEvent event) {

    }

}
