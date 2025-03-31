package Model.Content;

import Model.Account.Category;

import java.time.LocalDate;
import java.util.ArrayList;

public class Content {
    private static int idCounter = 1;
    private final int contentId;
    private String contentName;
    private Boolean isExlusive;
    private String contentDescription;
    private int playTime;
    private int viewCount;
    private int likeCount;
    private LocalDate uploadDate;
    private Category category;
    private String contentPath;
    private String cover;
    private ArrayList<Comment> comments;
    private final int ownerId;
    private int channelId;

    public Content(int ownerId, int channelId, String contentName, Boolean isExlusive, String contentDescription, int playTime, Category category, String contentPath, String cover) {
        this.contentId = idCounter++;
        this.contentName = contentName;
        this.isExlusive = isExlusive;
        this.contentDescription = contentDescription;
        this.playTime = playTime;
        this.viewCount = 0 ;
        this.likeCount = 0 ;
        this.uploadDate = LocalDate.now();
        this.category = category;
        this.contentPath = contentPath;
        this.cover = cover;
        this.comments = new ArrayList<>();
        this.ownerId = ownerId;
        this.channelId = channelId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getContentId() {
        return contentId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public Boolean getExlusive() {
        return isExlusive;
    }

    public void setExlusive(Boolean exlusive) {
        isExlusive = exlusive;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public int getOwnerId() {
        return ownerId;
    }


}


