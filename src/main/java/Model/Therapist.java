package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Therapist extends Person{
   private Date WorkDateStart;
   private ArrayList<Date> workSchedule;
   private Calendar cal = Calendar.getInstance();

    public Therapist() {
    }

    public Therapist(String id, String name, Address address, String gender, Date date, String contactNo, Date WorkDateStart, ArrayList<Date> workSchedule) {
        super(id, name, address, gender, date, contactNo);
        this.WorkDateStart = WorkDateStart;
        this.workSchedule = workSchedule;
    }

    public Date getWorkDateStart() {
        return WorkDateStart;
    }

    public void setWorkDateStart(Date WorkDateStart) {
        this.WorkDateStart = WorkDateStart;
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
                "Work Date Start=" + WorkDateStart +
                ", workSchedule=" + workSchedule +
                '}' + super.toString();
    }

    public int getExperience() {
       return getYear(WorkDateStart,new Date());
    }
    int getYear(Date date1,Date date2){
        SimpleDateFormat simpleDateformat=new SimpleDateFormat("yyyy");
        Integer.parseInt(simpleDateformat.format(date1));

        return Integer.parseInt(simpleDateformat.format(date2))- Integer.parseInt(simpleDateformat.format(date1));

    }
}
