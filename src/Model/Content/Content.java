package Model.Content;

import Model.Account.Category;

import java.util.ArrayList;
import java.util.Date;

public class Content {
    private static int idCounter = 1;
    private int contentId;
    private String contentName;
    private Boolean isExlusive;
    private String contentDescription;
    private int playTime;
    private int viewCount;
    private int likeCount;
    private Date uploadDate;
    private Category category;
    private String contentPath;
    private String cover;
    private ArrayList<Comment> comments;
    private int ownerId;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    private int channelId;
    public int getContentId() {
        return contentId;
    }

    public String getContentName() {
        return contentName;
    }

    public Boolean getExlusive() {
        return isExlusive;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public int getPlayTime() {
        return playTime;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public Category getCategory() {
        return category;
    }

    public String getContentPath() {
        return contentPath;
    }

    public String getCover() {
        return cover;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public void setExlusive(Boolean exlusive) {
        isExlusive = exlusive;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public Content(int ownerId,int channelId, String contentName, Boolean isExlusive, String contentDescription, int playTime, int viewCount, int likeCount, Category category, String contentPath, String cover) {
        this.contentId = idCounter++;
        this.contentName = contentName;
        this.isExlusive = isExlusive;
        this.contentDescription = contentDescription;
        this.playTime = playTime;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.uploadDate = new Date();
        this.category = category;
        this.contentPath = contentPath;
        this.cover = cover;
        this.comments = new ArrayList<>();
        this.ownerId = ownerId;
        this.channelId = channelId;
    }


}


