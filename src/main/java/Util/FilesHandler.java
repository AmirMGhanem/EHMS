package Util;

import DBH.patientDAO;
import DBH.therapistDAO;
import Model.Allergy;
import Model.Medicine;
import Model.Patient;
import Model.Therapist;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilesHandler {


    public static void SaveNurse() throws IOException, IOException {
        FileWriter fr = null;
        DBH.therapistDAO tdb = new therapistDAO();
        ArrayList<Therapist> ALTHERAPIST = new ArrayList<Therapist>();
        try {
            fr = new FileWriter("src/main/resources/Files/TherapistFiles/Therapists.txt");
            fr.write("ID           Name           Address           Gender           Date           ContactNo");
            fr.write("\n --------------------------------------------------------------------------------------");
            ALTHERAPIST = tdb.selectAll();
            System.out.println(ALTHERAPIST.size());
            for (Therapist t : ALTHERAPIST) {
                fr.write("\n" + t.getID() + "     " + t.getName() + "     " + t.getAddress().getCity() + " , " + t.getAddress().getStreet() + " , " + t.getAddress().getHouseNum() + "     " + t.getGender() + "     " + t.getDate() + "     " + t.getContactNo());
            }
            fr.close();
            System.out.println("Test6 Applied");
        } catch (IOException | SQLException e1) {
            e1.printStackTrace();


        }


    }

    public void SaveSpecificNurse(Therapist t) {
        FileWriter fr = null;
        try {
            File f = new File("src/main/resources/Files/TherapistFiles/detailed/" + t.getName() + ".txt");
            if(f.exists())
            {
                fr = new FileWriter(f,true);
                fr.append("\n 122313123123123");

            }else
            {
                fr = new FileWriter(f);
                fr.write("ID           Name           Address           Gender           Date           ContactNo");
                fr.write("\n --------------------------------------------------------------------------------------");
                fr.write("\n" + t.getID() + "   " + t.getName() + "   " + t.getAddress().getCity() + " , " + t.getAddress().getStreet() + " , " + t.getAddress().getHouseNum() + "   " + t.getGender() + "   " + t.getDate() + "   " + t.getContactNo());

            }
            fr.close();
            System.out.println("Test6 Applied");
        } catch (IOException e1) {
            e1.printStackTrace();


        }

    }

    public void SaveSpecificPatient(Patient p) {
        FileWriter fr = null;
        try {
            File f = new File("src/main/resources/Files/PatientFiles/detailed/" + p.getName() + ".txt");
            if(f.exists())
            {
                fr = new FileWriter(f,true);
                fr.append("\n 122313123123123");

            }else
            {
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

                fr.write("\n" +m.getMedicineNum() + "      |      " + m.getName() +"      |      "+ m.getType()+"      |      "+m.getType());
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

                fr.write("\n"+ a.getName() + "          |          " + a.getMedicines().getName());
            }
            fr.close();
        } catch (IOException | SQLException e1) {
            e1.printStackTrace();


        }


    }
}
