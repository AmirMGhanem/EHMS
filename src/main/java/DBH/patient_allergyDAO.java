package DBH;


import Model.patient_allergy;
import Util.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class patient_allergyDAO {
    static Connection con = DatabaseConnector.getConnection();

    public int insertToPatient_allergy(patient_allergy pm) throws SQLException {

        String sql = "insert into patient_allergy(patientid, allergyname) values(?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pm.getPatientid());
        ps.setString(2, pm.getAllergyName());

        int rows = ps.executeUpdate();

        ps.close();
        System.out.println(rows+" " +pm.getPatientid() + " " + pm.getAllergyName());
        return rows;
    }

    public ObservableList<Model.patient_allergy> selectAllObservable() throws SQLException {
        ObservableList list = FXCollections.observableArrayList();

        String sql = "select * from patient_allergy";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Model.patient_allergy pa = new Model.patient_allergy(rs.getString("patientid"),rs.getString("allergyname"));
            list.add(pa);
        }
        System.out.println(list);
        ps.close();
        rs.close();

        return list;
    }


    public ArrayList<Model.patient_allergy> selectAll() throws SQLException {
        ArrayList<Model.patient_allergy> list = new ArrayList<Model.patient_allergy>();

        System.out.println(list);

        String sql = "select * from patient_allergy";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Model.patient_allergy pa = new Model.patient_allergy(rs.getString("patientid"),rs.getString("allergyname"));
            list.add(pa);
        }

        System.out.println(list);

        ps.close();
        rs.close();

        return list;
    }

    public void removeByAllergyname(patient_allergy pa) throws SQLException {

        String sql1 = "DELETE FROM patient_allergy WHERE patient_allergy.allergyname=? and patient_allergy.patientid=? ";
        PreparedStatement ps = con.prepareStatement(sql1);

        ps.setString(1 , pa.getAllergyName());
        ps.setString(2,pa.getPatientid());

        ps.executeUpdate();
        ps.close();

    }
}
