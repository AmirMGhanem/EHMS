package DBH;


import Util.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class patient_medicineDAO {
    static Connection con = DatabaseConnector.getConnection();

    public int insertToPatient_Medicine(Model.patient_medicine pm) throws SQLException {

        String sql = "insert into patient_medicine(patientid, medicinenum,timesperday,duration) values(?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, pm.getPatientid());
        ps.setInt(2, pm.getMedicinenum());
        ps.setInt(3, pm.getTimesperday());
        ps.setInt(4, pm.getDuration());

        int rows = ps.executeUpdate();

        ps.close();

        return rows;
    }

    public void removeByMedicineNum(int medicinenum, String patientid) throws SQLException {
        String sql1 = "DELETE FROM patient_medicine WHERE patient_medicine.medicinenum=? and patient_medicine.patientid=? ";
        PreparedStatement ps = con.prepareStatement(sql1);

        ps.setInt(1, medicinenum);
        ps.setString(2, patientid);

        ps.executeUpdate();
        ps.close();
    }

    public ObservableList<Model.patient_medicine> selectAllObservable() throws SQLException {
        ObservableList list = FXCollections.observableArrayList();

        String sql = "select * from patient_medicine";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Model.patient_medicine pm = new Model.patient_medicine(rs.getString("patientid"), rs.getInt("medicinenum"), rs.getInt("timesperday"), rs.getInt("duration"));
            list.add(pm);
        }

        ps.close();
        rs.close();

        return list;
    }


    public ArrayList<Model.patient_medicine> selectAll() throws SQLException {
        ArrayList<Model.patient_medicine> list = new ArrayList<Model.patient_medicine>();

        System.out.println(list);

        String sql = "select * from patient_medicine";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Model.patient_medicine pm = new Model.patient_medicine(rs.getString("patientid"), rs.getInt("medicinenum"), rs.getInt("timesperday"), rs.getInt("duration"));
            list.add(pm);
        }

        System.out.println(list);

        ps.close();
        rs.close();

        return list;
    }


    public int getCount(String id) throws SQLException {
        int numberRow = 0;
        String query = "select count(*) from patient_medicine where patientid=?";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            numberRow = rs.getInt("count(*)");
        }
        return numberRow;
    }


    public int getCount() throws SQLException {
        int numberRow = 0;
        String query = "select count(*) from patient_medicine";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            numberRow = rs.getInt("count(*)");
        }
        return numberRow;
    }


}
