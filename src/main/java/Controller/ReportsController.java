package Controller;

import Model.Report;
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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.*;
import java.awt.*;
import java.awt.desktop.PrintFilesEvent;
import java.awt.desktop.PrintFilesHandler;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    @FXML private Pane parent;
    @FXML private TextField TextFieldID;
    @FXML private RadioButton RadioTherapist;
    @FXML private RadioButton RadioPatient;
    @FXML private RadioButton RadioStaff;
    @FXML private Button BtnPreview;
    @FXML private Button BtnClear;
    @FXML private CheckBox CheckBoxMedicine;
    @FXML private CheckBox CheckBoxAllergies;
    @FXML private CheckBox CheckBoxMeals;
    @FXML private CheckBox CheckBoxRequests;
    @FXML private CheckBox CheckBoxPresonalInfo;
    @FXML private CheckBox CheckBoxAddress;
    @FXML private CheckBox CheckBoxHR;
    @FXML private CheckBox CheckBoxSchedule;
    @FXML private Button BtnDeSelectAll;
    @FXML private Button BtnSelectAll;
    @FXML private ImageView ImageViewTherapistExportPDF1;
    @FXML private ImageView ImageViewTherapistExportPDF2;
    @FXML private ImageView ImageViewTherapistExportFile1;
    @FXML private ImageView ImageViewTherapistExportFile2;

    public ArrayList<Report> ALREPORTS = new ArrayList<Report>();
    ToggleGroup radioGroup;

    PrinterJob job = PrinterJob.getPrinterJob();

    @FXML
    void OnClickClear(ActionEvent event) {
        CheckBoxMedicine.setSelected(false);
        CheckBoxAllergies.setSelected(false);
        CheckBoxMeals.setSelected(false);
        CheckBoxRequests.setSelected(false);
        CheckBoxPresonalInfo.setSelected(false);
        CheckBoxAddress.setSelected(false);
        CheckBoxHR.setSelected(false);
        CheckBoxSchedule.setSelected(false);
        RadioTherapist.setSelected(false);
        RadioStaff.setSelected(false);
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
        CheckBoxHR.setSelected(false);
        CheckBoxSchedule.setSelected(false);
    }

    @FXML
    void OnClickPreview(ActionEvent event) {

        RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        if (CheckBoxMedicine.isSelected())
            TextFieldID.setText(toogleGroupValue);
    }

    @FXML
    void OnClickSelectAll(ActionEvent event) {
        CheckBoxMedicine.setSelected(true);
        CheckBoxAllergies.setSelected(true);
        CheckBoxMeals.setSelected(true);
        CheckBoxRequests.setSelected(true);
        CheckBoxPresonalInfo.setSelected(true);
        CheckBoxAddress.setSelected(true);
        CheckBoxHR.setSelected(true);
        CheckBoxSchedule.setSelected(true);
    }


    @FXML
    void TherapistFileClick(MouseEvent event) {

    }

    @FXML
    void TherapistPDFClick(MouseEvent event) {


    }

    @FXML
    void onClickFile(MouseEvent event) {

    }

    @FXML
    void onClickPDF(MouseEvent event) throws PrinterException, IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        System.out.println(filename);

        // Desktop desktop = Desktop.getDesktop();
        //  desktop.print(new File("src/main/resources/Files/PDF/PatientPDF.pdf"));

    }

    @FXML
    void onClickPrint(MouseEvent event) {

    }

    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        radioGroup = new ToggleGroup();
        RadioPatient.setToggleGroup(radioGroup);
        RadioStaff.setToggleGroup(radioGroup);
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
}
