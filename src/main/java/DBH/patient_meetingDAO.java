package DBH;

import Model.patient_meeting;
import Util.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class patient_meetingDAO {
    static Connection con = DatabaseConnector.getConnection();


    public int insertToPatient_meeting(String patientid , int meetingnum) throws SQLException {

        String sql = "insert into Patient_meeting(patientid, meetingnum) values(?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, patientid);
        ps.setInt(2, meetingnum);

        int rows = ps.executeUpdate();

        ps.close();

        return rows;
    }

    public int insertToPatient_meeting(patient_meeting pm) throws SQLException {

        String sql = "insert into patient_meeting(patientid, meetingnum) values(?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pm.getPatientid());
        ps.setInt(2, pm.getMeetingnum());
        int rows = ps.executeUpdate();
        ps.close();
        return rows;
    }

    public ArrayList<Model.patient_meeting> selectAll() throws SQLException {
        ArrayList<Model.patient_meeting> list = new ArrayList<Model.patient_meeting>();
        String sql = "select * from patient_meeting";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Model.patient_meeting pm = new Model.patient_meeting(rs.getString("patientid"),rs.getInt("meetingnum"));
            list.add(pm);
        }
        ps.close();
        rs.close();

        return list;
    }



    public void removeByMeetingNum(patient_meeting pm) throws SQLException {

        String sql1 = "DELETE FROM patient_meeting WHERE patient_meeting.meetingnum=? and patient_meeting.patientid=? ";
        PreparedStatement ps = con.prepareStatement(sql1);
        ps.setInt(1 , pm.getMeetingnum());
        ps.setString(2,pm.getPatientid());

        ps.executeUpdate();
        ps.close();

    }

    public int getCount(String id) throws SQLException {
        int numberRow = 0;
        String query = "select count(*) from patient_meeting where patientid=?";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            numberRow = rs.getInt("count(*)");
        }
        return numberRow;
    }



    public int getCount() throws SQLException {
        int numberRow = 0;
        String query = "select count(*) from patient_meeting";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            numberRow = rs.getInt("count(*)");
        }
        return numberRow;
    }
}
