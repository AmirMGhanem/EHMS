package Controller;

import DBH.*;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MainPaneController implements Initializable, Util.JavafxPaneHandler {


    //DATABASE HANDLERER DECLERATIONS
    DBH.therapistDAO tDAO = new therapistDAO();
    DBH.patientDAO pDAO = new patientDAO();
    DBH.notificationDAO nDAO = new notificationDAO();
    DBH.medicineDAO mDAO = new medicineDAO();
    DBH.AllergyDAO aDAO = new AllergyDAO();
    //ARRAYLISTS DECLERATIONS
    private ArrayList<Patient> patientArrayList = new ArrayList<Patient>();
    private ArrayList<Therapist> therapistArrayList = new ArrayList<Therapist>();
    private ArrayList<Medicine> medicineArrayList = new ArrayList<Medicine>();
    private ArrayList<Allergy> allergyArrayList = new ArrayList<Allergy>();
    private ArrayList<Notification> notificationArrayList = new ArrayList<Notification>();
    //OBSERVABLE DECLERATIONS
    ObservableList notificationObservable = FXCollections.observableArrayList();

    //pieChart.data decleration
    PieChart.Data highdata;
    PieChart.Data mediumdata;
    PieChart.Data lowdata;

    PieChart.Data waterdata;
    PieChart.Data mealdata;
    PieChart.Data toiletdata;
    PieChart.Data emergencydata;


    @FXML
    private TableView<Notification> TableViewNotifications;
    @FXML
    private TableColumn<Notification, Number> ColNum;

    @FXML
    private TableColumn<Notification, String> ColType;

    @FXML
    private TableColumn<Notification, String> ColDesc;

    @FXML
    private TableColumn<Notification, String> ColPatientName;

    @FXML
    private TableColumn<Notification, String> ColPatientID;

    @FXML
    private TableColumn<Notification, Date> ColTime;
    @FXML
    private PieChart PieChartRequests;

    @FXML
    private PieChart PieChartActive;

    @FXML
    private Button BtnDay;

    @FXML
    private Button BrnMonthly;

    @FXML
    private Button BtnQuarterly;
    @FXML
    private Label LabelActiveTherapist;


    @FXML
    private Label LabelActivePatient;


    @FXML
    private BarChart<?, ?> BarChartNotifications;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    void OnClickDay(ActionEvent event) {
        CategoryAxis xAxis = new CategoryAxis();
        //xAxis.setCategories(FXCollections.<String>observableArrayList(month));
        NumberAxis yAxis = new NumberAxis("Units", 0.0d, 3000.0d, 1000.0d);
        ObservableList<BarChart.Series> barChartData = FXCollections.observableArrayList(
                new BarChart.Series("Medicines", FXCollections.observableArrayList(

                        new BarChart.Data("Today", 3d)
                )),
                new BarChart.Series("Allergies", FXCollections.observableArrayList(
                        new BarChart.Data("Today", 2d)
                )),
                new BarChart.Series("Meetings", FXCollections.observableArrayList(
                        new BarChart.Data("Today", 1d)
                ))
        );
        BarChart chart = new BarChart(xAxis, yAxis, barChartData, 25.0d);
        BarChartNotifications.getData().clear();
        BarChartNotifications.getData().addAll(chart.getData());

    }

    @FXML
    void OnClickMonthly(ActionEvent event) {
        String[] month = {"1", "2", "3", "4", "5",
                "7", "8", "9", "10", "11",
                "12", "13", "14", "15", "16",
                "17", "18", "19", "20", "21",
                "22", "23", "24", "25", "26",
                "27", "28", "29", "30", "31"};
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(month));
        NumberAxis yAxis = new NumberAxis("Units", 0.0d, 3000.0d, 1000.0d);
        ObservableList<BarChart.Series> barChartData = FXCollections.observableArrayList(
                new BarChart.Series("Medicines", FXCollections.observableArrayList(

                        new BarChart.Data(month[1], 3d),
                        new BarChart.Data(month[4], 2d),
                        new BarChart.Data(month[5], 1d),
                        new BarChart.Data(month[8], 4d),
                        new BarChart.Data(month[10], 1d),
                        new BarChart.Data(month[18], 0d),
                        new BarChart.Data(month[20], 0d),
                        new BarChart.Data(month[28], 2d)


                )),
                new BarChart.Series("Allergies", FXCollections.observableArrayList(
                        new BarChart.Data(month[3], 1d),
                        new BarChart.Data(month[7], 2d),
                        new BarChart.Data(month[9], 4d),
                        new BarChart.Data(month[14], 3d),
                        new BarChart.Data(month[19], 1d),
                        new BarChart.Data(month[21], 1d),
                        new BarChart.Data(month[24], 2d),
                        new BarChart.Data(month[27], 5d)
                )),
                new BarChart.Series("Meetings", FXCollections.observableArrayList(
                        new BarChart.Data(month[2], 7d),
                        new BarChart.Data(month[3], 4d),
                        new BarChart.Data(month[2], 1d),
                        new BarChart.Data(month[7], 3d),
                        new BarChart.Data(month[12], 6d),
                        new BarChart.Data(month[16], 2d),
                        new BarChart.Data(month[22], 3d),
                        new BarChart.Data(month[21], 4d)
                ))
        );
        BarChart chart = new BarChart(xAxis, yAxis, barChartData, 25.0d);
        BarChartNotifications.getData().clear();
        BarChartNotifications.getData().addAll(chart.getData());
    }

    @FXML
    void OnClickQuarterly(ActionEvent event) {
        String[] month = {"January", "Current", "Last-Month"};
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(month));
        NumberAxis yAxis = new NumberAxis("Units", 0.0d, 3000.0d, 1000.0d);
        ObservableList<BarChart.Series> barChartData = FXCollections.observableArrayList(
                new BarChart.Series("Medicines", FXCollections.observableArrayList(

                        new BarChart.Data(month[0], 5),
                        new BarChart.Data(month[1], 0d),
                        new BarChart.Data(month[2], 3d)


                )),
                new BarChart.Series("Allergies", FXCollections.observableArrayList(
                        new BarChart.Data(month[0], 2d),
                        new BarChart.Data(month[1], 1d),
                        new BarChart.Data(month[2], 0d)


                )),
                new BarChart.Series("Meetings", FXCollections.observableArrayList(
                        new BarChart.Data(month[0], 1d),
                        new BarChart.Data(month[1], 1d),
                        new BarChart.Data(month[2], 2d)


                ))
        );
        BarChart chart = new BarChart(xAxis, yAxis, barChartData, 25.0d);
        BarChartNotifications.getData().clear();
        BarChartNotifications.getData().addAll(chart.getData());
    }

    @Override
    public void JavafxTableFill() throws SQLException {

    }

    @Override
    public void JavafxChoiceFill() {

    }


    @Override
    public void JavafxDiagramFill() throws IOException {
        highdata = new PieChart.Data("High Urgency", 1);
        mediumdata = new PieChart.Data("Medium Urgency", 2);
        lowdata = new PieChart.Data("Low Urgency", 3);
        waterdata = new PieChart.Data("Water", 1);
        mealdata = new PieChart.Data("Meal", 2);
        toiletdata = new PieChart.Data("Toilet", 3);
        emergencydata = new PieChart.Data("Emergency", 4);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(highdata, mediumdata, lowdata);
        ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(waterdata, mealdata, toiletdata, emergencydata);
        PieChartRequests.setData(pieChartData);
        PieChartRequests.setTitle("Urgency Level");
        PieChartRequests.setLegendVisible(true);
        PieChartRequests.setLabelsVisible(false);
        PieChartRequests.setLegendSide(Side.RIGHT);
        PieChartActive.setData(pieChartData2);
        PieChartActive.setTitle("Requests Types");
        PieChartActive.setLegendVisible(true);
        PieChartActive.setLabelsVisible(false);
        PieChartActive.setLegendSide(Side.RIGHT);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            LabelActiveTherapist.setText(Integer.toString(tDAO.getCount()));
            LabelActivePatient.setText(Integer.toString(pDAO.getCount()));
            JavafxDiagramFill();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

}
