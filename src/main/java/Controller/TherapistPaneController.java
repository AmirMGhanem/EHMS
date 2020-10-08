package Controller;

import Model.*;
import Util.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class TherapistPaneController implements Initializable, Util.JavafxPaneHandler {

    private ArrayList<Therapist> ALTHERAPIST = new ArrayList<Therapist>();
    private ObservableList<Therapist> Therapist = FXCollections.observableArrayList(ALTHERAPIST);
    DBH.therapistDAO TDBH = new DBH.therapistDAO();

    public ObservableList<Model.Therapist> getTherapist() {
        return Therapist;
    }

    public void setTherapist(ObservableList<Model.Therapist> therapist) {
        Therapist = therapist;
    }
//Getters and setters and add methods

    MessageAlerter ma = new MessageAlerter();
    PdfExporter pdfExporter = new PdfExporter();

    @FXML
    public Pane parent;
    @FXML
    public TableView<Therapist> NurseTable;
    @FXML
    public TableColumn<Therapist, String> ColID;
    @FXML
    public TableColumn<Therapist, String> ColName;
    @FXML
    public TableColumn<Therapist, String> ColAddress;
    @FXML
    private TableColumn<Therapist, Number> ColAddressCode;
    @FXML
    private TableColumn<Therapist, String> ColAddressCity;
    @FXML
    private TableColumn<Therapist, String> ColAddressStreet;
    @FXML
    private TableColumn<Therapist, Number> ColAddressHouse;
    @FXML
    public TableColumn<Therapist, String> ColGender;
    @FXML
    public TableColumn<Person, Date> ColBdate;
    @FXML
    public TableColumn<Model.Therapist, Number> ColExperience;
    @FXML
    public TableColumn<Therapist, String> ColContactNo;
    @FXML
    private Button BtnRemoveNurse;
    @FXML
    private Button BtnNurseInvest;
    @FXML
    private Button BtnNurseFile;
    @FXML
    private Button BtnNurseXML;
    @FXML
    private Button BtnSpecNurseFile;
    Util.FilesHandler fh;
    @FXML
    void OnClickInvestigation(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("WorkingSchedule");
        Stage stage = new Stage();
        stage.setTitle("Therapist Working Schedule Window");
        stage.setScene(new Scene(view, 1254, 800));
         stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    void OnClickRemove(ActionEvent event) throws SQLException {
        String id = NurseTable.getSelectionModel().getSelectedItem().getID();
        int addressCode = NurseTable.getSelectionModel().getSelectedItem().getAddress().getAddresscode();
        System.out.println(id);
        System.out.println(addressCode);
        TDBH.removeTherapistByID(id, addressCode);
        NurseTable.getItems().removeAll(NurseTable.getSelectionModel().getSelectedItem());

        ma.MessageWithoutHeader("Removed", "Therapist Removed Successfully :)");
    }

    @FXML
    void OnClickSpecToFile(ActionEvent event) {

        String id = "";
        try {
            id = NurseTable.getSelectionModel().getSelectedItem().getID();
        } catch (Exception e) {
            ma.MessageWithoutHeader("Unexpected Error", "Chose Specific Therapist");
        }
        for (Model.Therapist t : ALTHERAPIST)
            if (t.getID().equals(id)) {
                fh.SaveSpecificNurse(t);
                ma.MessageWithoutHeader("Exported", "Specific Therapist Exported To PDF");
            }
    }

    @FXML
    void OnClickToFile(ActionEvent event) throws IOException {

        fh.SaveNurse();
        ma.MessageWithoutHeader("Exported", "Therapists Exported To FILE");
    }

    @FXML
    void OnClickToPDF(ActionEvent event) throws IOException, DocumentException, URISyntaxException, SQLException {

        pdfExporter.SaveTherapistPDF();
        ma.MessageWithoutHeader("Exported", "Therapists Exported To PDF");
    }

    @FXML
    void OnClickBtnPdfWithSched(ActionEvent event) throws IOException, DocumentException {
        try {
            pdfExporter.SaveTherapistPDF();
            TableExporter tableExporter = new TableExporter();
            tableExporter.TableExport();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Button btn = (Button) event.getTarget();
        Parent p =  btn.getParent();
        Scene scene =  p.getScene();
        Window window = scene.getWindow();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(window);
        if (selectedDirectory != null) {
            pdfExporter.PdfConcatenate(selectedDirectory.getAbsolutePath());
        }
    }

    public void transferMessage(Therapist t) throws SQLException {
        ALTHERAPIST.add(t);
        NurseTable.getItems().clear();
        NurseTable.getItems().addAll(ALTHERAPIST);
        NurseTable.setItems(Therapist);
        for (Therapist t1 : ALTHERAPIST)
            System.out.println("TESTTTTTTTTTT" + t1.toString());
        JavafxTableFill();
    }

    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        CssStyler();
        try {
            fh = new FilesHandler();
            TableInit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    private void TableInit() throws SQLException {
        //Table Init
        ColID.setCellValueFactory(new PropertyValueFactory<Model.Therapist, String>("ID"));
        ColName.setCellValueFactory(new PropertyValueFactory<Model.Therapist, String>("Name"));
        ColGender.setCellValueFactory(new PropertyValueFactory<Model.Therapist, String>("Gender"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<Model.Therapist, String>("Address"));
        ColAddressCode.setCellValueFactory(CellData -> new SimpleIntegerProperty(CellData.getValue().getAddress().getAddresscode()));
        ColAddressCity.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getAddress().getCity()));
        ColAddressStreet.setCellValueFactory(CellData -> new SimpleStringProperty(CellData.getValue().getAddress().getStreet()));
        ColAddressHouse.setCellValueFactory(CellData -> new SimpleIntegerProperty(CellData.getValue().getAddress().getHouseNum()));
        ColBdate.setCellValueFactory(new PropertyValueFactory<Person, Date>("date"));
        ColExperience.setCellValueFactory(CellData -> new SimpleIntegerProperty(CellData.getValue().getExperience()));
        ColContactNo.setCellValueFactory(new PropertyValueFactory<Therapist, String>("ContactNo"));
        ColExperience.setEditable(true);
        //add your data to the table here.
        JavafxTableFill();
        NurseTable.setItems(Therapist);
    }

    //Overrided by implementing JavafxPaneHandler
    @Override
    public void JavafxTableFill() throws SQLException {
        ALTHERAPIST = TDBH.selectAll();
        Therapist = TDBH.selectTherapists();
    }

    @Override
    public void JavafxChoiceFill() {
    }

    @Override
    public void JavafxDiagramFill() {

    }
}