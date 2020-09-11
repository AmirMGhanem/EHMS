package DBH;

import Model.Medicine;
import Util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class patient_medicineDAO {
    static Connection con = DatabaseConnector.getConnection();

    public int insertToPatient_Medicine(String patientid , int medicinenum) throws SQLException {

        String sql = "insert into patient_medicine(patientid, medicinenum) values(?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, patientid);
        ps.setInt(2, medicinenum);

        int rows = ps.executeUpdate();

        ps.close();

        return rows;
    }

    public void removeByMedicineNum(int medicinenum) throws SQLException {
        String sql1 = "DELETE FROM patient_medicine WHERE patient_medicine.medicinenum=? ";
        PreparedStatement ps = con.prepareStatement(sql1);

        ps.setInt(1 , medicinenum);

        ps.executeUpdate();
        ps.close();
    }
}
