package Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Therapist extends Person{
   private Double experience;
   private ArrayList<Date> workSchedule;
    private Calendar cal = Calendar.getInstance();

    public Therapist() {
    }

    public Therapist(String id, String name, Address address, String gender, Date date, String contactNo, Double experience, ArrayList<Date> workSchedule) {
        super(id, name, address, gender, date, contactNo);
        this.experience = experience;
        this.workSchedule = workSchedule;
    }

    public Double getExperience() {
        return experience;
    }

    public void setexperience(double experience) {
        this.experience = experience;
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
                "experience=" + experience +
                ", workSchedule=" + workSchedule +
                '}' + super.toString();
    }
}
