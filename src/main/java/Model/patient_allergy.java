package Model;

public class patient_allergy {
    private String patientid;
    private String allergyName;

    public patient_allergy() {
    }

    public patient_allergy(String patientid, String allergyName) {
       setPatientid(patientid);
       setAllergyName(allergyName);
    }

    public String getPatientid() {

        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }

    @Override
    public String toString() {
        return "patient_allergy{" +
                "patientid='" + patientid + '\'' +
                ", allergyName='" + allergyName + '\'' +
                '}';
    }
}
