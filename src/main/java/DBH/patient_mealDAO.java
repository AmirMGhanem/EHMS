package DBH;


import Model.patient_allergy;
import Model.patient_meal;
import Util.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class patient_mealDAO {
    static Connection con = DatabaseConnector.getConnection();

    public int insertToPatient_meal(patient_meal pm) throws SQLException {

        String sql = "insert into patient_meal(patientid, mealname) values(?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pm.getPatientid());
        ps.setString(2, pm.getMealName());
        int rows = ps.executeUpdate();
        ps.close();
        return rows;
    }

    public ObservableList<Model.patient_meal> selectAllObservable() throws SQLException {
        ObservableList list = FXCollections.observableArrayList();

        String sql = "select * from patient_meal";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Model.patient_meal pm = new Model.patient_meal(rs.getString("patientid"),rs.getString("mealname"));
            list.add(pm);
        }
        System.out.println(list);
        ps.close();
        rs.close();

        return list;
    }


    public ArrayList<Model.patient_meal> selectAll() throws SQLException {
        ArrayList<Model.patient_meal> list = new ArrayList<Model.patient_meal>();
        String sql = "select * from patient_meal";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Model.patient_meal pm = new Model.patient_meal(rs.getString("patientid"),rs.getString("mealname"));
            list.add(pm);
        }
        ps.close();
        rs.close();

        return list;
    }

    public void removeByMealName(patient_meal pm) throws SQLException {

        String sql1 = "DELETE FROM patient_meal WHERE patient_meal.mealname=? and patient_meal.patientid=? ";
        PreparedStatement ps = con.prepareStatement(sql1);

        ps.setString(1 , pm.getMealName());
        ps.setString(2,pm.getPatientid());

        ps.executeUpdate();
        ps.close();

    }

    public int getCount(String id) throws SQLException {
        int numberRow = 0;
        String query = "select count(*) from patient_meal where patientid=?";
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
        String query = "select count(*) from patient_meal";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            numberRow = rs.getInt("count(*)");
        }
        return numberRow;
    }
}
