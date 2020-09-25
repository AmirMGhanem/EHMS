package Controller;

import Util.CssFile;
import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    ObservableList BlendModelist = FXCollections.observableArrayList();
    ObservableList Fontlist = FXCollections.observableArrayList();

    @FXML private Pane SettingPanel;
    @FXML private Pane TopPanel;
    @FXML private TabPane TablPaneThemeDesign;
    @FXML private ColorPicker ColorPickerThemeDesgin;
    @FXML private ColorPicker ColorPickerButtonColor;
    @FXML private ColorPicker ColorPickerTopPaneColor;
    @FXML private Slider SliderFontSize;
    @FXML private ColorPicker ColorPickerTextColor;
    @FXML private ColorPicker ColorPickerBorderColor;
    @FXML private ChoiceBox<String> ChoiceBlendMode;
    @FXML private ToggleButton ToggleBtnDarkMode;
    @FXML private ToggleGroup Off;
    @FXML private Button BtnSetDefault;
    @FXML private TabPane TabPaneSystem;
    @FXML private ChoiceBox<?> ChoiceTimeZone;
    @FXML private DatePicker DatePicker;
    @FXML private ChoiceBox<String> ChoiceFontFamily;
    @FXML private TextField TextFieldEmail;
    @FXML private Button BtnSystemSave;

    @FXML
    void DarkModeTogglePressed(ActionEvent event) {

    }

    @FXML
    void OnClickSystemSave(ActionEvent event) {

    }

    public void JavafxChoiceFill() {
        BlendModelist.addAll("SRC_OVER", "SRC_ATOP", "ADD", "MULTIPLY", "SCREEN", "OVERLAY", "DARKEN", "LIGHTEN", "COLOR_DODGE", "COLOR_BURN", "HARD_LIGHT", "SOFT_LIGHT", "DIFFERENCE", "EXCLUSION", "RED", "GREEN", "BLUE");
        ChoiceBlendMode.getItems().addAll(BlendModelist);
        ChoiceBlendMode.setValue("SRC_OVER");

        Fontlist.addAll("System", "Aharoni", "Arial", "Traditional Arabic", "Viner Hand ITC");
        ChoiceFontFamily.getItems().addAll(Fontlist);
        ChoiceFontFamily.setValue("System");
    }

    static boolean flagtoggle = false; //false == light


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


            JavafxChoiceFill();

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


                    //---------------------

                    //Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
                    //StyleManager.getInstance().addUserAgentStylesheet(css);


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

    public void OnSelectBlendMode(ActionEvent event) {
        String blendmode = ChoiceBlendMode.getValue().toString();
        SettingPanel.setBlendMode(BlendMode.valueOf(blendmode));
        System.out.println(blendmode);
    }

    public void onSliderChanged(MouseEvent mouseEvent) {
        double slideValue = SliderFontSize.getValue();
        System.out.println(slideValue);
        BtnSetDefault.setStyle("-fx-font-size: " + slideValue);
    }

    public void OnSelectFamilyStyle(ActionEvent event) {
        String font = ChoiceFontFamily.getValue();
        BtnSetDefault.setFont(Font.font(font));
    }

    @FXML
    void OnClickDefault(ActionEvent event) throws IOException {
        System.out.println("Set Default Clicked");
        flagtoggle = false ;
        SettingPanel.getStylesheets().clear();
        String css = this.getClass().getResource("/Css/lightmode.css").toExternalForm();
        SettingPanel.getStylesheets().add(css);
    }

    public void OnClickSave(ActionEvent event) throws IOException, URISyntaxException {
        CssFile cssfile = new CssFile();
        String url =("src/main/resources/Css/userDesign.css");

        String PaneColor = ColorPickerThemeDesgin.getValue().toString();
        String ButtonColor = ColorPickerButtonColor.getValue().toString();
        String TopPaneColor = ColorPickerTopPaneColor.getValue().toString();
        String blendmode = ChoiceBlendMode.getValue().toString();
        Double FontSize = SliderFontSize.getValue();
        String TextColor = ColorPickerTextColor.getValue().toString();
        String BorderColor = ColorPickerBorderColor.getValue().toString();
        String FontFamily = ChoiceFontFamily.getValue();
    }



    public Boolean getToggleMode() {

        return ToggleBtnDarkMode.isSelected();
    }
}




/*

String PaneColor = ColorPickerThemeDesgin.getValue().toString();
        String ButtonColor = ColorPickerButtonColor.getValue().toString();
        String TopPaneColor = ColorPickerTopPaneColor.getValue().toString();
        String blendmode = ChoiceBlendMode.getValue().toString();
        Double FontSize = SliderFontSize.getValue();
        String TextColor = ColorPickerTextColor.getValue().toString();
        String BorderColor = ColorPickerBorderColor.getValue().toString();
        String FontFamily = ChoiceFontFamily.getValue();


        CssFile cssfile = new CssFile();
        String url =("src/main/resources/Css/userDesign.css");

        if(PaneColor != null && ButtonColor != null && TopPaneColor != null && TextColor != null && BorderColor != null)
        {
            String PaneCss = ".Pane{" +
                    "\n-fx-background-color : #" + PaneColor.charAt(2) + PaneColor.charAt(3) + PaneColor.charAt(4) + PaneColor.charAt(5) + PaneColor.charAt(6) + PaneColor.charAt(7) + ";" +
                    "\n-fx-border-color : #" + BorderColor.charAt(2) + BorderColor.charAt(3) + BorderColor.charAt(4) + BorderColor.charAt(5) + BorderColor.charAt(6) + BorderColor.charAt(7) + ";" +
                    "\n-fx-blend-mode : #" + blendmode + ";" +
                    "\n}";
            String TopPaneCss = "\n\n.TopPane{" +
                    "\n-fx-background-color : #" + TopPaneColor.charAt(2) + TopPaneColor.charAt(3) + TopPaneColor.charAt(4) + TopPaneColor.charAt(5) + TopPaneColor.charAt(6) + TopPaneColor.charAt(7) + ";" +
                    "\n-fx-border-color : #" + BorderColor.charAt(2) + BorderColor.charAt(3) + BorderColor.charAt(4) + BorderColor.charAt(5) + BorderColor.charAt(6) + BorderColor.charAt(7) + ";" +
                    "\n}";
            String ButtonCss = "\n\n.Button{" +
                    "\n-fx-background-color : #" + ButtonColor.charAt(2) + ButtonColor.charAt(3) + ButtonColor.charAt(4) + ButtonColor.charAt(5) + ButtonColor.charAt(6) + ButtonColor.charAt(7) + ";" +
                    "\n-fx-border-color : #" + BorderColor.charAt(2) + BorderColor.charAt(3) + BorderColor.charAt(4) + BorderColor.charAt(5) + BorderColor.charAt(6) + BorderColor.charAt(7) + ";" +
                    "\n-fx-font-size : " + FontSize + ";" +
                    "\n-fx-font-family : " + FontFamily + ";" +
                    "\n-fx-text-fill : " + TextColor.charAt(2) + TextColor.charAt(3) + TextColor.charAt(4) + TextColor.charAt(5) + TextColor.charAt(6) + TextColor.charAt(7) + ";" +
                    "\n}";
            String ChoiceCss = "\n\n.Choice{" +
                    "\n-fx-border-color : #" + BorderColor.charAt(2) + BorderColor.charAt(3) + BorderColor.charAt(4) + BorderColor.charAt(5) + BorderColor.charAt(6) + BorderColor.charAt(7) + ";" +
                    "\n-fx-font-family : " + FontFamily + ";" +
                    "\n-fx-text-fill : " + TextColor.charAt(2) + TextColor.charAt(3) + TextColor.charAt(4) + TextColor.charAt(5) + TextColor.charAt(6) + TextColor.charAt(7) + ";" +
                    "\n}";
            String TextFieldCss = "\n\n.TextField{" +
                    "\n-fx-font-family : " + FontFamily + ";" +
                    "\n-fx-text-fill : " + TextColor.charAt(2) + TextColor.charAt(3) + TextColor.charAt(4) + TextColor.charAt(5) + TextColor.charAt(6) + TextColor.charAt(7) + ";" +
                    "\n}";
            String LabelCss = "\n\n.Label{" +
                    "\n-fx-font-family : " + FontFamily + ";" +
                    "\n-fx-text-fill : " + TextColor.charAt(2) + TextColor.charAt(3) + TextColor.charAt(4) + TextColor.charAt(5) + TextColor.charAt(6) + TextColor.charAt(7) + ";" +
                    "\n}";
            String DatePickerCss = "\n\n.DatePicker{" +
                    "\n-fx-font-family : " + FontFamily + ";" +
                    "\n-fx-text-fill : " + TextColor.charAt(2) + TextColor.charAt(3) + TextColor.charAt(4) + TextColor.charAt(5) + TextColor.charAt(6) + TextColor.charAt(7) + ";" +
                    "\n}";
            String SliderCss = "\n\n.Slider{" +
                    "\n-fx-font-family : " + FontFamily + ";" +
                    "\n-fx-text-fill : " + TextColor.charAt(2) + TextColor.charAt(3) + TextColor.charAt(4) + TextColor.charAt(5) + TextColor.charAt(6) + TextColor.charAt(7) + ";" +
                    "\n}";

            String userDesign = PaneCss + TopPaneCss + ButtonCss + ChoiceCss + TextFieldCss + LabelCss + DatePickerCss + SliderCss;
            cssfile.CreateFile(url, userDesign);
        }


        if(PaneColor == null || ButtonColor == null || TopPaneColor == null || TextColor == null || BorderColor == null)
        {
            String ButtonCss = "\n\n.Button{" +
                    "\n-fx-font-size : " + FontSize + ";" +
                    "\n-fx-font-family : " + FontFamily + ";" +
                    "\n}";
            String LabelCss = "\n\n.Label{" +
                    "\n-fx-font-size : " + FontSize + ";" +
                    "\n-fx-font-family : " + FontFamily + ";" +
                    "\n}";
        }


 */

        /*
        String TopPaneCss;

        String ButtonCss = ".Button{" +
                "-fx-background-color : " + ButtonColor.charAt(2) + ButtonColor.charAt(3) + ButtonColor.charAt(4) + ButtonColor.charAt(5) + ButtonColor.charAt(6) + ButtonColor.charAt(7) + ";" +
                "-fx-border-color : " + BorderColor.charAt(2) + BorderColor.charAt(3) + BorderColor.charAt(4) + BorderColor.charAt(5) + BorderColor.charAt(6) + BorderColor.charAt(7) + ";" +
                "-fx-font-size : " + FontSize + ";" +
                "-fx-text-fill : " + TextColor.charAt(2) + TextColor.charAt(3) + TextColor.charAt(4) + TextColor.charAt(5) + TextColor.charAt(6) + TextColor.charAt(7) + ";" +
                "-fx-font-family : " + FontFamily + ";" +
                "}";
        */