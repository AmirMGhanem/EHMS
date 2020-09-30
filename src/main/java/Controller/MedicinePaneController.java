package Controller;

import DBH.AllergyDAO;
import DBH.patient_allergyDAO;
import DBH.patient_medicineDAO;
import Model.*;
import Util.FilesHandler;
import Util.FooterPageEvent;
import Util.FxmlLoader;
import Util.MessageAlerter;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;


public class MedicinePaneController implements Initializable, Util.JavafxPaneHandler {

    //defining lists for Medicines and patient-med
    public ArrayList<Medicine> ALMED = new ArrayList<Medicine>();
    private ArrayList<patient_medicine> patient_medicineArrayList = new ArrayList<patient_medicine>();
    private ObservableList<patient_medicine> patient_medicineObservableList = FXCollections.observableArrayList();
    DBH.patient_medicineDAO pmdao = new patient_medicineDAO();
    private ObservableList<Medicine> Medicines = FXCollections.observableArrayList(ALMED);
    private ObservableList<Model.Patient> Patients = FXCollections.observableArrayList();
    DBH.medicineDAO MDH = new DBH.medicineDAO();
    ObservableList Choicelist = FXCollections.observableArrayList();


    //defining lists for Allergies and patient-allergy
    ObservableList allergyOvservableList = FXCollections.observableArrayList();
    ArrayList<Allergy> allergyArrayList = new ArrayList<Allergy>();
    DBH.AllergyDAO Ado = new AllergyDAO();

    private ArrayList<patient_allergy> PA = new ArrayList<patient_allergy>();
    ObservableList<patient_allergy> paObservablelist = FXCollections.observableArrayList();
    DBH.patient_allergyDAO paDAO = new patient_allergyDAO();

    MessageAlerter ma = new MessageAlerter();

    @FXML
    private Pane parent;

    @FXML
    private
    ChoiceBox<String> ChoicePatient;

    @FXML
    private Label LabelPatientID;

    @FXML
    private TableView<Medicine> MedTable;

    @FXML
    private TableColumn<Medicine, Number> ColMedNum;

    @FXML
    private TableColumn<Medicine, String> ColMedName;

    @FXML
    private TableColumn<Medicine, String> ColMedType;
    //---------------------------------

    @FXML
    private TableView<patient_medicine> TablePatientMedicines;

    @FXML
    private TableColumn<patient_medicine, String> ColID;


    @FXML
    private TableColumn<patient_medicine, Number> ColMedNO;

    @FXML
    private TableColumn<patient_medicine, Number> colTimesPerDay;

    @FXML
    private TableColumn<patient_medicine, Number> ColDuration;


    @FXML
    private Button BtnRemoveMed;

    @FXML
    private Button BtnExportMedPDF;

    @FXML
    private Button BtnExportMedFile;

    @FXML
    private Button BtnAttachMed;

    @FXML
    Button BtnLoadAll;

    @FXML
    private TableView<Allergy> AllergyTable;

    @FXML
    private TableColumn<Allergy, String> ColAllergyName;


    @FXML
    private TableColumn<Allergy, String> ColMedicine;

    @FXML
    private Button BtnRemoveAllergy;

    @FXML
    private Button BtnExportAllergyPDF;

    @FXML
    private Button BtnExportAllergyFile;

    @FXML
    private Button BtnAttachAllergy;

    @FXML
    private Button BtnDetachAllergy;
    @FXML
    private Label LabelPatientIDAllergy;
    @FXML
    private Button BtnLoadIDAllergy;

    @FXML
    private Label LabelUpdateAttach;

    @FXML
    private Label LabelLoadUpdate;
    @FXML
    private ChoiceBox<String> ChoicePatientAllergy;
    @FXML
    private Button BtnDetachMed;

    @FXML
    private Button BtnLoadID;

    static int mednum;
    static String patientID;

    public static String getPatientID() {
        return patientID;
    }

    public static void setPatientID(String patientID) {
        MedicinePaneController.patientID = patientID;
    }

    public static int getMednum() {
        return mednum;
    }

    public void setMednum(int mednum) {
        this.mednum = mednum;
    }


    @FXML
    void OnClickBtnDetachMed(ActionEvent event) throws SQLException {
        if (TablePatientMedicines.getSelectionModel().getSelectedItem() != null) {
            patient_medicine patient_medicine = TablePatientMedicines.getSelectionModel().getSelectedItem();
            TablePatientMedicines.getItems().remove(TablePatientMedicines.getSelectionModel().getSelectedItem());
            pmdao.removeByMedicineNum(patient_medicine.getMedicinenum(), patient_medicine.getPatientid());
            LabelUpdateAttach.setText("Detached!");
            ma.MessageWithoutHeader("Detached", "Medicine Detached From Selected Patient Successfully");
        } else
            ma.MessageWithoutHeader("Unexpected", "Please Select Row From The Right Table \n in order to detach");

    }


    @FXML
    void OnClickBtnAttachMed(ActionEvent event) throws IOException {
        setMednum(MedTable.getSelectionModel().getSelectedItem().getMedicineNum());
        setPatientID(LabelPatientID.getText());
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("addMedicineToPatient");
        Stage stage = new Stage();
        stage.setTitle("Add Med-Patient Window");
        stage.setScene(new Scene(view, 550, 245));
        stage.show();


    }

    @FXML
    void OnClickBtnExportMedFile(ActionEvent event) throws IOException {
        Util.FilesHandler fh = new FilesHandler();
        fh.SaveMedicines();
        ma.MessageWithoutHeader("Exported", "Medicines Exported To File Successfully :)");
    }

    @FXML
    void OnClickBtnExportMedPDF(ActionEvent event) throws IOException, DocumentException, SQLException {
        Document document = new Document();
        Font font;
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/Files/PDF/MedicinesPDF.pdf"));
        document.open();
        //Adding the footer to the pdf file, by class created on the utils
        Util.FooterPageEvent footer = new FooterPageEvent();
        writer.setPageEvent(footer);

        //creating paragraph
        Paragraph p1 = new Paragraph();
        Paragraph pNew5Lines = new Paragraph();
        for (int i = 0; i < 5; i++)
            p1.add("\n");

        //Printing Chunk Text on the pdf

        p1.add("#        Medicine Name        Medicine Type        ");
        p1.add("\n--------------------------------------------------------------------------------------------\n");
        font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

        for (String s : MDH.MedicinesPDF()) {
            Chunk chunk = new Chunk(s, font);
            p1.add(chunk);
            p1.add("\n");
        }


        //Drawing an image from the resources folder
        Image img = Image.getInstance("src/main/resources/Images/banner.png");
        BufferedImage bimg = ImageIO.read(new File("src/main/resources/Images/banner.png"));
        new Chunk(img, 0, 0, true);
        //Get Sizes
        float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - 0) / img.getWidth()) * 90;
        //setting the scaler on the image for resizing the image by percentage
        img.scalePercent(scaler);
        img.setAlignment(Element.ALIGN_CENTER);
        //creating paragraph and adding the banner with text
        Paragraph preface = new Paragraph();
        preface.add(img);
        document.add(preface);
        document.add(pNew5Lines);
        document.add(p1);
        document.close();
        writer.close();

        ma.MessageWithoutHeader("Exported", "Medicines Exported To PDF Successfully :)");
    }

    @FXML
    void OnClickBtnRemoveMed(ActionEvent event) throws SQLException {
        ArrayList<Model.patient_medicine> pmlist = new ArrayList<Model.patient_medicine>();
        int mednum = MedTable.getSelectionModel().getSelectedItem().getMedicineNum();
        boolean flag = false;
        patient_medicineDAO pmDAO = new patient_medicineDAO();
        pmlist = pmDAO.selectAll();

        for (Model.patient_medicine pm : pmlist) {
            if (pm.getMedicinenum() == mednum)
                flag = true;
        }
        if (flag == true) {
            ma.ShowWarningMessage("Unexpected Error", "Can't Delete This Medicine", "Please Detach First From All Patients");
        }
        if (flag == false) {
            MDH.removeMedicineByID(mednum);
            MedTable.getItems().removeAll(MedTable.getSelectionModel().getSelectedItem());
        }
    }


    @FXML
    void OnClickBtnLoadID(ActionEvent event) throws SQLException, InterruptedException {
        TablePatientMedicines.getItems().clear();
        patient_medicineObservableList.clear();
        JavafxTableFill();
        for (patient_medicine pm : patient_medicineArrayList) {
            if (pm.getPatientid().equals(LabelPatientID.getText())) {
                TablePatientMedicines.getItems().add(pm);
            }
        }

    }


    private void TableInit() throws SQLException {
        //Medicine Table Init
        ColMedNum.setCellValueFactory(new PropertyValueFactory<Medicine, Number>("medicineNum"));
        ColMedName.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        ColMedType.setCellValueFactory(new PropertyValueFactory<Medicine, String>("type"));


        //patient_medicine Table init
        ColID.setCellValueFactory(new PropertyValueFactory<patient_medicine, String>("patientid"));
        ColMedNO.setCellValueFactory(new PropertyValueFactory<patient_medicine, Number>("medicinenum"));
        colTimesPerDay.setCellValueFactory(new PropertyValueFactory<patient_medicine, Number>("timesperday"));
        ColDuration.setCellValueFactory(new PropertyValueFactory<patient_medicine, Number>("duration"));


        ColAllergyName.setCellValueFactory(new PropertyValueFactory<Allergy, String>("name"));
        ColMedicine.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getMedicines().getName()));
        JavafxTableFill();
        MedTable.setItems(Medicines);
        AllergyTable.setItems(allergyOvservableList);
    }


    @FXML
    void onClickBtnExportAllergyFile(ActionEvent event) throws IOException {
        Util.FilesHandler fh = new FilesHandler();
        fh.SaveAllergies();
        ma.MessageWithoutHeader("Exported", "Allergies Exported To File Successfully :)");
    }

    @FXML
    void onClickBtnExportAllergyPDF(ActionEvent event) throws IOException, DocumentException, SQLException {
        Document document = new Document();
        Font font;
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/Files/PDF/AllergiesPDF.pdf"));
        document.open();
        //Adding the footer to the pdf file, by class created on the utils
        Util.FooterPageEvent footer = new FooterPageEvent();
        writer.setPageEvent(footer);

        //creating paragraph
        Paragraph p1 = new Paragraph();
        Paragraph pNew5Lines = new Paragraph();
        for (int i = 0; i < 5; i++)
            p1.add("\n");

        //Printing Chunk Text on the pdf

        p1.add(" Allergy Name                       MedicineName ");
        p1.add("\n----------------------------------------------------------------------\n");
        font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

        for (String s : Ado.AllergiesPDF()) {
            Chunk chunk = new Chunk(s, font);
            p1.add(chunk);
            p1.add("\n");
        }


        //Drawing an image from the resources folder
        Image img = Image.getInstance("src/main/resources/Images/banner.png");
        BufferedImage bimg = ImageIO.read(new File("src/main/resources/Images/banner.png"));
        new Chunk(img, 0, 0, true);
        //Get Sizes
        float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - 0) / img.getWidth()) * 90;
        //setting the scaler on the image for resizing the image by percentage
        img.scalePercent(scaler);
        img.setAlignment(Element.ALIGN_CENTER);
        //creating paragraph and adding the banner with text
        Paragraph preface = new Paragraph();
        preface.add(img);
        document.add(preface);
        document.add(pNew5Lines);
        document.add(p1);
        document.close();
        writer.close();
        ma.MessageWithoutHeader("Exported", "Allergies Exported To PDF Successfully :)");
    }

    @FXML
    void onClickBtnRemoveAllergy(ActionEvent event) throws SQLException {
        ArrayList<Model.patient_allergy> palist = new ArrayList<patient_allergy>();
        String allergyname = AllergyTable.getSelectionModel().getSelectedItem().getName();
        boolean flag = false;
        palist = paDAO.selectAll();

        for (Model.patient_allergy pa : palist) {
            if (pa.getAllergyName().equals(allergyname)) {
                flag = true;
            }
        }
        if (flag == true) {
            ma.ShowWarningMessage("Unexpected Error", "Can't Delete This Allergy", "Please Detach First From All Patients");
        }
        if (flag == false) {
            Ado.removeAllergyByName(allergyname);
            AllergyTable.getItems().removeAll(AllergyTable.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void OnClickBtnDetachAllergy(ActionEvent event) throws SQLException {
        String allergyname = AllergyTable.getSelectionModel().getSelectedItem().getName();
        PA = paDAO.selectAll();

        for (Model.patient_allergy pa : PA) {
            if (pa.getAllergyName().equals(allergyname) && pa.getPatientid().equals(LabelPatientIDAllergy.getText())) {
                paDAO.removeByAllergyname(pa);
                LabelUpdateAttach.setText("Detached!");
                ma.MessageWithoutHeader("Detached", "Allergy Detached From Selected Patient Successfully");
            }
        }
    }

    @FXML
    void OnClickBtnAttachAllergy(ActionEvent event) {
        String Allergyname = AllergyTable.getSelectionModel().getSelectedItem().getName();
        String PatientidForAttach = LabelPatientIDAllergy.getText();
        patient_allergy pm = new patient_allergy(PatientidForAttach, Allergyname);
        try {
            if (paDAO.insertToPatient_allergy(pm) == 0)
                LabelUpdateAttach.setText("Unsuccessfully");
            else
                LabelUpdateAttach.setText("Successfully Added");
        } catch (SQLException e) {
            ma.ShowWarningMessage("Unexpected Error", "Allergy Already Added ", "Cannot Add The Same Allergy Twice To The Same Patient");
        }
    }

    //Initilaizable and javafx handler implement


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CssStyler();
            TableInit();
            JavafxDiagramFill();
            DBH.patientDAO PDH = new DBH.patientDAO();
            Patients = PDH.selectPatients();
            JavafxChoiceFill();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void JavafxTableFill() throws SQLException {
        patient_medicineArrayList = pmdao.selectAll();
        patient_medicineObservableList.setAll(patient_medicineArrayList);
        Medicines = MDH.selectMedicines();
        ALMED = MDH.selectAll();
        PA = paDAO.selectAll();
        allergyOvservableList = Ado.selectAllObservable();
        allergyArrayList = Ado.selectAll();


    }

    @Override
    public void JavafxChoiceFill() throws SQLException {

        Choicelist.removeAll();
        for (Patient p : Patients) {
            Choicelist.add(p.getName());
        }
        ChoicePatient.setValue("Choose Patient");
        ChoicePatient.getItems().addAll(Choicelist);
        ChoicePatientAllergy.getItems().setAll(Choicelist);
    }

    @Override
    public void JavafxDiagramFill() throws IOException {

    }

    @FXML
    public void onSelectPatient(ActionEvent event) throws SQLException {
        LabelPatientID.setText("");
        String name = "";
        for (Patient p : Patients) {
            if (p.getName().equals(ChoicePatient.getValue())) {
                LabelPatientID.setText(p.getID());
                LabelLoadUpdate.setText("Loaded");
                name = p.getName();
            }
        }
        ChoicePatient.getSelectionModel().select(-1);
        ChoicePatient.setValue(name);
    }

    @FXML
    void OnClickBtnLoadAll(ActionEvent event) throws SQLException {
        JavafxTableFill();
        AllergyTable.setItems(allergyOvservableList);
    }

    @FXML
    void OnClickBtnLoadIDAllergy(ActionEvent event) throws SQLException {

        String name = "";
        for (Patient p : Patients) {
            if (p.getName().equals(ChoicePatientAllergy.getValue())) {
                LabelPatientIDAllergy.setText(p.getID());
                LabelLoadUpdate.setText("Loaded");
                name = p.getName();
            }
        }
        //----------------------------------------------------------------------------- LOAD ALLERGY
        AllergyTable.getItems().removeAll();
        JavafxTableFill();
        AllergyTable.getItems().clear();
        for (patient_allergy pa : PA) {
            if (pa.getPatientid().equals(LabelPatientIDAllergy.getText())) {
                for (Allergy a : allergyArrayList) {
                    if (pa.getAllergyName().equals(a.getName())) {
                        System.out.println("EQUAL");
                        AllergyTable.getItems().add(a);
                    }
                }
            }
        }

    }

    @FXML
    void onSelectPatientAllergy(ActionEvent event) throws SQLException {

//        ChoicePatientAllergy.getSelectionModel().select(-1);
  //      ChoicePatientAllergy.setValue(name);

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
