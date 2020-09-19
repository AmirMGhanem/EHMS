package Model;

public class patient_medicine {
    private String patientid;
     private int medicinenum;

    public patient_medicine(String patientid, int medicinenum) {
        setPatientid(patientid);
        setMedicinenum(medicinenum);
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public int getMedicinenum() {
        return medicinenum;
    }

    public void setMedicinenum(int medicinenum) {
        this.medicinenum = medicinenum;
    }

    @Override
    public String toString() {
        return "patient_medicine{" +
                "patientid='" + patientid + '\'' +
                ", medicinenum=" + medicinenum +
                '}';
    }
}
