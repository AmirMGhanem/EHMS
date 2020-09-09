package Controller;

import DBH.adressDAO;
import DBH.personDAO;
import DBH.therapistDAO;
import Model.Address;
import Model.Person;
import Model.Therapist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class EditNurseController implements Initializable,Util.JavafxPaneHandler {

    DBH.therapistDAO tbh = new DBH.therapistDAO();
    ArrayList<Therapist> list = new ArrayList<Therapist>();

    @FXML private Button BtnClear;
    @FXML private Button BtnSave;
    @FXML private TextField TextFieldNurseID;
    @FXML private TextField TextFieldFirstName;
    @FXML private TextField TextFieldLastName;
    @FXML private TextField TextFieldContactNum;
    @FXML private TextField TextFieldCity;
    @FXML private TextField TextFieldStreet;
    @FXML private TextField TextFieldHouseNum;

    ObservableList ThreeDigitsList = FXCollections.observableArrayList();

    @FXML void OnClickClear(ActionEvent event) {

    }

    @FXML public void onEnter(ActionEvent ae) throws SQLException {
        String id = TextFieldNurseID.getText();

        list = tbh.selectAll();

        for (Therapist t : list) {
            if(t.getID().equals(id)){
                TextFieldFirstName.setText(t.getFirstName());
                TextFieldLastName.setText(t.getLasttName());
               TextFieldContactNum.setText(t.getContactNo());
               TextFieldCity.setText(t.getAddress().getCity());
               TextFieldStreet.setText(t.getAddress().getStreet());
              TextFieldHouseNum.setText(Integer.toString(t.getAddress().getHouseNum()));
            }

        }
    }

    @FXML
    void OnClickSave(ActionEvent event) throws SQLException {

        list = tbh.selectAll();
        for(Therapist t : list) {
            if(t.getID().equals(TextFieldNurseID.getText())) {
                t.setName(TextFieldFirstName.getText() + " " + TextFieldLastName.getText());
                String ContactNum = TextFieldContactNum.getText();
                t.setContactNo(ContactNum);
                Address address = new Address(TextFieldCity.getText(), TextFieldStreet.getText(), Integer.parseInt(TextFieldHouseNum.getText()));
                t.setAddress(address);
                tbh.Updateherapist(t);
            }

        }
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
