package DBH;

import Model.Address;
import Model.Medicine;
import Model.Therapist;
import Util.DatabaseConnector;
import Util.JPQLHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class medicineDAO implements JPQLHandler {

    static Connection con = DatabaseConnector.getConnection();

    public int insertMedicine(Medicine m) throws SQLException {

        String sql = "insert into medicine(name, type, timesperday, medicinenum) values(?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, m.getName());
        ps.setString(2, m.getType());
        ps.setInt(3, m.getTimesPerDay());
        ps.setInt(4, m.getMedicineNum());

        int rows = ps.executeUpdate();

        ps.close();

        return rows;
    }

    public ArrayList<Medicine> selectAll() throws SQLException {
        ArrayList<Medicine> list = new ArrayList<Medicine>();

        System.out.println(list);

        String sql = "select * from medicine , patient_medicine where medicine.medicinenum = patient_medicine.medicinenum";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Medicine m = new Medicine(rs.getInt("medicinenum"), rs.getString("name"), rs.getString("type"), rs.getInt("timesperday"));
            list.add(m);
        }

        System.out.println(list);
        ps.close();
        rs.close();
        return list;
    }

    public void UpdateMedicine(Medicine m) throws SQLException {

        String sql1 = "update medicine SET name = ?, type=?, timesperday=? where medicinenum=?";
        PreparedStatement ps = con.prepareStatement(sql1);

        ps.setString(1, m.getName());
        ps.setString(2, m.getType());
        ps.setInt(3, m.getTimesPerDay());
        ps.setInt(4, m.getMedicineNum());

        int rows = ps.executeUpdate();
        ps.close();
    }

    public void removeMedicineByID(int medicinenum) throws SQLException {
        String sql1 = "DELETE FROM medicine WHERE medicine.medicinenum=? ";
        PreparedStatement ps = con.prepareStatement(sql1);

        ps.setInt(1 , medicinenum);

        ps.executeUpdate();
        ps.close();
    }


    @Override
    public void SelectQuery() {

    }

    @Override
    public void InsertQuery() {

    }

    @Override
    public void RemoveQuery() {

    }

    @Override
    public int CountQuery() {
        return 0;
    }

    @Override
    public List SelectAllQuery() {
        return null;
    }
}
