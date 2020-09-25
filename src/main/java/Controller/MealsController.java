package Controller;


import DBH.mealDAO;
import DBH.patientDAO;
import DBH.patient_mealDAO;
import DBH.patient_medicineDAO;
import Model.*;
import Util.JavafxPaneHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MealsController implements Initializable, JavafxPaneHandler {


    DBH.patientDAO pDAO = new patientDAO();
    private ArrayList<Patient> patientArrayList = new ArrayList<Patient>();
    private ObservableList patientObservable = FXCollections.observableArrayList();
    DBH.mealDAO mDAO = new mealDAO();
    private ArrayList<Meal> mealArrayList = new ArrayList<Meal>();
    private ObservableList mealObservable = FXCollections.observableArrayList();
    DBH.patient_mealDAO pmDAO = new patient_mealDAO();
    private ArrayList<patient_meal> patient_mealArrayList = new ArrayList<patient_meal>();

    ObservableList Choicelist = FXCollections.observableArrayList();


    @FXML
    private Pane parent;
    @FXML
    private Button BtnShowAll;

    @FXML
    private Button BtnAddMeal;

    @FXML
    private Button BtnDeleteMeal;

    @FXML
    private TextField TextFieldAddMealName;

    @FXML
    private TextField TextFieldAddMealWeight;

    @FXML
    private TableView<Meal> TableMeals;

    @FXML
    private TableColumn<Meal, String> ColMealName;

    @FXML
    private TableColumn<Meal, Number> ColWeight;


    @FXML
    private TableView<Patient> TablePatient;

    @FXML
    private TableColumn<Patient, String> ColPatientid;

    @FXML
    private TableColumn<Patient, String> ColPatientName;

    @FXML
    private Button BtnAttachMeal;

    @FXML
    private Button BtnDetachMeal;

    @FXML
    private ListView<patient_meal> ListViewMeals;

    @FXML
    private TextField TextFieldID;

    @FXML
    private ChoiceBox<String> ChoiceMeal;

    @FXML
    void OnClickAddMeal(ActionEvent event) throws SQLException {
        Meal m = new Meal();
        m.setName(TextFieldAddMealName.getText());
        m.setWeight(Integer.parseInt(TextFieldAddMealWeight.getText()));
        mDAO.insertMeal(m);
        mealArrayList = mDAO.selectAll();
        TableInit();
        JavafxChoiceFill();
    }

    @FXML
    void onClickBtnDeleteMeal(ActionEvent event) throws SQLException {
        String name = TableMeals.getSelectionModel().getSelectedItem().getName();
        patient_mealArrayList = pmDAO.selectAll();
        boolean flag = false;
        for (Model.patient_meal pm : patient_mealArrayList) {
            if (pm.getMealName().equals(name)) {
                flag = true;
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please Detach First From All Patients", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            }
        }
        if (!flag) {
            mDAO.removeMealByName(name);
            TableMeals.getItems().removeAll(TableMeals.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void onClickBtnAttachMeal(ActionEvent event) {
        try {
            String mealname = TableMeals.getSelectionModel().getSelectedItem().getName();
            String patientid = TablePatient.getSelectionModel().getSelectedItem().getID();
            patient_meal pm = new patient_meal(patientid, mealname);
            if (pmDAO.insertToPatient_meal(pm) == 0)
                System.out.println("Unsuccesffully");
            else {
                System.out.println("Successfully attached");
                ListViewMeals.getItems().add(pm);
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cannot Add Twice The Same Meal", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
    }

    @FXML
    void onClickBtnDetachMeal(ActionEvent event) throws SQLException {
        String str = ListViewMeals.getSelectionModel().getSelectedItems().toString();
        String[] strline;
        strline = str.split(" ");
        String id = strline[1];
        System.out.println(id);
        String name = strline[3];
        System.out.println(name);
        patient_meal pm = new patient_meal(id, name);
        pmDAO.removeByMealName(pm);
        ListViewMeals.getItems().removeAll(ListViewMeals.getSelectionModel().getSelectedItem());


    }

    @FXML
    void onEnter(ActionEvent event) throws SQLException {
        ListViewMeals.getItems().clear();
        String id = TextFieldID.getText();
        patient_mealArrayList = pmDAO.selectAll();
        for (patient_meal pm : patient_mealArrayList) {
            if (pm.getPatientid().equals(id))
                ListViewMeals.getItems().add(pm);
        }
        if (id.equals(""))
            ListViewInit();

    }

    @FXML
    private void OnSelectMealName(ActionEvent event) throws SQLException {

        String selectedItem = ChoiceMeal.getSelectionModel().getSelectedItem();

        ListViewMeals.getItems().clear();

        patient_mealArrayList = pmDAO.selectAll();
        for (patient_meal pm : patient_mealArrayList) {
            if (pm.getMealName().equals(selectedItem))
                ListViewMeals.getItems().add(pm);

        }

        ChoiceMeal.getSelectionModel().select(-1);
        ChoiceMeal.setValue(selectedItem);


    }

    @FXML
    void onClickBtnShowAll(ActionEvent event) throws SQLException {
        ListViewMeals.getItems().clear();
        ListViewInit();
    }

    private void TableInit() throws SQLException {
        ColMealName.setCellValueFactory(new PropertyValueFactory<Meal, String>("name"));
        ColWeight.setCellValueFactory(new PropertyValueFactory<Meal, Number>("weight"));

        ColPatientid.setCellValueFactory(new PropertyValueFactory<Model.Patient, String>("ID"));
        ColPatientName.setCellValueFactory(new PropertyValueFactory<Model.Patient, String>("name"));
        JavafxTableFill();
        TablePatient.setItems(patientObservable);
        TableMeals.setItems(mealObservable);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            TableInit();
            ListViewInit();
            JavafxChoiceFill();
            CssStyler();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void ListViewInit() throws SQLException {
        patient_mealArrayList = pmDAO.selectAll();
        ListViewMeals.getItems().setAll(patient_mealArrayList);
    }


    @Override
    public void JavafxTableFill() throws SQLException {
        mealArrayList = mDAO.selectAll();
        mealObservable = mDAO.selectAllObservable();
        patientArrayList = pDAO.selectAll();
        patientObservable = pDAO.selectPatients();

    }

    @Override
    public void JavafxChoiceFill() throws SQLException {
        Choicelist.clear();
        ChoiceMeal.getItems().clear();

        ChoiceMeal.setValue("Choose Meal");
        for (Meal m : mealArrayList)
            Choicelist.add(m.getName());

        ChoiceMeal.getItems().addAll(Choicelist);


    }


    @Override
    public void JavafxDiagramFill() throws IOException {

    }

    private void CssStyler() {
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
