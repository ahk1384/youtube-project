package Model.Content;

import java.time.LocalDate;

public class Comment {
    private static int idCounter = 1;
    private int commentId;
    private int userCommenterId;
    private String comment;
    private LocalDate commentDate;
    public Comment(int userCommenter, String comment) {
        this.commentId = idCounter++;
        this.userCommenterId = userCommenter;
        this.comment = comment;
        this.commentDate = LocalDate.now();
    }

    public int getCommentId() {
        return commentId;
    }

    public int getUserCommenterId() {
        return userCommenterId;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setUserCommenterId(int userCommenterId) {
        this.userCommenterId = userCommenterId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }
}
