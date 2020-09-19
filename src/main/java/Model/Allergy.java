
package Model;

public class Allergy {
    private String name;
    private Medicine medicines = new Medicine();

    public Allergy() {
    }

    public Allergy(String name, Medicine medicines) {
       setName(name);
       setMedicines(medicines);
    }

    public Allergy(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Medicine getMedicines() {
        return medicines;
    }
    public void setMedicines(Medicine medicines) {
        this.medicines = medicines;
    }

    @Override
    public String toString() {
        return "Allergy{" +
                "name='" + name + '\'' +
                ", medicines=" + medicines +
                '}';
    }


}
