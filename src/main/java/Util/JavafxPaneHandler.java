package Util;

import java.io.IOException;
import java.sql.SQLException;

public interface JavafxPaneHandler {
     void JavafxTableFill() throws SQLException;
     void JavafxChoiceFill();
     void JavafxDiagramFill() throws IOException;
}
