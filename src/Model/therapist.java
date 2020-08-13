package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class therapist extends Person{
    Date dateWorkStart ;
    ArrayList<Date> workSchedule;

    public therapist ()
    {

    }

    public therapist(Date dateWorkStart, ArrayList<Date> workSchedule) {
        this.dateWorkStart = dateWorkStart;
        this.workSchedule = workSchedule;
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
                '}';
    }
}
