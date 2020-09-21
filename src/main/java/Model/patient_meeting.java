package Model;

public class patient_meeting {
    private String patientid;
    private int meetingnum;

    public patient_meeting() {
    }

    public patient_meeting(String patientid, int meetingnum) {
      setPatientid(patientid);
      setMeetingnum(meetingnum);
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public int getMeetingnum() {
        return meetingnum;
    }

    public void setMeetingnum(int meetingnum) {
        this.meetingnum = meetingnum;
    }

    @Override
    public String toString() {
        return "patient_meeting{" +
                "patientid='" + patientid + '\'' +
                ", meetingnum=" + meetingnum +
                '}';
    }
}
