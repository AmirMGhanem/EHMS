package Util;

import Model.*;

import javax.swing.*;

public interface Service {
/*
    //***************************************************************** Files Adding *****************************************************************
    public void addTherapistToFile(Therapist t);                   //Add Therapist To File
    public void addPatientToFile(Patient p);                       //Add Patient To File
    public void addMealToFile(Meal m);                             //Add Meal To File
    public void addMedicineToFile(Medicine m);                     //Add Medicine To File
    public void addMeetingToFile(Meeting m);                       //Add Meeting To File
    public void addAllergyToFile(Allergy a);                       //Add Allergy To File
    public void addReportToFile(Report r);                         //Add Report To File
    public void addUserToFile(UserInfo u);                             //Add User To File

    //*****************************************************************  Validations  *****************************************************************
    //Id Validation Function
    public default boolean idVal(String id) {
        if ((int) id.length() == 9) return true;
        else {
            JOptionPane.showMessageDialog(null, "ID Number Must Be 9 Digits", "ID Number Must Be 9 Digits", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    //Contact Number Validation Function
    public default boolean contactNumVal(String contactNo) {
        if ((int) contactNo.length() == 10) return true;
        else {
            JOptionPane.showMessageDialog(null, "Phone Number Must Be 10 Digits", "Phone Number Must Be 10 Digits", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    //Houser Number Validation Function
    public default boolean houseNumVal(int houseNum) {
        if (houseNum > 0) return true;
        else {
            JOptionPane.showMessageDialog(null, "House Number Can't Be Smaller Than 0", "House Number Can't Be Smaller Than 0", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    //Meal Weight Validation Functions
    public default boolean weightVal(int weight) {
        if (weight > 0) return true;
        else {
            JOptionPane.showMessageDialog(null, "Meal Weight Must Be Greater That  0", "Meal Weight Must Be Greater That  0", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    //Medicine Times Per Day Validation Function
    public default boolean timesPerDayVal(int timesPerDay) {
        if (timesPerDay > 0) return true;
        else return false;
    }
*/
}
