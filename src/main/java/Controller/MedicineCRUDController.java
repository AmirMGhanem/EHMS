package Controller;

import DBH.*;
import Model.Allergy;
import Model.Medicine;
import Model.Patient;
import Util.MessageAlerter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class MedicineCRUDController implements Initializable, Util.JavafxPaneHandler {


    @FXML
    private Pane parent;
    @FXML
    private ChoiceBox<String> ChoiceAddPatientsID;
    @FXML
    private TextField TextFieldAddName;
    @FXML
    private ChoiceBox<String> ChoiceAddType;
    @FXML
    private Spinner<Integer> AddTimesPerDay;
    @FXML
    private Button BtnAdd;
    @FXML
    private ChoiceBox<String> ChoiceEditCurrName;
    @FXML
    private Spinner<Integer> EditCurrTimesPerDay;
    @FXML
    private TextField EditNewName;
    @FXML
    private ChoiceBox<String> ChoiceEditCurrType;
    @FXML
    private ChoiceBox<?> ChoiceEditNewType;
    @FXML
    private Spinner<Integer> EditNewTimesPerDay;
    @FXML
    private Button BtnEditSubmit;
    @FXML
    private ChoiceBox<String> ChoiceRemoveName;
    @FXML
    private Button BtnRemove;
    @FXML
    private TextArea TextArea;
    @FXML
    private TextField TextFieldMedicineNum;
    @FXML
    private TextField TextFieldEditMedicineNum;
    @FXML
    private ChoiceBox<String> ChoiceEditPatientID;
    @FXML
    private ChoiceBox<String> ChoiceNewEditPatientID;
    @FXML
    private TextField TextFieldEditNewMedicineNum;
    @FXML
    private TextField TextFieldRemoveMedicineNum;
    @FXML
    private ChoiceBox<String> ChoiceRemovePatientID;
    @FXML
    private TextField TextFieldAddAllergyName;
    @FXML
    private ChoiceBox<?> ChoiceAdddAllergyMedName;
    @FXML
    private Button BtnAddAllergy;
    @FXML
    private Button BtnEditAllergySubmit;
    @FXML
    private ChoiceBox<?> ChoiceEditAllergyName;
    @FXML
    private TextField TextFieldNewAllergyName;
    @FXML
    private TextField TextFieldEditAllergyMedName;
    @FXML
    private TextField TextFieldEditAllergyMedNum;
    @FXML
    private ChoiceBox<?> ChoiceNewAllergyMedicine;

    DBH.medicineDAO mbh = new DBH.medicineDAO();
    DBH.AllergyDAO Ado = new AllergyDAO();
    ObservableList medicineObservableList = FXCollections.observableArrayList();
    ArrayList<Medicine> MedicineArrayList = new ArrayList<Medicine>();
    ObservableList allergyOvservableList = FXCollections.observableArrayList();
    ArrayList<Allergy> allergyArrayList = new ArrayList<Allergy>();

    MessageAlerter ma = new MessageAlerter();

    @FXML
    public void onEnterE(ActionEvent ae) throws SQLException {

        ArrayList<Medicine> mlist = new ArrayList<Medicine>();
        int medicineNum = Integer.parseInt(TextFieldEditMedicineNum.getText());
        ObservableList list = FXCollections.observableArrayList();
        ChoiceEditCurrType.getItems().clear();
        ChoiceEditCurrName.getItems().clear();
        list.clear();
        mlist = mbh.selectAll();
        for (Medicine m : mlist) {
            if (medicineNum == m.getMedicineNum()) {
                list.add(m.getName());
                ChoiceEditCurrType.getItems().add(m.getType());
            }
        }
        ChoiceEditCurrName.getItems().addAll(list);
        TextFieldEditNewMedicineNum.setText(TextFieldEditMedicineNum.getText());
        TextFieldEditNewMedicineNum.setEditable(false);
        TextFieldEditNewMedicineNum.setDisable(true);
    }


    @FXML
    void OnClickBtnAdd(ActionEvent event) throws SQLException {
        String MessageInformation = "";

        if((TextFieldAddName.getLength() == 0) || (ChoiceAddType.getValue() == null)){
            MessageInformation += "Missing Information :" ;
            if(TextFieldAddName.getLength()==0) MessageInformation += "\n * Medicine Name";
            if(ChoiceAddType.getValue() == null) MessageInformation += "\n * Medicine Type";
            ma.ShowErrorMessage("Unexpected Error", "Missing Information", MessageInformation);
        }
        else{
            Medicine m = new Medicine();
            m.setName(TextFieldAddName.getText());
            m.setType(ChoiceAddType.getValue());
            mbh.insertMedicine(m);

            MessageInformation += "Medicine Added Successfully :)" ;
            ma.MessageWithoutHeader("Added", MessageInformation);
        }
    }

    @FXML
    void OnClickBtnEditSubmit(ActionEvent event) throws SQLException {
        String MessageInformation = "";

        if((TextFieldEditMedicineNum.getLength()==0) || (ChoiceEditCurrName.getValue()==null) || (ChoiceEditCurrType.getValue()==null) || (TextFieldEditNewMedicineNum.getLength()==0) || (EditNewName.getLength()==0) || (ChoiceEditNewType.getValue()==null)){
            MessageInformation += "Missing Information ";

            if(TextFieldEditMedicineNum.getLength()==0) MessageInformation += "\n * Medicine Num";
            if(ChoiceEditCurrName.getValue()==null) MessageInformation += "\n * Medidine Name" ;
            if(ChoiceEditCurrType.getValue()==null) MessageInformation += "\n * Medicine Type";
            if(TextFieldEditNewMedicineNum.getLength()==0) MessageInformation += "\n * Medicine New num";
            if(EditNewName.getLength()==0) MessageInformation += "\n * Medidine New Name" ; ;
            if(ChoiceEditNewType.getValue()==null) MessageInformation += "\n * Medicine New Type";;

            ma.ShowErrorMessage("Unexpected Error", "Fail To Add", MessageInformation);
        }
        else{
            Medicine m = new Medicine();
            m.setMedicineNum(Integer.parseInt(TextFieldEditMedicineNum.getText()));
            m.setName(EditNewName.getText());
            m.setType(ChoiceEditNewType.getValue().toString());
            m.setTimesPerDay(EditNewTimesPerDay.getValue());
            mbh.UpdateMedicine(m);

            MessageInformation += "Medicine Edited Successfullt :)";
            ma.MessageWithoutHeader("Edited", MessageInformation);
        }
    }



    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



            try {
                CssStyler();
            MedicineArrayList = mbh.selectAll();
            allergyArrayList = Ado.selectAll();
            JavafxChoiceFill();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initializeManual() throws SQLException {
        MedicineArrayList = mbh.selectAll();
        allergyArrayList = Ado.selectAll();
        medicineObservableList.clear();
        allergyOvservableList.clear();

        for (Medicine m : MedicineArrayList) {
            medicineObservableList.add(m.getName());
        }
        for (Allergy a : allergyArrayList) {
            allergyOvservableList.add(a.getName());
        }
    }


    @FXML
    private void OnSelectAllergyName(ActionEvent event) throws SQLException {

        String selectedItem = ChoiceEditAllergyName.getSelectionModel().getSelectedItem().toString();

        for (Allergy a : allergyArrayList) {
            if (a.getName().equals(selectedItem)) {
                TextFieldEditAllergyMedName.setText(a.getMedicines().getName());
                TextFieldEditAllergyMedNum.setText(Integer.toString(a.getMedicines().getMedicineNum()));
                TextFieldNewAllergyName.setText(selectedItem);
            }
        }


    }

    @FXML
    void OnClickBtnAddAllergy(ActionEvent event) throws SQLException {
        String MessageInformation = "";

        if((TextFieldAddAllergyName.getLength() == 0) || (ChoiceAdddAllergyMedName.getValue() == null)){
            MessageInformation += "Missing Information :" ;
            if(TextFieldAddAllergyName.getLength()==0) MessageInformation += "\n * Allergy Name";
            if(ChoiceAdddAllergyMedName.getValue() == null) MessageInformation += "\n * Medicine Name";
            ma.ShowErrorMessage("Unexpected Error", "Missing Information", MessageInformation);
        }
        else{
            ArrayList<Medicine> mlist = new ArrayList<Medicine>();
            mlist = mbh.selectAll();
            Allergy a = new Allergy();
            a.setName(TextFieldAddAllergyName.getText());
            for (Medicine m : mlist)
                if (m.getName().equals(ChoiceAdddAllergyMedName.getValue()))
                    a.setMedicines(m);

            Ado.insertAllergy(a);
            JavafxChoiceFill();

            MessageInformation += "Allergy Added Successfully :)" ;
            ma.MessageWithoutHeader("Added", MessageInformation);
        }
    }

    @FXML
    void OnClickBtnEditAllergySubmit(ActionEvent event) throws SQLException {
        String MessageInformation = "";

        if((ChoiceEditAllergyName.getValue()==null) || (TextFieldEditAllergyMedNum.getLength()==0) || (TextFieldEditAllergyMedName.getLength()==0) || (TextFieldNewAllergyName.getLength()==0) || (ChoiceNewAllergyMedicine.getValue()==null)){
            MessageInformation += "Missing Information ";

            if(ChoiceEditAllergyName.getValue()==null) MessageInformation += "\n * Allergy Name";
            if(TextFieldEditAllergyMedNum.getLength()==0) MessageInformation += "\n * Medidine Num" ;
            if(TextFieldEditAllergyMedName.getLength()==0) MessageInformation += "\n * Allergy Name";
            if(TextFieldNewAllergyName.getLength()==0) MessageInformation += "\n * New Allergy Name";
            if(ChoiceNewAllergyMedicine.getValue()==null) MessageInformation += "\n * New Medidine Name" ; ;

            ma.ShowErrorMessage("Unexpected Error", "Fail To Add", MessageInformation);
        }
        else{
            Allergy a = new Allergy();
            a.setName(TextFieldNewAllergyName.getText());
            for (Medicine m : MedicineArrayList)
                if (m.getName().equals(ChoiceNewAllergyMedicine.getValue()))
                    a.setMedicines(m);

            Ado.UpdateAllergy(a, ChoiceEditAllergyName.getValue().toString());

            MessageInformation += "Allergy Edited Successfullt :)";
            ma.MessageWithoutHeader("Edited", MessageInformation);
        }
    }


    //Overrided by implementing JavafxPaneHandler
    @Override
    public void JavafxTableFill() {

    }

    @Override
    public void JavafxChoiceFill() throws SQLException {

        ObservableList TypeList = FXCollections.observableArrayList();

        ChoiceAddType.getItems().clear();
        ChoiceEditNewType.getItems().clear();
        ChoiceAdddAllergyMedName.getItems().clear();
        ChoiceEditAllergyName.getItems().clear();
        ChoiceNewAllergyMedicine.getItems().clear();
        TypeList.clear();

        String oi = "Ointment";
        String ca = "Capsules";
        String li = "Liquid";
        String po = "Powder";
        TypeList.addAll(oi, ca, li, po);
        ChoiceAddType.getItems().addAll(TypeList);
        ChoiceEditNewType.getItems().addAll(TypeList);
        initializeManual();
        ChoiceAdddAllergyMedName.getItems().addAll(medicineObservableList);

        ChoiceEditAllergyName.getItems().addAll(allergyOvservableList);
        ChoiceNewAllergyMedicine.getItems().addAll(medicineObservableList);


    }


    @Override
    public void JavafxDiagramFill() {

    }

    private void CssStyler()
    {
        FXMLLoader loader = new FXMLLoader();

        try {
            loader.load(getClass().getResource("/FXML/Settings.fxml").openStream());

            SettingsController settingsController = loader.getController();
            if (settingsController.getToggleMode()) {
                String css = this.getClass().getResource("/Css/darkmode.css").toExternalForm();
                parent.getStylesheets().add(css);
            } else {
                String css = this.getClass().getResource("/Css/lightmode.css").toExternalForm();
                parent.getStylesheets().add(css);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}