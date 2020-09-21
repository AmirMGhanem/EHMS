package Controller;
import DBH.AllergyDAO;
import DBH.patient_allergyDAO;
import DBH.patient_medicineDAO;
import Model.*;
import Util.FilesHandler;
import Util.FooterPageEvent;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class MedicinePaneController implements Initializable, Util.JavafxPaneHandler {

    //defining lists for Medicines and patient-med
    public ArrayList<Medicine> ALMED = new ArrayList<Medicine>();
    private ObservableList<Medicine> Medicines = FXCollections.observableArrayList(ALMED);
    private ObservableList<Model.Patient> Patients = FXCollections.observableArrayList();
    DBH.medicineDAO MDH = new DBH.medicineDAO();
    ObservableList Choicelist = FXCollections.observableArrayList();

    //defining lists for Allergies and patient-allergy
    ObservableList allergyOvservableList = FXCollections.observableArrayList();
    ArrayList<Allergy> allergyArrayList = new ArrayList<Allergy>();
    DBH.AllergyDAO Ado = new AllergyDAO();

    private ArrayList<patient_allergy> PA = new ArrayList<patient_allergy>();
     ObservableList<patient_allergy> paObservablelist= FXCollections.observableArrayList();
     DBH.patient_allergyDAO paDAO=  new patient_allergyDAO();



    @FXML
    private Button BtnLoadData;

    @FXML
    private ChoiceBox<String> ChoicePatient;

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

    @FXML
    private TableColumn<Medicine, Number> ColMedTimes;

    @FXML
    private Button BtnRemoveMed;

    @FXML
    private Button BtnExportMedPDF;

    @FXML
    private Button BtnExportMedFile;

    @FXML
    private Button BtnAttachMed;

    @FXML
    private TableView<Allergy> AllergyTable;

    @FXML
    private TableColumn<Allergy, String> ColAllergyName;


    @FXML
    private TableColumn<Allergy,String> ColMedicine;

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
    private Label LabelUpdateAttach;

    @FXML
    private Label LabelLoadUpdate;

    @FXML
    private Button BtnDetachMed;
    @FXML
    private Button BtnLoadAllData;
    @FXML
    private Button BtnLoadID;




    @FXML
    void OnClickBtnDetachMed(ActionEvent event) throws SQLException {
        ArrayList<Model.patient_medicine> pmlist = new ArrayList<Model.patient_medicine>();
        int mednum = MedTable.getSelectionModel().getSelectedItem().getMedicineNum();

        patient_medicineDAO pmDAO = new patient_medicineDAO();
        pmlist = pmDAO.selectAll();

        for (Model.patient_medicine pm : pmlist) {
            if (pm.getMedicinenum() == mednum) {
                pmDAO.removeByMedicineNum(mednum, pm.getPatientid());
                LabelUpdateAttach.setText("Detached!");
            }
        }

    }


    @FXML
    void OnClickBtnAttachMed(ActionEvent event)   {

        int MedNumForAttach = MedTable.getSelectionModel().getSelectedItem().getMedicineNum();
        String PatientidForAttach = LabelPatientID.getText();

        DBH.patient_medicineDAO pmdo = new patient_medicineDAO();

        try {
            if (pmdo.insertToPatient_Medicine(PatientidForAttach, MedNumForAttach) == 0)
                LabelUpdateAttach.setText("Unsuccessfully");
            else
                LabelUpdateAttach.setText("Successfully Added");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cannot Add The Same Medicine \nTwice To The Same Patient", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }


    }

    @FXML
    void OnClickBtnExportMedFile(ActionEvent event) throws IOException {
        Util.FilesHandler fh = new FilesHandler();
        fh.SaveMedicines();



    }

    @FXML
    void OnClickBtnExportMedPDF(ActionEvent event) throws IOException, DocumentException, SQLException {
        Document document = new Document();
        Font font;
        PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/Files/PDF/MedicinesPDF.pdf"));
        document.open();
        //Adding the footer to the pdf file, by class created on the utils
        Util.FooterPageEvent footer = new FooterPageEvent();
        writer.setPageEvent(footer);

        //creating paragraph
        Paragraph p1 = new Paragraph();
        Paragraph pNew5Lines = new Paragraph();
        for(int i=0 ; i<5;i++)
            p1.add("\n");

        //Printing Chunk Text on the pdf

        p1.add("#        Medicine Name        Medicine Type        Medicine Times Per Day      ");
        p1.add("\n--------------------------------------------------------------------------------------------\n");
        font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

        for(String s : MDH.MedicinesPDF())
        {
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

    }

    @FXML
    void OnClickBtnRemoveMed(ActionEvent event) throws SQLException {
        ArrayList<Model.patient_medicine> pmlist = new ArrayList<Model.patient_medicine>();
        int mednum = MedTable.getSelectionModel().getSelectedItem().getMedicineNum();
        boolean flag = false;
        patient_medicineDAO pmDAO = new patient_medicineDAO();
        pmlist = pmDAO.selectAll();

        for (Model.patient_medicine pm : pmlist) {
            if (pm.getMedicinenum() == mednum) {
                flag = true;
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please Detach First From All Patients", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();

            }
        }
        if (flag == false) {
            MDH.removeMedicineByID(mednum);
            MedTable.getItems().removeAll(MedTable.getSelectionModel().getSelectedItem());
        }

    }

    @FXML
    void OnLoadDataClick(ActionEvent event) throws SQLException {
        ObservableList<patient_medicine> pmlist = FXCollections.observableArrayList();
        patient_medicineDAO pmDAO = new patient_medicineDAO();
        pmlist = pmDAO.selectAllObservable();
        MedTable.getItems().removeAll();


        JavafxTableFill();
        MedTable.getItems().clear();
        for (patient_medicine pm : pmlist) {
            if (pm.getPatientid().equals(LabelPatientID.getText())) {
                for (Medicine m : Medicines) {
                    if(m.getMedicineNum()==pm.getMedicinenum())
                        MedTable.getItems().add(m);
                }
            }
        }

        //----------------------------------------------------------------------------- LOAD ALLERGY

        ObservableList<patient_allergy> palist = FXCollections.observableArrayList();
        palist = paDAO.selectAllObservable();
        AllergyTable.getItems().removeAll();

        JavafxTableFill();
        AllergyTable.getItems().clear();
        for (patient_allergy pa : palist) {
            if (pa.getPatientid().equals(LabelPatientID.getText())) {
                for (Allergy a : allergyArrayList) {
                    if(pa.getAllergyName().equals(a.getName()))
                    {
                        System.out.println("EQUAL");
                        AllergyTable.getItems().add(a);}

                }
            }
        }

    }


    @FXML
    void OnLoadAllDataClick(ActionEvent event) throws SQLException, InterruptedException {
        JavafxTableFill();
        MedTable.setItems(Medicines);
        AllergyTable.setItems(allergyOvservableList);


        //TimeUnit.SECONDS.sleep(2);
    }





    @FXML
    void OnLoadIDClick(ActionEvent event) {
        for (Patient p : Patients) {
            if (p.getName().equals(ChoicePatient.getValue())) {
                LabelPatientID.setText(p.getID());
                LabelLoadUpdate.setText("Loaded");
            }
        }
    }

    private void TableInit() throws SQLException {
        //Table Init
        ColMedNum.setCellValueFactory(new PropertyValueFactory<Medicine, Number>("medicineNum"));
        ColMedName.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        ColMedType.setCellValueFactory(new PropertyValueFactory<Medicine, String>("type"));
        ColMedTimes.setCellValueFactory(new PropertyValueFactory<Medicine, Number>("timesPerDay"));
        JavafxTableFill();
        MedTable.setItems(Medicines);


        ColAllergyName.setCellValueFactory(new PropertyValueFactory<Allergy,String>("name"));
        ColMedicine.setCellValueFactory(CellData->new SimpleStringProperty(CellData.getValue().getMedicines().getName()));
        JavafxTableFill();
        AllergyTable.setItems(allergyOvservableList);
    }



    @FXML
    void onClickBtnExportAllergyFile(ActionEvent event) throws IOException {
        Util.FilesHandler fh = new FilesHandler();
        fh.SaveAllergies();
    }

    @FXML
    void onClickBtnExportAllergyPDF(ActionEvent event) throws IOException, DocumentException, SQLException {
        Document document = new Document();
        Font font;
        PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/Files/PDF/AllergiesPDF.pdf"));
        document.open();
        //Adding the footer to the pdf file, by class created on the utils
        Util.FooterPageEvent footer = new FooterPageEvent();
        writer.setPageEvent(footer);

        //creating paragraph
        Paragraph p1 = new Paragraph();
        Paragraph pNew5Lines = new Paragraph();
        for(int i=0 ; i<5;i++)
            p1.add("\n");

        //Printing Chunk Text on the pdf

        p1.add(" Allergy Name                       MedicineName ");
        p1.add("\n----------------------------------------------------------------------\n");
        font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

        for(String s : Ado.AllergiesPDF())
        {
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please Detach First From All Patients", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();

            }
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
            if (pa.getAllergyName().equals(allergyname)) {
                paDAO.removeByAllergyname(pa);
                LabelUpdateAttach.setText("Detached!");
            }
        }

    }
    @FXML
    void OnClickBtnAttachAllergy(ActionEvent event) {
        String Allergyname = AllergyTable.getSelectionModel().getSelectedItem().getName();
        String PatientidForAttach = LabelPatientID.getText();
        patient_allergy pm = new patient_allergy(PatientidForAttach,Allergyname);
        try {
            if (paDAO.insertToPatient_allergy(pm) == 0)
                LabelUpdateAttach.setText("Unsuccessfully");
            else
                LabelUpdateAttach.setText("Successfully Added");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cannot Attach the same Allergy \nTwice To The Same Patient", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
    }

    //Initilaizable and javafx handler implement


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
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

        Medicines = MDH.selectMedicines();
        ALMED = MDH.selectAll();
        allergyOvservableList=Ado.selectAllObservable();
        allergyArrayList = Ado.selectAll();



    }

    @Override
    public void JavafxChoiceFill() throws SQLException {

        Choicelist.removeAll();

        for (Patient p : Patients)
            Choicelist.add(p.getName());
        ChoicePatient.setValue("Choose Patient");
        ChoicePatient.getItems().addAll(Choicelist);
    }

    @Override
    public void JavafxDiagramFill() throws IOException {

    }


}
