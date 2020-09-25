package Model;

import DBH.patientDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class patient_meal {
    private String patientid ;
    private String mealName;




    public patient_meal() {
    }

    public patient_meal(String patientid, String mealName) {
        setPatientid(patientid);
        setMealName(mealName);
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }




    @Override
    public String toString() {
        ArrayList<Patient> alp = new ArrayList<Patient>();
        DBH.patientDAO pdao= new patientDAO();
        try {
            alp=pdao.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String patientname="";
        for(Patient p : alp)
        {
            if(getPatientid().equals(p.getID()))
                patientname=p.getName();
        }


        return "PatientID--> "+getPatientid()+" MealName--> "+getMealName()+" PatientNam  --> "+patientname;
    }
}
