package DBH;


import Model.Medicine;
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

public class medicineDAO {

    static Connection con = DatabaseConnector.getConnection();

    public int insertMedicine(Medicine m) throws SQLException {

        String sql = "insert into medicine(name, type ) values(?,?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, m.getName());
        ps.setString(2, m.getType());



        int rows = ps.executeUpdate();

        ps.close();

        return rows;
    }

    public ArrayList<Medicine> selectAll() throws SQLException {
        ArrayList<Medicine> list = new ArrayList<Medicine>();

        String sql = "select * from medicine";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Medicine m = new Medicine(rs.getInt("medicinenum"), rs.getString("name"), rs.getString("type"));
            list.add(m);
        }

        ps.close();
        rs.close();
        return list;
    }

    public Medicine selectLastRow() throws SQLException {
        Medicine m= null;
        String sql = "SELECT * FROM  medicine ORDER BY  medicinenum DESC LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
             m = new Medicine(rs.getInt("medicinenum"),rs.getString("name"),rs.getString("type"));

        }
        return m;

    }

    public void UpdateMedicine(Medicine m) throws SQLException {

        String sql1 = "update medicine SET name = ?, type=? where medicinenum=?";
        PreparedStatement ps = con.prepareStatement(sql1);

        ps.setString(1, m.getName());
        ps.setString(2, m.getType());
        ps.setInt(3, m.getMedicineNum());

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

    public ObservableList<Medicine> selectMedicines() throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();



        String sql = "select * from medicine";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Medicine m = new Medicine(rs.getInt("medicinenum"), rs.getString("name"), rs.getString("type"));
            list.add(m);
        }



        ps.close();
        rs.close();

        return list;
    }

    public int getCount() throws SQLException {
        int numberRow = 0;
        String query = "select count(*) from medicine";
        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            numberRow = rs.getInt("count(*)");
        }
        return numberRow;
    }

    public ArrayList<String> MedicinesPDF() throws SQLException {
        ArrayList<String> list = new ArrayList<String>();

        String sql = "select * from medicine";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        String str="";
        while (rs.next()) {

            Medicine m = new Medicine(rs.getInt("medicinenum"), rs.getString("name"), rs.getString("type"));
            str=m.getMedicineNum() + "  |  " + m.getName() +"  |  "+ m.getType()+"  |  "+m.getType();
            list.add(str);
        }



        ps.close();
        rs.close();

        return list;
    }






}
