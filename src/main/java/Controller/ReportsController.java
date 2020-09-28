package Controller;

import DBH.patientDAO;
import DBH.therapistDAO;
import Model.Patient;
import Model.Report;
import Model.Therapist;
import Util.MessageAlerter;
import Util.PdfExporter;
import com.itextpdf.text.DocumentException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    @FXML
    private Pane parent;
    @FXML
    private TextField TextFieldID;
    @FXML
    private RadioButton RadioTherapist;
    @FXML
    private RadioButton RadioPatient;
    @FXML
    private Button BtnPreview;
    @FXML
    private Button BtnClear;
    @FXML
    private CheckBox CheckBoxMedicine;
    @FXML
    private CheckBox CheckBoxAllergies;
    @FXML
    private CheckBox CheckBoxMeals;
    @FXML
    private CheckBox CheckBoxRequests;
    @FXML
    private CheckBox CheckBoxPresonalInfo;
    @FXML
    private CheckBox CheckBoxAddress;
    @FXML
    private Button BtnDeSelectAll;
    @FXML
    private Button BtnSelectAll;

    @FXML
    private ImageView ImageViewTherapistExportPDF1;
    @FXML
    private ImageView ImageViewTherapistExportPDF2;
    @FXML
    private ImageView ImageViewTherapistExportFile1;
    @FXML
    private ImageView ImageViewTherapistExportFile2;

    PdfExporter pdfExporter = new PdfExporter();
    MessageAlerter messageAlerter = new MessageAlerter();


    ToggleGroup radioGroup;

    PrinterJob job = PrinterJob.getPrinterJob();

    @FXML
    void OnClickClear(ActionEvent event) {
        CheckBoxMedicine.setDisable(false);
        CheckBoxAllergies.setDisable(false);
        CheckBoxMeals.setDisable(false);
        CheckBoxRequests.setDisable(false);

        CheckBoxMedicine.setSelected(false);
        CheckBoxAllergies.setSelected(false);
        CheckBoxMeals.setSelected(false);
        CheckBoxRequests.setSelected(false);
        CheckBoxPresonalInfo.setSelected(false);
        CheckBoxAddress.setSelected(false);
        RadioTherapist.setSelected(false);
        RadioPatient.setSelected(false);
        TextFieldID.setText("");
    }

    @FXML
    void OnClickDeSelectAll(ActionEvent event) {
        CheckBoxMedicine.setSelected(false);
        CheckBoxAllergies.setSelected(false);
        CheckBoxMeals.setSelected(false);
        CheckBoxRequests.setSelected(false);
        CheckBoxPresonalInfo.setSelected(false);
        CheckBoxAddress.setSelected(false);

    }

    @FXML
    void OnClickPreview(ActionEvent event) {

        RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();

    }


    @FXML
    void OnClickSelectAll(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
        if (selectedRadioButton.getText().equals("Therapist")) {
            CheckBoxPresonalInfo.setSelected(true);
            CheckBoxAddress.setSelected(true);
            CheckBoxMedicine.setDisable(true);
            CheckBoxAllergies.setDisable(true);
            CheckBoxMeals.setDisable(true);
            CheckBoxRequests.setDisable(true);
        } else if (selectedRadioButton.getText().equals("Patient")) {
            CheckBoxPresonalInfo.setSelected(true);
            CheckBoxAddress.setSelected(true);
            CheckBoxMedicine.setDisable(false);
            CheckBoxAllergies.setDisable(false);
            CheckBoxMeals.setDisable(false);
            CheckBoxRequests.setDisable(false);

            CheckBoxMedicine.setSelected(true);
            CheckBoxAllergies.setSelected(true);
            CheckBoxMeals.setSelected(true);
            CheckBoxRequests.setSelected(true);

        } else {
            CheckBoxPresonalInfo.setSelected(true);
            CheckBoxAddress.setSelected(true);
            CheckBoxMedicine.setSelected(true);
            CheckBoxAllergies.setSelected(true);
            CheckBoxMeals.setSelected(true);
            CheckBoxRequests.setSelected(true);
        }

    }


    @FXML
    void TherapistFileClick(MouseEvent event) {

    }

    @FXML
    void TherapistPDFClick(MouseEvent event) throws DocumentException, SQLException, IOException {

    }

    @FXML
    void onClickFile(MouseEvent event) {

    }

    @FXML
    void onClickPDF(MouseEvent event) throws PrinterException, IOException, SQLException, DocumentException {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        System.out.println(filename);




        RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
        if (TextFieldID.getText().equals("")) {
            if (selectedRadioButton.getText().equals("Therapist")) {
                pdfExporter.SaveTherapistPDF();
                messageAlerter.MessageWithoutHeader("Exporting to pdf", "Pdf File Has Been Generated Successfully");
            } else if (selectedRadioButton.getText().equals("Patient")) {
                pdfExporter.SavePatientPDF();
                messageAlerter.MessageWithoutHeader("Exporting to pdf", "Pdf File Has Been Generated Successfully");
            } else
                messageAlerter.ShowErrorMessage("Error", "Can not export file", "* please choose radio to export PDF file");
        }


    }

    @FXML
    void onClickPrint(MouseEvent event) {

    }


    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        radioGroup = new ToggleGroup();
        RadioPatient.setToggleGroup(radioGroup);
        RadioTherapist.setToggleGroup(radioGroup);
        CssStyler();
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

    @FXML
    public void onSelectTherapistRadio(ActionEvent event) {

        CheckBoxMedicine.setDisable(true);
        CheckBoxAllergies.setDisable(true);
        CheckBoxMeals.setDisable(true);
        CheckBoxRequests.setDisable(true);

    }

    @FXML
    public void onSelectPatientRadio(ActionEvent event) {
        CheckBoxMedicine.setDisable(false);
        CheckBoxAllergies.setDisable(false);
        CheckBoxMeals.setDisable(false);
        CheckBoxRequests.setDisable(false);

    }
}
