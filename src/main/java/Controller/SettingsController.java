package Controller;

import DBH.userInfoDAO;
import Model.UserInfo;
import Util.CssFile;
import Util.IValidations;
import Util.MessageAlerter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SettingsController implements Initializable, IValidations {

    ObservableList BlendModelist = FXCollections.observableArrayList();
    ObservableList Fontlist = FXCollections.observableArrayList();
    DBH.userInfoDAO uiDAo = new userInfoDAO();

    MessageAlerter ma = new MessageAlerter();


    @FXML
    private Pane SettingPanel;
    @FXML
    private Pane TopPanel;
    @FXML
    private TabPane TablPaneThemeDesign;
    @FXML
    private ColorPicker ColorPickerThemeDesgin;
    @FXML
    private ColorPicker ColorPickerButtonColor;
    @FXML
    private ColorPicker ColorPickerTopPaneColor;
    @FXML
    private Slider SliderFontSize;
    @FXML
    private ColorPicker ColorPickerTextColor;
    @FXML
    private ColorPicker ColorPickerBorderColor;
    @FXML
    private ChoiceBox<String> ChoiceBlendMode;
    @FXML
    private ToggleButton ToggleBtnDarkMode;
    @FXML
    private ToggleGroup Off;
    @FXML
    private Button BtnSetDefault;
    @FXML
    private Button BtnSetCustom;
    @FXML
    private TabPane TabPaneSystem;
    @FXML
    private ChoiceBox<?> ChoiceTimeZone;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private ChoiceBox<String> ChoiceFontFamily;
    @FXML
    private TextField TextFieldEmail;
    @FXML
    private Button BtnRegister;
    @FXML
    private TextField TextFieldRegisterUser;
    @FXML
    private TextField TextFieldRegisterPass;

    static boolean CustomeDesignFlag = false;

    public boolean isCustomeDesignFlag() {
        return CustomeDesignFlag;
    }

    public void setCustomeDesignFlag(boolean customeDesignFlag) {
        CustomeDesignFlag = customeDesignFlag;
    }

    @FXML
    void OnClickBtnRegister(ActionEvent event) throws SQLException {
        if (!(nameValidation(TextFieldRegisterUser.getText()) && nameValidation(TextFieldRegisterPass.getText())))
            ma.ShowErrorMessage("Error", "Incorrect Inputs", "Please Make Sure That The Registering Information You \n Inserted Contains Text Only");
        else {
            UserInfo ui = new UserInfo(TextFieldRegisterUser.getText(), TextFieldRegisterPass.getText());
            uiDAo.inserUser(ui);
            ma.MessageWithoutHeader("Successfully", "Registered Successfully");
        }
    }

    @FXML
    void DarkModeTogglePressed(ActionEvent event) {
    }

    static boolean flagtoggle = false; //false == light

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (flagtoggle == true) {
            SettingPanel.getStylesheets().clear();
            ToggleBtnDarkMode.setSelected(true);
            String css = this.getClass().getResource("/Css/darkmode.css").toExternalForm();
            SettingPanel.getStylesheets().add(css);
        } else {
            SettingPanel.getStylesheets().clear();
            ToggleBtnDarkMode.setSelected(false);
            String css = this.getClass().getResource("/Css/lightmode.css").toExternalForm();
            SettingPanel.getStylesheets().add(css);
        }
        ColorPickerThemeDesgin.setValue(Color.valueOf("#f2f2f2"));
        ColorPickerButtonColor.setValue(Color.valueOf("#787878"));
        ColorPickerTopPaneColor.setValue(Color.valueOf("#59b7ff"));
        ColorPickerTextColor.setValue(Color.valueOf("#000000"));
        ColorPickerBorderColor.setValue(Color.valueOf("#59b7ff"));

        ColorPickerThemeDesgin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String c = String.valueOf(ColorPickerThemeDesgin.getValue());
                System.out.println(c);
                SettingPanel.setStyle("-fx-background-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7));
                System.out.println("fx-background-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7));
            }
        });

        ColorPickerButtonColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String c = String.valueOf(ColorPickerButtonColor.getValue());
                System.out.println(c);
                BtnSetDefault.setStyle("-fx-background-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7));
                System.out.println("fx-background-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7));
            }
        });

        ColorPickerTopPaneColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String c = String.valueOf(ColorPickerTopPaneColor.getValue());
                System.out.println(c);
                TopPanel.setStyle("-fx-background-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7));
                System.out.println("fx-background-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7));
            }
        });

        ColorPickerTextColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String c = String.valueOf(ColorPickerTextColor.getValue());
                String textColor = "-fx-text-fill: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7);
                //or txtSearch.setStyle("-fx-text-fill: #BA55D3;");
                BtnSetDefault.setStyle(textColor);
                System.out.println(textColor);
            }
        });

        ColorPickerBorderColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String c = String.valueOf(ColorPickerBorderColor.getValue());
                System.out.println(c);
                String color = "-fx-border-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7);
                TablPaneThemeDesign.setStyle(color);
                TabPaneSystem.setStyle(color);
                System.out.println("fx-background-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7));
            }
        });

        ToggleBtnDarkMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (ToggleBtnDarkMode.isSelected()) {
                    ToggleBtnDarkMode.setText("On");
                    System.out.println("Toggle On");
                    SettingPanel.getStylesheets().clear();
                    flagtoggle = true;
                    String css = this.getClass().getResource("/Css/darkmode.css").toExternalForm();
                    SettingPanel.getStylesheets().add(css);
                    System.out.println(css);
                } else {
                    System.out.println("Toggle Off");
                    ToggleBtnDarkMode.setText("Off");
                    SettingPanel.getStylesheets().clear();
                    flagtoggle = false;
                    String css = this.getClass().getResource("/Css/lightmode.css").toExternalForm();
                    SettingPanel.getStylesheets().add(css);
                }
            }
        });
    }

    public void onSliderChanged(MouseEvent mouseEvent) {
        double slideValue = SliderFontSize.getValue();
        BtnSetDefault.setStyle("-fx-font-size: " + slideValue);
    }

    public void OnSelectFamilyStyle(ActionEvent event) {
        String font = ChoiceFontFamily.getValue();
        BtnSetDefault.setFont(Font.font(font));
    }

    @FXML
    void OnClickBtnSetCustom(ActionEvent event) {
        setCustomeDesignFlag(true);
        ma.MessageWithoutHeader("Design Armed To Project", "Css File [Css/UserCustomDesign.css] \n" +
                "has been Armed to the project, " +
                "to disarm please press disarm");
    }

    @FXML
    void OnClickDefault(ActionEvent event) throws IOException {
        setCustomeDesignFlag(false);
        ma.MessageWithoutHeader("Design disarmed from Project", "Css File [Css/UserCustomDesign.css] \n" +
                "has been disarmed \n" +
                "you are using the default [Css/lightmode.css] style");
    }
@FXML public void OnClickSave(ActionEvent event) throws IOException, URISyntaxException {
        CssFile cssfile = new CssFile();
        String url = ("src/main/resources/Css/userDesign.css");
        //------------------------------------------
        String cTheme = String.valueOf(ColorPickerThemeDesgin.getValue());
        String ThemeStyle = ("-fx-background-color: #" + cTheme.charAt(2) + cTheme.charAt(3) + cTheme.charAt(4) + cTheme.charAt(5) + cTheme.charAt(6) + cTheme.charAt(7));
        //------------------------------------------
        String cButton = String.valueOf(ColorPickerButtonColor.getValue());
        String ButtonStyle = ("-fx-background-color: #" + cButton.charAt(2) + cButton.charAt(3) + cButton.charAt(4) + cButton.charAt(5) + cButton.charAt(6) + cButton.charAt(7));
        //------------------------------------------
        String cTop = String.valueOf(ColorPickerTopPaneColor.getValue());
        String TopPaneStyle = ("-fx-background-color: #" + cTop.charAt(2) + cTop.charAt(3) + cTop.charAt(4) + cTop.charAt(5) + cTop.charAt(6) + cTop.charAt(7));
        //-----------------------------------------
        Double FontSize = SliderFontSize.getValue();
        String FontSizeStyle = ("-fx-font-size: " + FontSize);
        //------------------------------------------
        String cTextColor = String.valueOf(ColorPickerTextColor.getValue());
        String textColorStyle = "-fx-text-fill: #" + cTextColor.charAt(2) + cTextColor.charAt(3) + cTextColor.charAt(4) + cTextColor.charAt(5) + cTextColor.charAt(6) + cTextColor.charAt(7);
        //------------------------------------------
        String str = ".Panel{\n" + ThemeStyle + ";\n" + FontSizeStyle + ";\n" + textColorStyle + ";\n}";
        str = str + "\n.Button{\n" +
                ButtonStyle + ";\n" +
                textColorStyle + ";\n}";
        str = str + "\n.ParentPane{\n" + ThemeStyle + ";\n" + FontSizeStyle + ";\n" + textColorStyle + "\n}";
        str = str + "\n.TopPane{\n" + TopPaneStyle + ";\n}";
        str = str + "\n.Label{\n" + textColorStyle + ";\n}";
        cssfile.CreateFile("src/main/resources/Css/UserCustomDesign.css", str);

        ma.MessageWithoutHeader("Style Saved", "The System need to restart \nin order to set your last custom design.");
    }

    public Boolean getToggleMode() {
        return ToggleBtnDarkMode.isSelected();
    }
}