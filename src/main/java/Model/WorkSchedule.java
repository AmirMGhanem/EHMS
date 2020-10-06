package Model;

import java.sql.Date;
import java.util.Objects;

public class WorkSchedule {
    Therapist therapist;
    String Day;
    int Shift;
    

    public WorkSchedule() {
    }

    public WorkSchedule(Therapist t, String day, int shift) {
        setTherapist(t);
        setDay(day);
        setShift(shift);
    }

    public WorkSchedule(String day, int shift) {
        setDay(day);
        setShift(shift);
    }


    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public int getShift() {
        return Shift;
    }

    public void setShift(int shift) {
        Shift = shift;
    }

    @Override
    public String toString() {
        return "WorkSchedule{" +
                "therapist=" + therapist.getID() +
                ", Day='" + Day + '\'' +
                ", Shift=" + Shift +
                '}';
    }
}
