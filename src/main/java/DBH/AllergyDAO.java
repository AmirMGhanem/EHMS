package DBH;


import Model.Allergy;
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

public class AllergyDAO implements JPQLHandler {

    static Connection con = DatabaseConnector.getConnection();


    medicineDAO mdh = new medicineDAO();


    public int insertAllergy(Allergy a) throws SQLException {

        String sql = "insert into allergy(name, medicinenum) values(?,?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, a.getName());
        ps.setInt(2, a.getMedicines().getMedicineNum());


        int rows = ps.executeUpdate();

        ps.close();

        return rows;
    }



    public ArrayList<Allergy> selectAll() throws SQLException {

        ArrayList<Allergy> list = new ArrayList<Allergy>();
        ArrayList<Medicine> medlist = new ArrayList<Medicine>();
        medlist=mdh.selectAll();
        String sql = "select * from allergy";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Allergy a = new Allergy(rs.getString("name"));
            int mednum = rs.getInt("medicinenum");
            for(Medicine m : medlist)
            {
                if(m.getMedicineNum()==mednum)
                    a.setMedicines(m);
            }

            list.add(a);
        }

        ps.close();
        rs.close();
        return list;
    }

    public ObservableList<Allergy> selectAllObservable() throws SQLException {

        ObservableList<Allergy> list = FXCollections.observableArrayList();
        ArrayList<Medicine> medlist = new ArrayList<Medicine>();
        medlist=mdh.selectAll();
        String sql = "select * from allergy";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Allergy a = new Allergy(rs.getString("name"));
            int mednum = rs.getInt("medicinenum");
            for(Medicine m : medlist)
            {
                if(m.getMedicineNum()==mednum)
                    a.setMedicines(m);
            }

            list.add(a);
        }

        ps.close();
        rs.close();
        return list;
    }


    public void UpdateAllergy(Allergy a,String oldname) throws SQLException {

        String sql1 = "update allergy SET name = ?, medicinenum=? where name=?";
        PreparedStatement ps = con.prepareStatement(sql1);

        ps.setString(1, a.getName());
        ps.setInt(2, a.getMedicines().getMedicineNum());
        ps.setString(3, oldname);

        int rows = ps.executeUpdate();
        System.out.println(rows +" "+ a.getMedicines().getMedicineNum() +"    " + a.getName());
        ps.close();
    }

    public void removeAllergyByName(String name) throws SQLException {
        String sql1 = "DELETE FROM allergy WHERE allergy.name=? ";
        PreparedStatement ps = con.prepareStatement(sql1);

        ps.setString(1 , name);

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


    public ArrayList<String> AllergiesPDF() throws SQLException {
        ArrayList<String> list = new ArrayList<String>();
        String sql = "select * from allergy";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        String str="";

        ArrayList<Medicine> medlist = new ArrayList<Medicine>();
        medlist=mdh.selectAll();
        while (rs.next()) {
            Allergy a = new Allergy(rs.getString("name"));
            int mednum = rs.getInt("medicinenum");
            for(Medicine m : medlist)
            {
                if(m.getMedicineNum()==mednum)
                    a.setMedicines(m);
            }
            str= a.getName() + "          |          " + a.getMedicines().getName();
            list.add(str);
        }



        ps.close();
        rs.close();

        return list;
    }
}
