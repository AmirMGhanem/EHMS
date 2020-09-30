package Model;



public class Medicine {

    private  int medicineNum;      //AI
    private String name;
    private String type;


    public Medicine() {
    }

    public Medicine(int medicineNum, String name, String type) {
      setMedicineNum(medicineNum);
      setName(name);
      setType(type);
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



    public int getMedicineNum() {
        return medicineNum;
    }

    public void setMedicineNum (int medicineNum) {
        this.medicineNum = medicineNum;
    }
//-------------Equals And Hash Code ----------------------

    @Override
    public String toString() {
        return "Medicine{" +
                "medicineNum=" + medicineNum +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}