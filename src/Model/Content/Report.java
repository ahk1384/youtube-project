package Model.Content;

import Model.Account.User;

public class Report {
    private static int idCounter = 1;
    private int reportId;
    private int reporterId;
    private int userReportedID;
    private int contentReportedID;
    private String description;
    public int getReporterId() {
        return reporterId;
    }

    public int getUserReportedID() {
        return userReportedID;
    }

    public int getContentReportedID() {
        return contentReportedID;
    }

    public String getDescription() {
        return description;
    }

    public int getReportId() {
        return reportId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Report(int reporterId, int userReportedID, int contentReportedID, String description) {
        this.reportId = idCounter++;
        this.reporterId = reporterId;
        this.userReportedID = userReportedID;
        this.contentReportedID = contentReportedID;
        this.description = description;
    }

}
