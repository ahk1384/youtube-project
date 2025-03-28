package Model.Content;

import Model.Account.User;
import Model.Account.Category;
import Model.DataBase.DataBase;

public class Podcast extends Content {
    private User owner;
    public Podcast(String contentName, Boolean isExlusive, String contentDescription, int playTime, int viewCount, int likeCount, Category category, String contentPath, String cover, int userId) {
        super(contentName, isExlusive, contentDescription, playTime, viewCount, likeCount, category, contentPath, cover);
        this.owner = DataBase.getUserById(userId);
    }
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
}
