package Model.Channel;

import Model.Account.User;

import java.util.Date;

public class comment {
    private static int idCounter = 1;
    private int commentId;
    private User userCommenter;
    private String comment;
    private Date commentDate;
    public comment(User userCommenter, String comment) {
        this.commentId = idCounter++;
        this.userCommenter = userCommenter;
        this.comment = comment;
        this.commentDate = new Date();
    }

    public int getCommentId() {
        return commentId;
    }

    public User getUserCommenter() {
        return userCommenter;
    }

    public String getComment() {
        return comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setUserCommenter(User userCommenter) {
        this.userCommenter = userCommenter;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
