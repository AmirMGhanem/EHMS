package Model;

import java.util.ArrayList;

public class Report {

    private String reportType;
    private ArrayList<Object> list;

    public Report() {
    }

    public Report(String reportType, ArrayList<Object> list) {
        this.reportType = reportType;
        this.list = list;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public ArrayList<Object> getList() {
        return list;
    }

    public void setList(ArrayList<Object> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportType='" + reportType + '\'' +
                ", list=" + list +
                '}';
    }
}
