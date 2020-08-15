package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MealsController implements Initializable,Util.JavafxPaneHandler {

    @FXML
    private DatePicker DatePicker;

    @FXML
    private Button BtnFind;

    @FXML
    private TextField TextFieldID;

    @FXML
    private ChoiceBox<?> ChoiceMeal;

    @FXML
    private Button BtnClear;

    @FXML
    private Button BtnAddMeal;

    @FXML
    private Label LabelID;

    @FXML
    void OnClickAddMeal(ActionEvent event) {

    }

    @FXML
    void OnClickClear(ActionEvent event) {

    }

    @FXML
    void OnClickFind(ActionEvent event) {

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
