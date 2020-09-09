package DBH;

import Model.Medicine;
import Model.Therapist;
import Util.DatabaseConnector;
import Util.JPQLHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class medicineDAO implements JPQLHandler {

    static Connection con = DatabaseConnector.getConnection();

    public int insertMedicine(Medicine m) throws SQLException {

        String sql = "insert into medicine(name, type, timesperday) values(?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, m.getName());
        ps.setString(2, m.getType());
        ps.setInt(3, m.getTimesPerDay());

        int rows = ps.executeUpdate();

        ps.close();

        return rows;
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
