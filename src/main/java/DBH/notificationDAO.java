package DBH;

import Model.*;
import Util.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class notificationDAO {
    static Connection con = DatabaseConnector.getConnection();

    private ArrayList<Patient> patientArrayList = new ArrayList<Patient>();
    DBH.patientDAO pDAO = new patientDAO();



    public int insertNotification(Notification n) throws SQLException {

        String sql = "insert into notification(request_type, request_desc,patient_id,patient_name,date) values(?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, n.getRequest().getType());
        ps.setString(2, n.getRequest().getDescription());
        ps.setString(3,n.getPatient().getID());
        ps.setString(4,n.getPatient().getName());
        ps.setDate(5, (Date) n.getDate());

        int rows = ps.executeUpdate();

        ps.close();

        return rows;
    }
    public Notification selectLastRow() throws SQLException {
        Notification n= null;
        Request r = null;
        String sql = "SELECT * FROM  notification ORDER BY  num DESC LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        patientArrayList=pDAO.selectAll();
        if(rs.next())
        {
            n.setNum(rs.getInt("num"));
            r=new Request(rs.getString("request_type"),rs.getString("request_desc"));
            n=new Notification();
            for(Patient p : patientArrayList)
            {
                if(rs.getString("patient_id").equals(p.getID()))
                    n.setPatient(p);
            }
            n.setRequest(r);
            n.setDate(rs.getDate("date"));


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

}
