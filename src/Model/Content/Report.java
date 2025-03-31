package Model.Content;

public class Report {
    private static int idCounter = 1;
    private final int reportId;
    private final int reporterId;
    private final int userReportedID;
    private final int contentReportedID;
    private String description;

    public Report(int reporterId, int userReportedID, int contentReportedID, String description) {
        this.reportId = idCounter++;
        this.reporterId = reporterId;
        this.userReportedID = userReportedID;
        this.contentReportedID = contentReportedID;
        this.description = description;
    }

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

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReportId() {
        return reportId;
    }

}
