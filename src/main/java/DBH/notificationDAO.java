package DBH;

import Model.*;
import Util.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class notificationDAO {
    static Connection con = DatabaseConnector.getConnection();

    private ArrayList<Patient> patientArrayList = new ArrayList<Patient>();
    private ArrayList<Therapist> therapistArrayList = new ArrayList<Therapist>();
    DBH.therapistDAO tdao = new therapistDAO();
    DBH.patientDAO pDAO = new patientDAO();


    public int insertNotification(Notification n) throws SQLException {
        String sql = "insert into notification(request_type, request_desc,patient_id,patient_name,date,therapist,istreated) values(?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, n.getRequest().getType());
        ps.setString(2, n.getRequest().getDescription());
        ps.setString(3, n.getPatient().getID());
        ps.setString(4, n.getPatient().getName());
        ps.setDate(5, new java.sql.Date(n.getDate().getTime()));
        ps.setString(6, "");
        ps.setString(7, n.getIsTreated());

        int rows = ps.executeUpdate();

        ps.close();

        return rows;
    }

    public ArrayList<Notification> selectAll() throws SQLException {
        ArrayList<Notification> list = new ArrayList<Notification>();
        therapistArrayList = tdao.selectAll();
        String sql = "SELECT * FROM  notification";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        patientArrayList = pDAO.selectAll();
        while (rs.next()) {
            Request r = new Request(rs.getString("request_type"), rs.getString("request_desc"));
            Notification n = new Notification();
            n.setNum(rs.getInt("num"));
            for (Patient p : patientArrayList) {
                if (rs.getString("patient_id").equals(p.getID()))
                    n.setPatient(p);
            }
            n.setRequest(r);
            n.setDate(rs.getDate("date"));
            n.setIsTreated(rs.getString("istreated"));
            for (Therapist t : therapistArrayList) {
                if (rs.getString("therapist").equals(t.getID()))
                    n.setTherapist(t);
                else
                    n.setTherapist(new Therapist());
            }
            list.add(n);
        }
        ps.close();
        rs.close();
        return list;
    }


    public Notification selectLastRow() throws SQLException {
        therapistArrayList = tdao.selectAll();
        Notification n = null;
        Request r = null;
        String sql = "SELECT * FROM  notification ORDER BY  num DESC LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        patientArrayList = pDAO.selectAll();
        if (rs.next()) {
            n.setNum(rs.getInt("num"));
            r = new Request(rs.getString("request_type"), rs.getString("request_desc"));
            n = new Notification();
            for (Patient p : patientArrayList) {
                if (rs.getString("patient_id").equals(p.getID()))
                    n.setPatient(p);
            }
            for (Therapist t : therapistArrayList) {
                if (rs.getString("therapist").equals(t.getID()))
                    n.setTherapist(t);
                else
                    n.setTherapist(new Therapist());
            }
            n.setRequest(r);
            n.setDate(rs.getDate("date"));
            n.setIsTreated(rs.getString("istreated"));
        }
        return n;

    }

    public int getCount() throws SQLException {
        int numberRow = 0;
        String query = "select count(*) from notification";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            numberRow = rs.getInt("count(*)");
        }
        return numberRow;
    }


    public void UpdateNotificationToTreated(int num, Therapist t) throws SQLException {
        String sql1 = "update notification SET istreated = ? , therapist=? where num = ?";
        PreparedStatement ps = con.prepareStatement(sql1);
        ps.setString(1, "true");
        ps.setString(2, t.getID());
        ps.setInt(3, num);
        int rows = ps.executeUpdate();
        ps.close();
    }


}
