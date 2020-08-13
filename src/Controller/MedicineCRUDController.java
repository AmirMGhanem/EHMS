package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MedicineCRUDController {

    @FXML
    private TextField TextFieldAddName;

    @FXML
    private ChoiceBox<?> ChoiceAddType;

    @FXML
    private Spinner<?> AddTimesPerDay;

    @FXML
    private Button BtnAdd;

    @FXML
    private ChoiceBox<?> ChoiceEditCurrName;

    @FXML
    private Spinner<?> EditCurrTimesPerDay;

    @FXML
    private TextField EditNewName;

    @FXML
    private ChoiceBox<?> ChoiceEditCurrType;

    @FXML
    private ChoiceBox<?> ChoiceEditNewType;

    @FXML
    private Spinner<?> EditNewTimesPerDay;

    @FXML
    private Button BtnEditSubmit;

    @FXML
    private ChoiceBox<?> ChoiceRemoveName;

    @FXML
    private Button BtnRemove;

    @FXML
    private TextArea TextArea;

    @FXML
    void OnClickBtnAdd(ActionEvent event) {

    }

    @FXML
    void OnClickBtnEditSubmit(ActionEvent event) {

    }

    @FXML
    void OnClickBtnRemove(ActionEvent event) {

    }

}
