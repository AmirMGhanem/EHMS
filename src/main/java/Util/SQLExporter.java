package Util;
import com.smattme.MysqlExportService;  //Dependency added to Pom.xml  -> mysql-backup4j
import javafx.stage.DirectoryChooser;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
public class SQLExporter {
    public void export() throws IOException, SQLException, ClassNotFoundException {
        Service service = new Service();
        //required properties for exporting of db
        Properties properties = new Properties();
        properties.setProperty(MysqlExportService.DB_NAME, "ehms");
        properties.setProperty(MysqlExportService.DB_USERNAME, "root");
        properties.setProperty(MysqlExportService.DB_PASSWORD, "");
        //set the outputs temp dir
        MysqlExportService mysqlExportService = new MysqlExportService(properties);
        properties.setProperty(MysqlExportService.PRESERVE_GENERATED_ZIP, "true");
        mysqlExportService.export();

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            String dest = selectedDirectory.getAbsolutePath();
        }
        File dest = new File(selectedDirectory.getPath() + "/EHMS-Backup4j-Tool-Exporter(WithData).sql");
        File file= mysqlExportService.getGeneratedZipFile();

        service.copyFileUsingChannel(file,dest);

        mysqlExportService.clearTempFiles(true);
    }


}
