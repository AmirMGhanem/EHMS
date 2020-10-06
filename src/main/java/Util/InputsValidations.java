package Util;

import java.util.Date;
import java.util.regex.Pattern;

public class InputsValidations {
    //-------------------------------------Add Nurse Pane Validations--------------------------------------
    public boolean isNurseAddingInputsValid(String FirstName, String LastName, String id, Date BirthDate, Date WorkStart, String DigitsNum7, String addressCode, String City, String Street, String HouseNum) {
        Date currentDate = new Date();

        if ((Pattern.matches("[a-zA-Z]+", FirstName))
                && (Pattern.matches("[a-zA-Z]+", LastName))
                && (Pattern.matches("[0-9]+", id)) && (id.length() == 9)
                &&((BirthDate.getYear() < currentDate.getYear())   ||   ((BirthDate.getYear() == currentDate.getYear()) && (BirthDate.getMonth() < currentDate.getMonth()))   ||   ((BirthDate.getYear() == currentDate.getYear()) && (BirthDate.getMonth() == currentDate.getMonth()) && (BirthDate.getDay() < currentDate.getDay())))
                &&((WorkStart.getYear() < currentDate.getYear())   ||   ((WorkStart.getYear() == currentDate.getYear()) && (WorkStart.getMonth() < currentDate.getMonth()))   ||   ((WorkStart.getYear() == currentDate.getYear()) && (WorkStart.getMonth() == currentDate.getMonth()) && (WorkStart.getDay() < currentDate.getDay())))
                &&((BirthDate.getYear() < WorkStart.getYear())     ||   ((BirthDate.getYear() == WorkStart.getYear()) && (BirthDate.getMonth() < WorkStart.getMonth()))       ||   ((BirthDate.getYear() == WorkStart.getYear()) && (BirthDate.getMonth() == WorkStart.getMonth()) && (BirthDate.getDay() < WorkStart.getDay())))
                && (Pattern.matches("[0-9]+", DigitsNum7)) && (DigitsNum7.length() == 7)
                && (Pattern.matches("[0-9]+", addressCode))
                && (Pattern.matches("[a-zA-Z]+", City))
                && (Pattern.matches("[a-zA-Z0-9]+", Street))
                && (Pattern.matches("[0-9]+", HouseNum)))
            return true;
        else
            return false;
    }


    //-------------------------------------Edit Nurse Pane Validations--------------------------------------
    public boolean isNurseEditInputsValid(String FirstName, String LastName,String ContectNum, String City, String Street, String HouseNum) {
        if ((Pattern.matches("[a-zA-Z]+", FirstName))
                && ((Pattern.matches("[a-zA-Z]+", LastName)))
                && ((Pattern.matches("[0-9]+", ContectNum))) && (ContectNum.length() == 10)
                && ((Pattern.matches("[a-zA-Z]+", City)))
                && ((Pattern.matches("[a-zA-Z0-9]+", Street)))
                && ((Pattern.matches("[0-9]+", HouseNum))))
            return true;
        else
            return false;
    }


    //-------------------------------------Add Patient Pane Validations-------------------------------------
    public boolean isPatientAddingInputsValid(String id, String FirstName, String LastName,Date BirthDate, String DigitsNum7, String addressCode, String City, String Street, String HouseNum) {
        Date currentDate = new Date();

        if ((Pattern.matches("[0-9]+", id)) && (id.length() == 9)
                && (Pattern.matches("[a-zA-Z]+", LastName))
                && (Pattern.matches("[a-zA-Z]+", FirstName))
                &&((BirthDate.getYear() < currentDate.getYear())   ||   ((BirthDate.getYear() == currentDate.getYear()) && (BirthDate.getMonth() < currentDate.getMonth()))   ||   ((BirthDate.getYear() == currentDate.getYear()) && (BirthDate.getMonth() == currentDate.getMonth()) && (BirthDate.getDay() < currentDate.getDay())))
                && (Pattern.matches("[0-9]+", DigitsNum7)) && (DigitsNum7.length() == 7)
                && (Pattern.matches("[0-9]+", addressCode))
                && (Pattern.matches("[a-zA-Z]+", City))
                && (Pattern.matches("[a-zA-Z0-9]+", Street))
                && (Pattern.matches("[0-9]+", HouseNum)))
            return true;
        else
            return false;
    }


    //-------------------------------------Edit Patient Pane Validations-------------------------------------
    public boolean isPatientEditInputsValid(String FirstName, String LastName,String ContectNum, String City, String Street, String HouseNum) {
        if ((Pattern.matches("[a-zA-Z]+", FirstName))
                && ((Pattern.matches("[a-zA-Z]+", LastName)))
                && ((Pattern.matches("[0-9]+", ContectNum))) && (ContectNum.length() == 10)
                && ((Pattern.matches("[a-zA-Z]+", City)))
                && ((Pattern.matches("[a-zA-Z0-9]+", Street)))
                && ((Pattern.matches("[0-9]+", HouseNum))))
            return true;
        else
            return false;
    }


    //-------------------------------------Add meal Pane Validations-----------------------------------------
    public boolean isMealAddInputsValid(String mealName, String mealWeight){
        if ((Pattern.matches("[a-zA-Z]+", mealName))
                && ((Pattern.matches("[0-9]+", mealWeight))))
            return true ;
        else
            return false ;
    }



    //-------------------------------------Medicine CRUD Pane Validations------------------------------------
    public  boolean isAddMedInputsValid(String medicineName){
        if ((Pattern.matches("[a-zA-Z0-9]+", medicineName)))
            return true ;
        else
            return false ;
    }

    public  boolean isEditMedInputsValid(String currentMedNum, String newMedName){
        if ((Pattern.matches("[0-9]+", currentMedNum))
                && (Pattern.matches("[a-zA-Z0-9]+", newMedName)))
            return true ;
        else
            return false ;
    }

    public  boolean isAddAllergyInputsValid(String allergyName){
        if ((Pattern.matches("[a-zA-Z]+", allergyName)))
            return true ;
        else
            return false ;
    }

    public  boolean isEditAllergyInputsValid(String currentMedName, String newAllergyName){
        if ((Pattern.matches("[a-zA-Z0-9]+", currentMedName))
                &&(Pattern.matches("[a-zA-Z]+", newAllergyName)))
            return true ;
        else
            return false ;
    }


    //-------------------------------------Meeting Pane Validations-----------------------------------------
    public boolean isMeetingAddingInputsValid(String MeetingName, String addressCode, String City, String Street, String HouseNum , Date MeetingDate) {
        Date currentDate = new Date();

        if ((Pattern.matches("[a-zA-Z]+", MeetingName))
                && (Pattern.matches("[0-9]+", addressCode))
                && (Pattern.matches("[a-zA-Z]+", City))
                && (Pattern.matches("[a-zA-Z0-9]+", Street))
                && (Pattern.matches("[0-9]+", HouseNum))
                &&((MeetingDate.getYear() > currentDate.getYear())   ||   ((MeetingDate.getYear() == currentDate.getYear()) && (MeetingDate.getMonth() > currentDate.getMonth()))   ||   ((MeetingDate.getYear() == currentDate.getYear()) && (MeetingDate.getMonth() == currentDate.getMonth()) && (MeetingDate.getDay() > currentDate.getDay()))))
            return true;
        else
            return false;
    }


    //-------------------------------------Settings Pane Validations-----------------------------------------
    public boolean isUserAddingInputsValid(String UserName, String Password) {
        Date currentDate = new Date();

        if ((Pattern.matches("[a-zA-Z0-9]+", UserName))
                && (Pattern.matches("[a-zA-Z0-9]+", Password)))
            return true;
        else
            return false;
    }
}