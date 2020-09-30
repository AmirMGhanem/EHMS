package Model;

public class patient_medicine {
    private String patientid;
     private int medicinenum;
     private int timesperday;
     private int duration;

    public patient_medicine() {
    }

    public patient_medicine(String patientid, int medicinenum, int timesperday, int duration) {
      setPatientid(patientid);
      setMedicinenum(medicinenum);
      setTimesperday(timesperday);
      setDuration(duration);
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

    public int getTimesperday() {
        return timesperday;
    }

    public void setTimesperday(int timesperday) {
        this.timesperday = timesperday;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "patient_medicine{" +
                "patientid='" + patientid + '\'' +
                ", medicinenum=" + medicinenum +
                ", timesperday=" + timesperday +
                ", duration=" + duration +
                '}';
    }
}
