package Model;

import java.util.Objects;

public class Channel {

    private int ChannelNum; //AI
    private Patient patient;
    private Therapist therapist;
    private UserInfo userInfo;

    public Channel() {
    }

    public Channel(Patient patient, Therapist therapist, UserInfo userInfo) {
        this.patient = patient;
        this.therapist = therapist;
        this.userInfo = userInfo;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return Objects.equals(patient, channel.patient) &&
                Objects.equals(therapist, channel.therapist) &&
                Objects.equals(userInfo, channel.userInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient, therapist, userInfo);
    }

    @Override
    public String toString() {
        return "Channel{" +
                "patient=" + patient +
                ", therapist=" + therapist +
                ", userInfo=" + userInfo +
                '}';
    }
}
