package Model;

import java.sql.Date;
import java.util.Objects;

public class WorkSchedule {
    Date Workday;
    int Hours;

    public WorkSchedule() {
    }

    public WorkSchedule(Date workday, int hours) {
        Workday = workday;
        Hours = hours;
    }

    public Date getWorkday() {

        return Workday;
    }

    public void setWorkday(Date workday) {
        Workday = workday;
    }

    public int getHours() {
        return Hours;
    }

    public void setHours(int hours) {
        Hours = hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkSchedule that = (WorkSchedule) o;
        return Hours == that.Hours &&
                Objects.equals(Workday, that.Workday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Workday, Hours);
    }

    @Override
    public String toString() {
        return "WorkSchedule{" +
                "Workday=" + Workday +
                ", Hours=" + Hours +
                '}';
    }
}
