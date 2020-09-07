package Model;

import java.util.ArrayList;

public class Allergy {
    private String name;

    public Allergy(String name) {
        this.name = name;
    }

    private ArrayList<Medicine> medicines = new ArrayList<Medicine>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(ArrayList<Medicine> medicines) {
        this.medicines = medicines;
    }
}
