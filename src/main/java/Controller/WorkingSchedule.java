package Controller;

import DBH.therapistDAO;
import DBH.workScheduleDAO;
import Model.Therapist;
import Model.WorkSchedule;
import Util.FilesHandler;
import Util.JavafxPaneHandler;
import Util.TableExporter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WorkingSchedule implements Initializable, JavafxPaneHandler {

    ArrayList<Therapist> therapistArrayList = new ArrayList<Therapist>();
    DBH.therapistDAO tdao = new therapistDAO();

    DBH.workScheduleDAO wsDAO = new workScheduleDAO();

    //Day# --> Edit   --> ** DayList11
    //Day#1 --> Add --> DayList1
    //Arraylist- > PaneButtons (All panes) for clear

    @FXML
    private Pane parent;
    @FXML
    private ListView<String> ListViewSunday1;
    @FXML
    private ListView<String> ListViewSunday2;
    @FXML
    private ListView<String> ListViewSunday3;
    @FXML
    private ListView<String> ListViewMonday1;
    @FXML
    private ListView<String> ListViewMonday2;
    @FXML
    private ListView<String> ListViewMonday3;
    @FXML
    private ListView<String> ListViewTuesday1;
    @FXML
    private ListView<String> ListViewTuesday2;
    @FXML
    private ListView<String> ListViewTuesday3;
    @FXML
    private ListView<String> ListViewWedensday1;
    @FXML
    private ListView<String> ListViewWedensday2;
    @FXML
    private ListView<String> ListViewWedensday3;
    @FXML
    private ListView<String> ListViewThursday1;
    @FXML
    private ListView<String> ListViewThursday2;
    @FXML
    private ListView<String> ListViewThursday3;
    @FXML
    private ListView<String> ListViewFriday1;
    @FXML
    private ListView<String> ListViewFriday2;
    @FXML
    private ListView<String> ListViewFriday3;
    @FXML
    private ListView<String> ListViewSaturday1;
    @FXML
    private ListView<String> ListViewSaturday2;
    @FXML
    private ListView<String> ListViewSaturday3;
    @FXML
    private ChoiceBox<String> ChoiceAdd;
    @FXML
    private Pane Sunday11;
    @FXML
    private Pane Sunday21;
    @FXML
    private Pane Sunday31;
    @FXML
    private Pane Monday11;
    @FXML
    private Pane Monday21;
    @FXML
    private Pane Monday31;
    @FXML
    private Pane Tuesday11;
    @FXML
    private Pane Tuesday21;
    @FXML
    private Pane Tuesday31;
    @FXML
    private Pane Wedensday11;
    @FXML
    private Pane Wedensday21;
    @FXML
    private Pane Wedensday31;
    @FXML
    private Pane Thursday11;
    @FXML
    private Pane Thursday21;
    @FXML
    private Pane Thursday31;
    @FXML
    private Pane Friday11;
    @FXML
    private Pane Friday21;
    @FXML
    private Pane Friday31;
    @FXML
    private Pane Saturday11;
    @FXML
    private Pane Saturday21;
    @FXML
    private Pane Saturday31;
    @FXML
    private ChoiceBox<String> ChoiceEdit;
    @FXML
    private Pane Sunday1;
    @FXML
    private Pane Sunday2;
    @FXML
    private Pane Sunday3;
    @FXML
    private Pane Monday1;
    @FXML
    private Pane Monday2;
    @FXML
    private Pane Monday3;
    @FXML
    private Pane Tuesday1;
    @FXML
    private Pane Tuesday2;
    @FXML
    private Pane Tuesday3;
    @FXML
    private Pane Wedensday1;
    @FXML
    private Pane Wedensday2;
    @FXML
    private Pane Wedensday3;
    @FXML
    private Pane Thursday1;
    @FXML
    private Pane Thursday2;
    @FXML
    private Pane Thursday3;
    @FXML
    private Pane Friday1;
    @FXML
    private Pane Friday2;
    @FXML
    private Pane Friday3;
    @FXML
    private Pane Saturday1;
    @FXML
    private Pane Saturday2;
    @FXML
    private Pane Saturday3;
    @FXML
    private Button BtnBack;
    @FXML
    private Button ButtonDeleteSpPatient;
    @FXML
    private Button ButtonDeleteSpDay;
    @FXML
    private Button ButtonDeleteAll;
    @FXML
    private Button ButtonDeleteSpShift;
    @FXML
    private ChoiceBox<String> ChoiceDeleteSpPatient;
    @FXML
    private ChoiceBox<String> ChoiceDeleteSpDay;
    @FXML
    private ChoiceBox<String> ChoiceDeleteSpShift;
    @FXML
    private Button BtnExportFILE;
    @FXML
    private Button BtnExportPDF;

    String style = "-fx-background-color:  #a7a7a7";
    String clicked = "-fx-background-color:  #59b7ff";

    ArrayList<Pane> PaneButtons = new ArrayList<Pane>();
    ArrayList<Pane> SundayList1 = new ArrayList<Pane>();
    ArrayList<Pane> SundayList2 = new ArrayList<Pane>();
    ArrayList<Pane> MondayList1 = new ArrayList<Pane>();
    ArrayList<Pane> MondayList2 = new ArrayList<Pane>();
    ArrayList<Pane> TuesdayList1 = new ArrayList<Pane>();
    ArrayList<Pane> TuesdayList2 = new ArrayList<Pane>();
    ArrayList<Pane> WedenesdayList1 = new ArrayList<Pane>();
    ArrayList<Pane> WedenesdayList2 = new ArrayList<Pane>();
    ArrayList<Pane> ThursdayList1 = new ArrayList<Pane>();
    ArrayList<Pane> ThursdayList2 = new ArrayList<Pane>();
    ArrayList<Pane> FridayList1 = new ArrayList<Pane>();
    ArrayList<Pane> FridayList2 = new ArrayList<Pane>();
    ArrayList<Pane> SaturdayList1 = new ArrayList<Pane>();
    ArrayList<Pane> SaturdayList2 = new ArrayList<Pane>();

    ArrayList<WorkSchedule> personalWorkSchedules = new ArrayList<WorkSchedule>();
    ArrayList<WorkSchedule> workScheduleArrayList = new ArrayList<WorkSchedule>();

    @FXML
    void onClickBtnBack(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void onClickBtnAdd(ActionEvent event) throws SQLException {
        personalWorkSchedules.clear();

        boolean addFlag = true;

        for (Pane p : SundayList2)
            if (p.getStyle().equals(clicked))
                personalWorkSchedules.add(new WorkSchedule("Sunday", (SundayList2.indexOf(p) + 1)));
        for (Pane p : MondayList2)
            if (p.getStyle().equals(clicked))
                personalWorkSchedules.add(new WorkSchedule("Monday", (MondayList2.indexOf(p) + 1)));
        for (Pane p : TuesdayList2)
            if (p.getStyle().equals(clicked))
                personalWorkSchedules.add(new WorkSchedule("Tuesday", (TuesdayList2.indexOf(p) + 1)));
        for (Pane p : WedenesdayList2)
            if (p.getStyle().equals(clicked))
                personalWorkSchedules.add(new WorkSchedule("Wednesday", (WedenesdayList2.indexOf(p) + 1)));
        for (Pane p : ThursdayList2)
            if (p.getStyle().equals(clicked))
                personalWorkSchedules.add(new WorkSchedule("Thursday", (ThursdayList2.indexOf(p) + 1)));
        for (Pane p : FridayList2)
            if (p.getStyle().equals(clicked))
                personalWorkSchedules.add(new WorkSchedule("Friday", (FridayList2.indexOf(p) + 1)));
        for (Pane p : SaturdayList2)
            if (p.getStyle().equals(clicked))
                personalWorkSchedules.add(new WorkSchedule("Saturday", (SaturdayList2.indexOf(p) + 1)));
        workScheduleArrayList = null;

        for (Therapist t : therapistArrayList) {
            if (ChoiceAdd.getValue().equals(t.getName())) {
                workScheduleArrayList = wsDAO.SelectAllByID(t.getID());
                if (workScheduleArrayList.size() == 0) {
                    wsDAO.insertWorkSched(t.getID(), personalWorkSchedules);
                } else {
                    for (WorkSchedule ws : personalWorkSchedules) {
                        addFlag = true;
                        for (WorkSchedule wsal : workScheduleArrayList) {
                            if ((ws.getDay().equals(wsal.getDay())))
                                addFlag = false;
                        }
                        if (addFlag == true)
                            wsDAO.insertSpecificDayWorkSched(t.getID(), ws);
                    }
                }
            }
        }
        ListViewFill();
    }

    @FXML
    void onClickBtnClear(ActionEvent event) {
        for (Pane p : PaneButtons)
            p.setStyle(style);
    }

    @FXML
    void onClickBtnSave(ActionEvent event) throws SQLException {
        personalWorkSchedules.clear();

        for (Pane p : SundayList1)
            if (p.getStyle().equals(clicked))
                personalWorkSchedules.add(new WorkSchedule("Sunday", (SundayList1.indexOf(p) + 1)));
        for (Pane p : MondayList1)
            if (p.getStyle().equals(clicked))
                personalWorkSchedules.add(new WorkSchedule("Monday", (MondayList1.indexOf(p) + 1)));
        for (Pane p : TuesdayList1)
            if (p.getStyle().equals(clicked))
                personalWorkSchedules.add(new WorkSchedule("Tuesday", (TuesdayList1.indexOf(p) + 1)));
        for (Pane p : WedenesdayList1)
            if (p.getStyle().equals(clicked))
                personalWorkSchedules.add(new WorkSchedule("Wednesday", (WedenesdayList1.indexOf(p) + 1)));
        for (Pane p : ThursdayList1)
            if (p.getStyle().equals(clicked))
                personalWorkSchedules.add(new WorkSchedule("Thursday", (ThursdayList1.indexOf(p) + 1)));
        for (Pane p : FridayList1)
            if (p.getStyle().equals(clicked))
                personalWorkSchedules.add(new WorkSchedule("Friday", (FridayList1.indexOf(p) + 1)));
        for (Pane p : SaturdayList1)
            if (p.getStyle().equals(clicked))
                personalWorkSchedules.add(new WorkSchedule("Saturday", (SaturdayList1.indexOf(p) + 1)));

        workScheduleArrayList = null;

        for (Therapist t : therapistArrayList) {
            if (ChoiceEdit.getValue().equals(t.getName())) {
                workScheduleArrayList = wsDAO.SelectAllByID(t.getID());
                for (WorkSchedule ws : personalWorkSchedules)
                    wsDAO.updateWorkSched(t.getID(), ws);
            }
        }

        ListViewFill();
    }

    @FXML
    void onClickDeleteSpPatient(ActionEvent event) throws SQLException {
        for (Therapist t : therapistArrayList) {
            if (ChoiceDeleteSpPatient.getValue().equals(t.getName()))
                wsDAO.removeWorkSceduleByTherapist(t);
        }
        ListViewFill();
    }

    @FXML
    void onClickDeleteSpDay(ActionEvent event) throws SQLException {
        String day = ChoiceDeleteSpDay.getValue();
        wsDAO.removeWorkSceduleByDay(day);
        ListViewFill();
    }

    @FXML
    void onClickDeleteSpShift(ActionEvent event) throws SQLException {
        String shift = ChoiceDeleteSpShift.getValue();
        String[] splited = shift.split(" ");
        wsDAO.removeWorkSceduleByShift(splited[0], Integer.parseInt(splited[1]));
        ListViewFill();
    }

    @FXML
    void onClickDeleteAll(ActionEvent event) throws SQLException {
        wsDAO.removeAll();
        ListViewFill();
    }

    @FXML
    void onClickExportToFILE(ActionEvent event) throws IOException, SQLException {
        Util.FilesHandler fh = new FilesHandler();
        fh.SaveWorkSchedule();
    }

    @FXML
    void onClickExportToPDF(ActionEvent event) throws FileNotFoundException, SQLException, MalformedURLException {
        TableExporter tableExporter = new TableExporter();
        tableExporter.TableExport();
    }

    //Edit Tab   --->   Morning shift
    @FXML
    void onClickSunday1(MouseEvent event) {
        Sunday1.setStyle(clicked);
        for (Pane p : SundayList1)
            if (!p.equals(Sunday1))
                p.setStyle(style);
    }

    //Add Tab   --->   Morning shift
    @FXML
    void onClickSunday11(MouseEvent event) {
        Sunday11.setStyle(clicked);
        for (Pane p : SundayList2)
            if (!p.equals(Sunday11))
                p.setStyle(style);
    }

    //Edit Tab   --->   Evening shift
    @FXML
    void onClickSunday2(MouseEvent event) {
        Sunday2.setStyle(clicked);
        for (Pane p : SundayList1)
            if (!p.equals(Sunday2))
                p.setStyle(style);
    }

    //Add Tab   --->   Evening shift
    @FXML
    void onClickSunday21(MouseEvent event) {
        Sunday21.setStyle(clicked);
        for (Pane p : SundayList2)
            if (!p.equals(Sunday21))
                p.setStyle(style);
    }

    //Edit Tab   --->   Night shift
    @FXML
    void onClickSunday3(MouseEvent event) {
        Sunday3.setStyle(clicked);
        for (Pane p : SundayList1)
            if (!p.equals(Sunday3))
                p.setStyle(style);
    }

    //Add Tab   --->   Night shift
    @FXML
    void onClickSunday31(MouseEvent event) {
        Sunday31.setStyle(clicked);
        for (Pane p : SundayList2)
            if (!p.equals(Sunday31))
                p.setStyle(style);
    }

    //Edit Tab   --->   Morning shift
    @FXML
    void onClickMonday1(MouseEvent event) {
        Monday1.setStyle(clicked);
        for (Pane p : MondayList1)
            if (!p.equals(Monday1))
                p.setStyle(style);
    }

    //Add Tab   --->   Morning shift
    @FXML
    void onClickMonday11(MouseEvent event) {
        Monday11.setStyle(clicked);
        for (Pane p : MondayList2)
            if (!p.equals(Monday11))
                p.setStyle(style);
    }

    //Edit Tab   --->   Evening shift
    @FXML
    void onClickMonday2(MouseEvent event) {
        Monday2.setStyle(clicked);
        for (Pane p : MondayList1)
            if (!p.equals(Monday2))
                p.setStyle(style);
    }

    //Add Tab   --->   Evening shift
    @FXML
    void onClickMonday21(MouseEvent event) {
        Monday21.setStyle(clicked);
        for (Pane p : MondayList2)
            if (!p.equals(Monday21))
                p.setStyle(style);
    }

    //Edit Tab   --->   Night shift
    @FXML
    void onClickMonday3(MouseEvent event) {
        Monday3.setStyle(clicked);
        for (Pane p : MondayList1)
            if (!p.equals(Monday3))
                p.setStyle(style);
    }

    //Add Tab   --->   Night shift
    @FXML
    void onClickMonday31(MouseEvent event) {
        Monday31.setStyle(clicked);
        for (Pane p : MondayList2)
            if (!p.equals(Monday31))
                p.setStyle(style);
    }

    //Edit Tab   --->   Morning shift
    @FXML
    void onClickTuesday1(MouseEvent event) {
        Tuesday1.setStyle(clicked);
        for (Pane p : TuesdayList1)
            if (!p.equals(Tuesday1))
                p.setStyle(style);
    }

    //Add Tab   --->   Morning shift
    @FXML
    void onClickTuesday11(MouseEvent event) {
        Tuesday11.setStyle(clicked);
        for (Pane p : TuesdayList2)
            if (!p.equals(Tuesday11))
                p.setStyle(style);
    }

    //Edit Tab   --->   Evening shift
    @FXML
    void onClickTuesday2(MouseEvent event) {
        Tuesday2.setStyle(clicked);
        for (Pane p : TuesdayList1)
            if (!p.equals(Tuesday2))
                p.setStyle(style);
    }

    //Add Tab   --->   Evening shift
    @FXML
    void onClickTuesday21(MouseEvent event) {
        Tuesday21.setStyle(clicked);
        for (Pane p : TuesdayList2)
            if (!p.equals(Tuesday21))
                p.setStyle(style);
    }

    //Edit Tab   --->   Night shift
    @FXML
    void onClickTuesday3(MouseEvent event) {
        Tuesday3.setStyle(clicked);
        for (Pane p : TuesdayList1)
            if (!p.equals(Tuesday3))
                p.setStyle(style);
    }

    //Add Tab   --->   Night shift
    @FXML
    void onClickTuesday31(MouseEvent event) {
        Tuesday31.setStyle(clicked);
        for (Pane p : TuesdayList2)
            if (!p.equals(Tuesday31))
                p.setStyle(style);
    }

    //Edit Tab   --->   Morning shift
    @FXML
    void onClickWedensday1(MouseEvent event) {
        Wedensday1.setStyle(clicked);
        for (Pane p : WedenesdayList1)
            if (!p.equals(Wedensday1))
                p.setStyle(style);
    }

    //Add Tab   --->   Morning shift
    @FXML
    void onClickWedensday11(MouseEvent event) {
        Wedensday11.setStyle(clicked);
        for (Pane p : WedenesdayList2)
            if (!p.equals(Wedensday11))
                p.setStyle(style);
    }

    //Edit Tab   --->   Evening shift
    @FXML
    void onClickWedensday2(MouseEvent event) {
        Wedensday2.setStyle(clicked);
        for (Pane p : WedenesdayList1)
            if (!p.equals(Wedensday2))
                p.setStyle(style);
    }

    //Add Tab   --->   Evening shift
    @FXML
    void onClickWedensday21(MouseEvent event) {
        Wedensday21.setStyle(clicked);
        for (Pane p : WedenesdayList2)
            if (!p.equals(Wedensday21))
                p.setStyle(style);
    }

    //Edit Tab   --->   Night shift
    @FXML
    void onClickWedensday3(MouseEvent event) {
        Wedensday3.setStyle(clicked);
        for (Pane p : WedenesdayList1)
            if (!p.equals(Wedensday3))
                p.setStyle(style);
    }

    //Add Tab   --->   Night shift
    @FXML
    void onClickWedensday31(MouseEvent event) {
        Wedensday31.setStyle(clicked);
        for (Pane p : WedenesdayList2)
            if (!p.equals(Wedensday31))
                p.setStyle(style);
    }

    //Edit Tab   --->   Morning shift
    @FXML
    void onClickThursday1(MouseEvent event) {
        Thursday1.setStyle(clicked);
        for (Pane p : ThursdayList1)
            if (!p.equals(Thursday1))
                p.setStyle(style);
    }

    //Add Tab   --->   Morning shift
    @FXML
    void onClickThursday11(MouseEvent event) {
        Thursday11.setStyle(clicked);
        for (Pane p : ThursdayList2)
            if (!p.equals(Thursday11))
                p.setStyle(style);
    }

    //Edit Tab   --->   Evening shift
    @FXML
    void onClickThursday2(MouseEvent event) {
        Thursday2.setStyle(clicked);
        for (Pane p : ThursdayList1)
            if (!p.equals(Thursday2))
                p.setStyle(style);
    }

    //Add Tab   --->   Evening shift
    @FXML
    void onClickThursday21(MouseEvent event) {
        Thursday21.setStyle(clicked);
        for (Pane p : ThursdayList2)
            if (!p.equals(Thursday21))
                p.setStyle(style);
    }

    //Edit Tab   --->   Night shift
    @FXML
    void onClickThursday3(MouseEvent event) {
        Thursday3.setStyle(clicked);
        for (Pane p : ThursdayList1)
            if (!p.equals(Thursday3))
                p.setStyle(style);
    }

    //Add Tab   --->   Night shift
    @FXML
    void onClickThursday31(MouseEvent event) {
        Thursday31.setStyle(clicked);
        for (Pane p : ThursdayList2)
            if (!p.equals(Thursday31))
                p.setStyle(style);
    }

    //Edit Tab   --->   Morning shift
    @FXML
    void onClickFriday1(MouseEvent event) {
        Friday1.setStyle(clicked);
        for (Pane p : FridayList1)
            if (!p.equals(Friday1))
                p.setStyle(style);
    }

    //Add Tab   --->   Morning shift
    @FXML
    void onClickFriday11(MouseEvent event) {
        Friday11.setStyle(clicked);
        for (Pane p : FridayList2)
            if (!p.equals(Friday11))
                p.setStyle(style);
    }

    //Edit Tab   --->   Evening shift
    @FXML
    void onClickFriday2(MouseEvent event) {
        Friday2.setStyle(clicked);
        for (Pane p : FridayList1)
            if (!p.equals(Friday2))
                p.setStyle(style);
    }

    //Add Tab   --->   Evening shift
    @FXML
    void onClickFriday21(MouseEvent event) {
        Friday21.setStyle(clicked);
        for (Pane p : FridayList2)
            if (!p.equals(Friday21))
                p.setStyle(style);
    }

    //Edit Tab   --->   Night shift
    @FXML
    void onClickFriday3(MouseEvent event) {
        Friday3.setStyle(clicked);
        for (Pane p : FridayList1)
            if (!p.equals(Friday3))
                p.setStyle(style);
    }

    //Add Tab   --->   Night shift
    @FXML
    void onClickFriday31(MouseEvent event) {
        Friday31.setStyle(clicked);
        for (Pane p : FridayList2)
            if (!p.equals(Friday31))
                p.setStyle(style);
    }

    //Edit Tab   --->   Morning shift
    @FXML
    void onClickSaturday1(MouseEvent event) {
        Saturday1.setStyle(clicked);
        for (Pane p : SaturdayList1)
            if (!p.equals(Saturday1))
                p.setStyle(style);
    }

    //Add Tab   --->   Morning shift
    @FXML
    void onClickSaturday11(MouseEvent event) {
        Saturday11.setStyle(clicked);
        for (Pane p : SaturdayList2)
            if (!p.equals(Saturday11))
                p.setStyle(style);
    }

    //Edit Tab   --->   Evening shift
    @FXML
    void onClickSaturday2(MouseEvent event) {
        Saturday2.setStyle(clicked);
        for (Pane p : SaturdayList1)
            if (!p.equals(Saturday2))
                p.setStyle(style);
    }

    //Add Tab   --->   Evening shift
    @FXML
    void onClickSaturday21(MouseEvent event) {
        Saturday21.setStyle(clicked);
        for (Pane p : SaturdayList2)
            if (!p.equals(Saturday21))
                p.setStyle(style);
    }

    //Edit Tab   --->   Night shift
    @FXML
    void onClickSaturday3(MouseEvent event) {
        Saturday3.setStyle(clicked);
        for (Pane p : SaturdayList1)
            if (!p.equals(Saturday3))
                p.setStyle(style);
    }

    //Add Tab   --->   Night shift
    @FXML
    void onClickSaturday31(MouseEvent event) {
        Saturday31.setStyle(clicked);
        for (Pane p : SaturdayList2)
            if (!p.equals(Saturday31))
                p.setStyle(style);
    }

    public void ListViewFill() throws SQLException {
        ListViewSunday1.getItems().clear();
        ListViewSunday2.getItems().clear();
        ListViewSunday3.getItems().clear();
        ListViewMonday1.getItems().clear();
        ListViewMonday2.getItems().clear();
        ListViewMonday3.getItems().clear();
        ListViewTuesday1.getItems().clear();
        ListViewTuesday2.getItems().clear();
        ListViewTuesday3.getItems().clear();
        ListViewWedensday1.getItems().clear();
        ListViewWedensday2.getItems().clear();
        ListViewWedensday3.getItems().clear();
        ListViewThursday1.getItems().clear();
        ListViewThursday2.getItems().clear();
        ListViewThursday3.getItems().clear();
        ListViewFriday1.getItems().clear();
        ListViewFriday2.getItems().clear();
        ListViewFriday3.getItems().clear();
        ListViewSaturday1.getItems().clear();
        ListViewSaturday2.getItems().clear();
        ListViewSaturday3.getItems().clear();

        for (Therapist t : therapistArrayList) {
            workScheduleArrayList = null;
            workScheduleArrayList = wsDAO.SelectAllByID(t.getID());
            for (WorkSchedule ws : workScheduleArrayList) {
                if (ws.getDay().equals("Sunday") && ws.getShift() == 1)
                    ListViewSunday1.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Sunday") && ws.getShift() == 2)
                    ListViewSunday2.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Sunday") && ws.getShift() == 3)
                    ListViewSunday3.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Monday") && ws.getShift() == 1)
                    ListViewMonday1.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Monday") && ws.getShift() == 2)
                    ListViewMonday2.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Monday") && ws.getShift() == 3)
                    ListViewMonday3.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Tuesday") && ws.getShift() == 1)
                    ListViewTuesday1.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Tuesday") && ws.getShift() == 2)
                    ListViewTuesday2.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Tuesday") && ws.getShift() == 3)
                    ListViewTuesday3.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Wednesday") && ws.getShift() == 1)
                    ListViewWedensday1.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Wednesday") && ws.getShift() == 2)
                    ListViewWedensday2.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Wednesday") && ws.getShift() == 3)
                    ListViewWedensday3.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Thursday") && ws.getShift() == 1)
                    ListViewThursday1.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Thursday") && ws.getShift() == 2)
                    ListViewThursday2.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Thursday") && ws.getShift() == 3)
                    ListViewThursday3.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Friday") && ws.getShift() == 1)
                    ListViewFriday1.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Friday") && ws.getShift() == 2)
                    ListViewFriday2.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Friday") && ws.getShift() == 3)
                    ListViewFriday3.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Saturday") && ws.getShift() == 1)
                    ListViewSaturday1.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Saturday") && ws.getShift() == 2)
                    ListViewSaturday2.getItems().add(t.getID() + "--->" + t.getName());
                if (ws.getDay().equals("Saturday") && ws.getShift() == 3)
                    ListViewSaturday3.getItems().add(t.getID() + "--->" + t.getName());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            JavafxChoiceFill();
            ListViewFill();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //--------------------------------
        SundayList1.add(Sunday1);
        SundayList1.add(Sunday2);
        SundayList1.add(Sunday3);

        SundayList2.add(Sunday11);
        SundayList2.add(Sunday21);
        SundayList2.add(Sunday31);
        //--------------------------------
        MondayList1.add(Monday1);
        MondayList1.add(Monday2);
        MondayList1.add(Monday3);

        MondayList2.add(Monday11);
        MondayList2.add(Monday21);
        MondayList2.add(Monday31);
        //--------------------------------
        TuesdayList1.add(Tuesday1);
        TuesdayList1.add(Tuesday2);
        TuesdayList1.add(Tuesday3);

        TuesdayList2.add(Tuesday11);
        TuesdayList2.add(Tuesday21);
        TuesdayList2.add(Tuesday31);
        //-------------------------
        WedenesdayList1.add(Wedensday1);
        WedenesdayList1.add(Wedensday2);
        WedenesdayList1.add(Wedensday3);

        WedenesdayList2.add(Wedensday11);
        WedenesdayList2.add(Wedensday21);
        WedenesdayList2.add(Wedensday31);
        //-------------------------
        ThursdayList1.add(Thursday1);
        ThursdayList1.add(Thursday2);
        ThursdayList1.add(Thursday3);

        ThursdayList2.add(Thursday11);
        ThursdayList2.add(Thursday21);
        ThursdayList2.add(Thursday31);
        //-------------------------
        FridayList1.add(Friday1);
        FridayList1.add(Friday2);
        FridayList1.add(Friday3);

        FridayList2.add(Friday11);
        FridayList2.add(Friday21);
        FridayList2.add(Friday31);
        //-------------------------
        SaturdayList1.add(Saturday1);
        SaturdayList1.add(Saturday2);
        SaturdayList1.add(Saturday3);

        SaturdayList2.add(Saturday11);
        SaturdayList2.add(Saturday21);
        SaturdayList2.add(Saturday31);
        //-------------------------
        PaneButtons.add(Tuesday11);
        PaneButtons.add(Tuesday21);
        PaneButtons.add(Tuesday31);

        //--------------------------------
        PaneButtons.add(Sunday1);
        PaneButtons.add(Sunday11);
        PaneButtons.add(Sunday2);
        PaneButtons.add(Sunday21);
        PaneButtons.add(Sunday3);
        PaneButtons.add(Sunday31);

        PaneButtons.add(Monday1);
        PaneButtons.add(Monday11);
        PaneButtons.add(Monday2);
        PaneButtons.add(Monday21);
        PaneButtons.add(Monday3);
        PaneButtons.add(Monday31);

        PaneButtons.add(Tuesday1);
        PaneButtons.add(Tuesday11);
        PaneButtons.add(Tuesday2);
        PaneButtons.add(Tuesday21);
        PaneButtons.add(Tuesday3);
        PaneButtons.add(Tuesday31);

        PaneButtons.add(Wedensday1);
        PaneButtons.add(Wedensday11);
        PaneButtons.add(Wedensday2);
        PaneButtons.add(Wedensday21);
        PaneButtons.add(Wedensday3);
        PaneButtons.add(Wedensday31);

        PaneButtons.add(Thursday1);
        PaneButtons.add(Thursday11);
        PaneButtons.add(Thursday2);
        PaneButtons.add(Thursday21);
        PaneButtons.add(Thursday3);
        PaneButtons.add(Thursday31);

        PaneButtons.add(Friday1);
        PaneButtons.add(Friday11);
        PaneButtons.add(Friday2);
        PaneButtons.add(Friday21);
        PaneButtons.add(Friday3);
        PaneButtons.add(Friday31);

        PaneButtons.add(Saturday1);
        PaneButtons.add(Saturday11);
        PaneButtons.add(Saturday2);
        PaneButtons.add(Saturday21);
        PaneButtons.add(Saturday3);
        PaneButtons.add(Saturday31);
        //-----------------------------
        for (Pane p : PaneButtons)
            p.setStyle(style);


    }

    @Override
    public void JavafxTableFill() throws SQLException {

    }

    @Override
    public void JavafxChoiceFill() throws SQLException {
        therapistArrayList = tdao.selectAll();
        ChoiceAdd.getItems().clear();
        ChoiceEdit.getItems().clear();
        ChoiceDeleteSpDay.getItems().clear();
        ChoiceDeleteSpPatient.getItems().clear();
        ChoiceDeleteSpShift.getItems().clear();

        for (Therapist t : therapistArrayList) {
            ChoiceAdd.getItems().add(t.getName());
            ChoiceEdit.getItems().add(t.getName());
            ChoiceDeleteSpPatient.getItems().add(t.getName());
        }

        ChoiceDeleteSpDay.getItems().addAll("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturdayy");
        ChoiceDeleteSpShift.getItems().addAll("Sunday 1", "Sunday 2", "Sunday 3", "Monday 1", "Monday 2", "Monday 3", "Tuesday 1", "Tuesday 2", "Tuesday 3", "Wednesday 1", "Wednesday 2", "Wednesday 3", "Thursday 1", "Thursday 2", "Thursday 3", "Friday 1", "Friday 2", "Friday 3", "Saturday 1", "Saturday 2", "Saturday 3");
    }

    @Override
    public void JavafxDiagramFill() throws IOException {

    }
}