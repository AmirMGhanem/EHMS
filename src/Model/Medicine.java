package Model;

import java.util.Objects;

public class Medicine {

    private String name;
    private String type;
    private int timesPerDay;

    public Medicine() {
    }

    public Medicine(String name, String type, int timesPerDay) {
        this.name = name;
        this.type = type;
        this.timesPerDay = timesPerDay;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTimesPerDay() {
        return timesPerDay;
    }

    public void setTimesPerDay(int timesPerDay) {
        this.timesPerDay = timesPerDay;
    }









//-------------Equals And Hash Code ----------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return timesPerDay == medicine.timesPerDay &&
                Objects.equals(name, medicine.name) &&
                Objects.equals(type, medicine.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, timesPerDay);
    }
}
