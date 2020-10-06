package Util;

import DBH.patientDAO;
import DBH.therapistDAO;
import DBH.workScheduleDAO;
import Model.*;
import org.hibernate.jdbc.Work;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilesHandler {

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
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Sunday")&&ws.getShift()==1)
                    fr.write(ws.getTherapist().getName()+",  ");
            fr.write("\n 2 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Sunday")&&ws.getShift()==2)
                    fr.write(ws.getTherapist().getName()+",  ");
            fr.write("\n 3 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Sunday")&&ws.getShift()==3)
                    fr.write(ws.getTherapist().getName()+",  ");

            fr.write("\n --------------------------------------------------------------------------------------");
            fr.write(" \n                                        Monday");
            fr.write("\n 1 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Monday")&&ws.getShift()==1)
                    fr.write(ws.getTherapist().getName()+",  ");
            fr.write("\n 2 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Monday")&&ws.getShift()==2)
                    fr.write(ws.getTherapist().getName()+",  ");
            fr.write("\n 3 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Monday")&&ws.getShift()==3)
                    fr.write(ws.getTherapist().getName()+",  ");

            fr.write("\n --------------------------------------------------------------------------------------");
            fr.write("  \n                                       Tuesday");
            fr.write("\n 1 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Tuesday")&&ws.getShift()==1)
                    fr.write(ws.getTherapist().getName()+",  ");
            fr.write("\n 2 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Tuesday")&&ws.getShift()==2)
                    fr.write(ws.getTherapist().getName()+",  ");
            fr.write("\n 3 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Tuesday")&&ws.getShift()==3)
                    fr.write(ws.getTherapist().getName()+",  ");

            fr.write("\n --------------------------------------------------------------------------------------");
            fr.write(" \n                                        Wednesday");
            fr.write("\n 1 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Wednesday")&&ws.getShift()==1)
                    fr.write(ws.getTherapist().getName()+",  ");
            fr.write("\n 2 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Wednesday")&&ws.getShift()==2)
                    fr.write(ws.getTherapist().getName()+",  ");
            fr.write("\n 3 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Wednesday")&&ws.getShift()==3)
                    fr.write(ws.getTherapist().getName()+",  ");

            fr.write("\n --------------------------------------------------------------------------------------");
            fr.write(" \n                                        Thursday");
            fr.write("\n 1 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Thursday")&&ws.getShift()==1)
                    fr.write(ws.getTherapist().getName()+",  ");
            fr.write("\n 2 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Thursday")&&ws.getShift()==2)
                    fr.write(ws.getTherapist().getName()+",  ");
            fr.write("\n 3 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Thursday")&&ws.getShift()==3)
                    fr.write(ws.getTherapist().getName()+",  ");

            fr.write("\n --------------------------------------------------------------------------------------");
            fr.write(" \n                                        Friday");
            fr.write("\n 1 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Friday")&&ws.getShift()==1)
                    fr.write(ws.getTherapist().getName()+",  ");
            fr.write("\n 2 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Friday")&&ws.getShift()==2)
                    fr.write(ws.getTherapist().getName()+",  ");
            fr.write("\n 3 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Friday")&&ws.getShift()==3)
                    fr.write(ws.getTherapist().getName()+",  ");

            fr.write("\n --------------------------------------------------------------------------------------");
            fr.write("\n                                         Saturday");
            fr.write("\n 1 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Saturday")&&ws.getShift()==1)
                    fr.write(ws.getTherapist().getName()+",  ");
            fr.write("\n 2 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Saturday")&&ws.getShift()==2)
                    fr.write(ws.getTherapist().getName()+",  ");
            fr.write("\n 3 shift -->");
            for(WorkSchedule ws : workScheduleArrayList)
                if(ws.getDay().equals("Saturday")&&ws.getShift()==3)
                    fr.write(ws.getTherapist().getName()+",  ");

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

            for(WorkSchedule ws : workScheduleArrayList)
                if((ws.getTherapist().getID().equals(t.getID())))
                {
                    fr.write(ws.getDay()+"------"+ws.getShift()+"\n");
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
            if (f.exists()) {
                fr = new FileWriter(f, true);
                fr.append("\n 122313123123123");
            } else {
                fr = new FileWriter(f);
                fr.write("ID           Name           Address           Gender           Date           ContactNo");
                fr.write("\n --------------------------------------------------------------------------------------");
                fr.write("\n" + p.getID() + "   " + p.getName() + "   " + p.getAddress().getCity() + " , " + p.getAddress().getStreet() + " , " + p.getAddress().getHouseNum() + "   " + p.getGender() + "   " + p.getDate() + "   " + p.getContactNo());
            }
            fr.close();
            System.out.println("Test6 Applied");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void SavePatient() throws IOException, IOException {
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
            fr.close();
            System.out.println("Test7 Applied");
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