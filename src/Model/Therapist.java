package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Therapist extends Person{
   private Date dateWorkStart ;
   private ArrayList<Date> workSchedule;

    public Therapist()
    {

    }

    public Therapist(Date dateWorkStart, ArrayList<Date> workSchedule) {
        this.dateWorkStart = dateWorkStart;
        this.workSchedule = workSchedule;
    }


    public Double getExperience()
    {
        return 1.1;
    }

    public Date getDateWorkStart() {
        return dateWorkStart;
    }

    public void setDateWorkStart(Date dateWorkStart) {
        this.dateWorkStart = dateWorkStart;
    }

    public ArrayList<Date> getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(ArrayList<Date> workSchedule) {
        this.workSchedule = workSchedule;
    }

    @Override
    public String toString() {
        return "therapist{" +
                "dateWorkStart=" + dateWorkStart +
                ", workSchedule=" + workSchedule +
                '}' + super.toString();
    }
}
