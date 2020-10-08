package Util;

import DBH.*;
import Model.*;
import org.hibernate.jdbc.Work;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilesHandler {

    static ArrayList<Meeting> meetingArrayList = new ArrayList<Meeting>();
    static ArrayList<Medicine> medicineArrayList = new ArrayList<Medicine>();
    static ArrayList<Allergy> allergyArrayList = new ArrayList<Allergy>();
    static ArrayList<Meal> mealArrayList = new ArrayList<Meal>();


    static ArrayList<patient_meeting> patient_meetingArrayList = new ArrayList<patient_meeting>();
    static ArrayList<patient_medicine> patient_medicineArrayList = new ArrayList<patient_medicine>();
    static ArrayList<patient_allergy> patient_allergyArrayList = new ArrayList<patient_allergy>();
    static ArrayList<patient_meal> patient_mealArrayList = new ArrayList<patient_meal>();


    static DBH.meetingDAO meetingDAO = new meetingDAO();
    static DBH.medicineDAO medicineDAO = new medicineDAO();
    static DBH.AllergyDAO allergyDAO = new AllergyDAO();
    static DBH.mealDAO mealDAO = new mealDAO();

    static DBH.patient_meetingDAO patient_meetingDAO = new patient_meetingDAO();
    static DBH.patient_medicineDAO patient_medicineDAO = new patient_medicineDAO();
    static DBH.patient_allergyDAO patient_allergyDAO = new patient_allergyDAO();
    static DBH.patient_mealDAO patient_mealDAO = new patient_mealDAO();

    public FilesHandler() throws SQLException {
        medicineArrayList = medicineDAO.selectAll();
        meetingArrayList = meetingDAO.selectAll();
        mealArrayList = mealDAO.selectAll();
        allergyArrayList = allergyDAO.selectAll();

        patient_medicineArrayList = patient_medicineDAO.selectAll();
        patient_meetingArrayList = patient_meetingDAO.selectAll();
        patient_mealArrayList = patient_mealDAO.selectAll();
        patient_allergyArrayList = patient_allergyDAO.selectAll();

    }

    public static void SaveNurse() throws IOException, IOException {
        FileWriter fr = null;
        DBH.therapistDAO tdb = new therapistDAO();
        DBH.workScheduleDAO wsdao = new workScheduleDAO();
        ArrayList<WorkSchedule> workScheduleArrayList = new ArrayList<WorkSchedule>();
        ArrayList<Therapist> ALTHERAPIST = new ArrayList<Therapist>();
        try {
            fr = new FileWriter("src/main/resources/Files/TherapistFiles/Therapists.txt");
            fr.write("ID           Name           Address           Gender           Date           ContactNo");
            fr.write("\n --------------------------------------------------------------------------------------");
            ALTHERAPIST = tdb.selectAll();
            workScheduleArrayList = wsdao.SelectAllWS();

            System.out.println(workScheduleArrayList);
            for (Therapist t : ALTHERAPIST) {
                fr.write("\n" + t.getID() + "     " + t.getName() + "     " + t.getAddress().getCity() + " , " + t.getAddress().getStreet() + " , " + t.getAddress().getHouseNum() + "     " + t.getGender() + "     " + t.getDate() + "     " + t.getContactNo());
            }
            fr.write("\n --------------------------------------------------------------------------------------\n");
            fr.write("\n\n\n\n\n\n                                       WorkingSchedule\n\n ");

            fr.write("                                         Sunday");
            fr.write("\n 1 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Sunday") && ws.getShift() == 1)
                    fr.write(ws.getTherapist().getName() + ",  ");
            fr.write("\n 2 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Sunday") && ws.getShift() == 2)
                    fr.write(ws.getTherapist().getName() + ",  ");
            fr.write("\n 3 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Sunday") && ws.getShift() == 3)
                    fr.write(ws.getTherapist().getName() + ",  ");

            fr.write("\n --------------------------------------------------------------------------------------");
            fr.write(" \n                                        Monday");
            fr.write("\n 1 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Monday") && ws.getShift() == 1)
                    fr.write(ws.getTherapist().getName() + ",  ");
            fr.write("\n 2 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Monday") && ws.getShift() == 2)
                    fr.write(ws.getTherapist().getName() + ",  ");
            fr.write("\n 3 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Monday") && ws.getShift() == 3)
                    fr.write(ws.getTherapist().getName() + ",  ");

            fr.write("\n --------------------------------------------------------------------------------------");
            fr.write("  \n                                       Tuesday");
            fr.write("\n 1 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Tuesday") && ws.getShift() == 1)
                    fr.write(ws.getTherapist().getName() + ",  ");
            fr.write("\n 2 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Tuesday") && ws.getShift() == 2)
                    fr.write(ws.getTherapist().getName() + ",  ");
            fr.write("\n 3 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Tuesday") && ws.getShift() == 3)
                    fr.write(ws.getTherapist().getName() + ",  ");

            fr.write("\n --------------------------------------------------------------------------------------");
            fr.write(" \n                                        Wednesday");
            fr.write("\n 1 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Wednesday") && ws.getShift() == 1)
                    fr.write(ws.getTherapist().getName() + ",  ");
            fr.write("\n 2 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Wednesday") && ws.getShift() == 2)
                    fr.write(ws.getTherapist().getName() + ",  ");
            fr.write("\n 3 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Wednesday") && ws.getShift() == 3)
                    fr.write(ws.getTherapist().getName() + ",  ");

            fr.write("\n --------------------------------------------------------------------------------------");
            fr.write(" \n                                        Thursday");
            fr.write("\n 1 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Thursday") && ws.getShift() == 1)
                    fr.write(ws.getTherapist().getName() + ",  ");
            fr.write("\n 2 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Thursday") && ws.getShift() == 2)
                    fr.write(ws.getTherapist().getName() + ",  ");
            fr.write("\n 3 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Thursday") && ws.getShift() == 3)
                    fr.write(ws.getTherapist().getName() + ",  ");

            fr.write("\n --------------------------------------------------------------------------------------");
            fr.write(" \n                                        Friday");
            fr.write("\n 1 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Friday") && ws.getShift() == 1)
                    fr.write(ws.getTherapist().getName() + ",  ");
            fr.write("\n 2 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Friday") && ws.getShift() == 2)
                    fr.write(ws.getTherapist().getName() + ",  ");
            fr.write("\n 3 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Friday") && ws.getShift() == 3)
                    fr.write(ws.getTherapist().getName() + ",  ");

            fr.write("\n --------------------------------------------------------------------------------------");
            fr.write("\n                                         Saturday");
            fr.write("\n 1 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Saturday") && ws.getShift() == 1)
                    fr.write(ws.getTherapist().getName() + ",  ");
            fr.write("\n 2 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Saturday") && ws.getShift() == 2)
                    fr.write(ws.getTherapist().getName() + ",  ");
            fr.write("\n 3 shift -->");
            for (WorkSchedule ws : workScheduleArrayList)
                if (ws.getDay().equals("Saturday") && ws.getShift() == 3)
                    fr.write(ws.getTherapist().getName() + ",  ");

            fr.write("\n --------------------------------------------------------------------------------------");


            fr.close();
            System.out.println("Test6 Applied");
        } catch (IOException | SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void SaveSpecificNurse(Therapist t) {
        FileWriter fr = null;
        DBH.workScheduleDAO wsdao = new workScheduleDAO();
        ArrayList<WorkSchedule> workScheduleArrayList = new ArrayList<WorkSchedule>();
        try {
            File f = new File("src/main/resources/Files/TherapistFiles/detailed/" + t.getName() + ".txt");
            fr = new FileWriter(f);
            fr.write("ID           Name           Address           Gender           Date           ContactNo");
            fr.write("\n --------------------------------------------------------------------------------------");
            fr.write("\n" + t.getID() + "   " + t.getName() + "   " + t.getAddress().getCity() + " , " + t.getAddress().getStreet() + " , " + t.getAddress().getHouseNum() + "   " + t.getGender() + "   " + t.getDate() + "   " + t.getContactNo());

            workScheduleArrayList = wsdao.SelectAllWS();
            fr.write("\n\n\n--------------------------------------------WorkSchedule----------------------------------------------------\n\n");

            for (WorkSchedule ws : workScheduleArrayList)
                if ((ws.getTherapist().getID().equals(t.getID()))) {
                    fr.write(ws.getDay() + "------" + ws.getShift() + "\n");
                }

            fr.close();
            System.out.println("Test6 Applied");
        } catch (IOException | SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void SaveSpecificPatient(Patient p) {
        FileWriter fr = null;
        try {
            File f = new File("src/main/resources/Files/PatientFiles/detailed/" + p.getName() + ".txt");
            fr = new FileWriter(f);
            fr.write("ID           Name           Address           Gender           Date           ContactNo");
            fr.write("\n --------------------------------------------------------------------------------------");
            fr.write("\n" + p.getID() + "   " + p.getName() + "   " + p.getAddress().getCity() + " , " + p.getAddress().getStreet() + " , " + p.getAddress().getHouseNum() + "   " + p.getGender() + "   " + p.getDate() + "   " + p.getContactNo());


            if (patient_medicineArrayList.size() > 0) {
                fr.write("\n\n\n\n -------------------------------------- Medicine ----------------------------------------");
                fr.write("\n -----------------Name-----------------------------------------Type-----------------------");

                for (patient_medicine item : patient_medicineArrayList) {
                    if (item.getPatientid().equals(p.getID()))
                        for (Medicine m : medicineArrayList)
                            if (item.getMedicinenum() == m.getMedicineNum())
                                fr.write("\n                " + m.getName() + "                                     " + m.getType());
                }
            }
            if (patient_meetingArrayList.size() > 0) {
                fr.write("\n\n\n\n -------------------------------------- Meeting ----------------------------------------");
                fr.write("\n ---------------Date--------------------Address------------------------Time------");
                for (patient_meeting item : patient_meetingArrayList) {
                    if (item.getPatientid().equals(p.getID()))
                        for (Meeting m : meetingArrayList)
                            if (item.getMeetingnum() == m.getNum())
                                fr.write("\n               " + m.getDate() + "           " + m.getAddress().getAddressOrganized() + "                      " + m.getTime() + "\n");
                }
            }
            if (patient_mealArrayList.size() > 0) {
                fr.write("\n\n\n\n -------------------------------------- Meals ----------------------------------------");
                fr.write("\n -----------------Name-----------------------------------------Weight-----------------------");

                for (patient_meal item : patient_mealArrayList) {
                    if (item.getPatientid().equals(p.getID()))
                        for (Meal m : mealArrayList)
                            if (item.getMealName().equals(m.getName()))
                                fr.write("\n                  " + m.getName() + "                                      " + m.getWeight());
                }
            }
            if (patient_allergyArrayList.size() > 0) {
                fr.write("\n\n\n\n -------------------------------------- Allergies ----------------------------------------");
                fr.write("\n -----------------Name-----------------------------------------Weight-----------------------");
                for (patient_allergy item : patient_allergyArrayList) {
                    if (item.getPatientid().equals(p.getID()))
                        for (Allergy a : allergyArrayList)
                            if (item.getAllergyName().equals(a.getName()))
                                fr.write("\n                 " + a.getName() + "                                            " + a.getMedicines().getName() );
                }
            }
            fr.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void SavePatient() {
        FileWriter fr = null;
        DBH.patientDAO pdb = new patientDAO();
        ArrayList<Patient> ALPATIENT = new ArrayList<Patient>();
        try {
            fr = new FileWriter("src/main/resources/Files/PatientFiles/Patients.txt");
            fr.write("ID           Name           Address           Gender           Date           ContactNo");
            fr.write("\n --------------------------------------------------------------------------------------");
            ALPATIENT = pdb.selectAll();
            System.out.println(ALPATIENT.size());
            for (Patient p : ALPATIENT) {
                fr.write("\n" + p.getID() + "     " + p.getName() + "     " + p.getAddress().getCity() + " , " + p.getAddress().getStreet() + " , " + p.getAddress().getHouseNum() + "     " + p.getGender() + "     " + p.getDate() + "     " + p.getContactNo());
            }

            if (medicineArrayList.size() > 0) {
                fr.write("\n -------------------------------------- Medicine ----------------------------------------\n");
                fr.write("\n -----------------Name-----------------------------------------Type-----------------------\n");

                for (Medicine item : medicineArrayList) {
                    fr.write("                " + item.getName() + "                                     " + item.getType() + "\n");
                }
            }
            if (meetingArrayList.size() > 0) {
                fr.write("\n -------------------------------------- Meeting ----------------------------------------\n");
                fr.write("\n ---------------Date--------------------Address------------------------Time------\n");
                for (Meeting item : meetingArrayList) {
                    fr.write("               " + item.getDate() + "           " + item.getAddress().getAddressOrganized() + "                      " + item.getTime() + "\n");
                }
            }
            if (mealArrayList.size() > 0) {
                fr.write("\n -------------------------------------- Meals ----------------------------------------\n");
                fr.write("\n -----------------Name-----------------------------------------Weight-----------------------\n");
                for (Meal item : mealArrayList) {
                    fr.write("                  " + item.getName() + "                                      " + item.getWeight() + "\n");
                }
            }
            if (allergyArrayList.size() > 0) {
                fr.write("\n -------------------------------------- Allergies ----------------------------------------\n");
                fr.write("\n -----------------Name-----------------------------------------Weight-----------------------\n");
                for (Allergy item : allergyArrayList) {
                    fr.write("                 " + item.getName() + "                             " + item.getMedicines() + "\n");
                }
            }
            fr.close();
        } catch (IOException | SQLException e1) {
            e1.printStackTrace();
        }
    }

    public static void SaveMedicines() throws IOException, IOException {
        FileWriter fr = null;
        DBH.medicineDAO MDH = new DBH.medicineDAO();
        ArrayList<Medicine> ALMED = new ArrayList<Medicine>();
        try {
            fr = new FileWriter("src/main/resources/Files/MedicinesFiles/Medicines.txt");
            fr.write("#        Medicine Name        Medicine Type        ");
            fr.write("\n--------------------------------------------------------------------------------------------\n");
            ALMED = MDH.selectAll();
            System.out.println(ALMED.size());
            for (Medicine m : ALMED) {
                fr.write("\n" + m.getMedicineNum() + "      |      " + m.getName() + "      |      " + m.getType() + "      |      " + m.getType());
            }
            fr.close();
        } catch (IOException | SQLException e1) {
            e1.printStackTrace();
        }
    }

    public static void SaveAllergies() throws IOException, IOException {
        FileWriter fr = null;
        DBH.AllergyDAO ADO = new DBH.AllergyDAO();
        ArrayList<Allergy> Allergies = new ArrayList<Allergy>();
        try {
            fr = new FileWriter("src/main/resources/Files/AllergiesFiles/Allergies.txt");
            fr.write(" Allergy Name          MedicineName              ");
            fr.write("\n--------------------------------------------------------------------------------------\n");
            Allergies = ADO.selectAll();
            System.out.println(Allergies.size());
            for (Allergy a : Allergies) {
                fr.write("\n" + a.getName() + "          |          " + a.getMedicines().getName());
            }
            fr.close();
        } catch (IOException | SQLException e1) {
            e1.printStackTrace();
        }
    }

    public static void SaveWorkSchedule() throws IOException, IOException, SQLException {
        FileWriter fr = null;
        DBH.workScheduleDAO wsADO = new DBH.workScheduleDAO();
        ArrayList<WorkSchedule> workScheduleArrayList = new ArrayList<WorkSchedule>();
        workScheduleArrayList = wsADO.SelectAll();
        try {
            fr = new FileWriter("src/main/resources/Files/WorkScheduleFiles/WorkSchedule.txt");
            fr.write(" Therapist ID                 Therapist Name                   Day                      Shift ");
            fr.write("\n------------------------------------------------------------------------------------------------\n");
            for (WorkSchedule ws : workScheduleArrayList) {
                fr.write("\n" + ws.getTherapist().getID() + "          /          " + ws.getTherapist().getName() + "          /          " + ws.getDay() + "           /           " + ws.getShift());
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}