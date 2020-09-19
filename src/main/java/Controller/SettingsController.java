package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML private ColorPicker ColorPickerThemeDesgin;
    @FXML private ColorPicker ColorPickerButtonColor;
    @FXML private ColorPicker ColorPickerTextColor;
    @FXML private Slider SliderFontSize;
    @FXML private Button BtnSaveDesign;
    @FXML private ToggleButton ToggleBtnDarkMode;
    @FXML private ToggleGroup Off;
    @FXML private TextField TextFieldDBURL;
    @FXML private TextField TextFieldUserName;
    @FXML private TextField TextFieldPassword;
    @FXML private Button BtnEstablishConnection;
    @FXML private TextField TextFieldDataBase;
    @FXML private ChoiceBox<?> ChoiceTimeZone;
    @FXML private DatePicker DatePicker;
    @FXML private TextField TextFieldEmail;
    @FXML private Button BtnSystemSave;




    @FXML
    void DarkModeTogglePressed(ActionEvent event) {

    }

    @FXML
    void OnClickEstablishConn(ActionEvent event) {



    }

    @FXML
    void OnClickSaveDesign(ActionEvent event) {

    }

    @FXML
    void OnClickSystemSave(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColorPickerButtonColor.setValue(Color.BLUE);

        ColorPickerButtonColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String c = String.valueOf(ColorPickerButtonColor.getValue());
                System.out.println(c);
                BtnEstablishConnection.setStyle("-fx-background-color: #"+c.charAt(2)+c.charAt(3)+c.charAt(4)+c.charAt(5)+c.charAt(6)+c.charAt(7));
                System.out.println("fx-background-color: #"+c.charAt(2)+c.charAt(3)+c.charAt(4)+c.charAt(5)+c.charAt(6)+c.charAt(7));
            }
        });
    }

}
