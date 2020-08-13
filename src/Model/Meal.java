package Model;

import java.util.Date;

public class Meal {

    private String name;
    private int weight;
    private Date time;

    public Meal() {
    }

    public Meal(String name, int weight, Date time) {
        this.name = name;
        this.weight = weight;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
