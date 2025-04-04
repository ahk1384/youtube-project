package Controller.Content;

import Controller.Account.UserController;
import Controller.DataBase.DataBaseController;
import Model.Content.Report;

import java.util.ArrayList;

public class ReportController {
    private static ReportController instance;
    private UserController userController = UserController.getInstance();
    private ContentController contentController = ContentController.getInstance();
    private Report report;
    public static ReportController getInstance() {
        if (instance == null) {
            instance = new ReportController();
        }
        return instance;
    }
    public ArrayList<Report> getReports() {
        return DataBaseController.getReports();
    }
    public String showReport(Report report) {
        return "User reporter: " + userController.getUserName(report.getReporterId()) + "\n"+
                "User reported: " + userController.getUserName(report.getUserReportedID())+ "\n"+
                "ContentReported : \n"+ contentController.showContentInfo(report.getContentReportedID()).toString() +"\n"+
                "Reported reason: " + report.getDescription() + "\n";
    }
    public Report getReportbyId(int id) {
        return DataBaseController.getReportById(id);
    }

}
