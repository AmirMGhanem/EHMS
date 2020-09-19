package Model;

import javafx.scene.control.Button;

public class Meal {

    private String name;
    private int weight;


    public Meal() {
    }

    public Meal(String name, int weight) {
       setName(name);
       setWeight(weight);

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

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}