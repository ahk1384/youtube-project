package Account;
import DataBase.DataBase;

public class Report {
    private User reporter;
    private int userReportedID;
    private int contentReportedID;
    private String description;
    public Report(User reporter, int userReportedID, int contentReportedID, String description) {
        this.reporter = reporter;
        this.userReportedID = userReportedID;
        this.contentReportedID = contentReportedID;
        this.description = description;
    }
}
