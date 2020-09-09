package DBH;

import Model.Medicine;
import Util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class patient_medicineDAO {
    static Connection con = DatabaseConnector.getConnection();

    public int insertToPatient_Medicine(String patientid , String medicineName) throws SQLException {

        String sql = "insert into patient_medicine(patientid, medicinename) values(?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, patientid);
        ps.setString(2, medicineName);

        int rows = ps.executeUpdate();

        ps.close();

        return rows;
    }
}
