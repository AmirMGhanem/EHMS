package Util;

import java.util.List;

public interface JPQLHandler {

    void SelectQuery();
    void InsertQuery();
    void RemoveQuery();
    int CountQuery();
    List SelectAllQuery();


}
