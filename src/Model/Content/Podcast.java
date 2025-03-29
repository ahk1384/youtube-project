package Model.Content;

import Controller.DataBase.DataBaseController;
import Model.Account.User;
import Model.Account.Category;

public class Podcast extends Content {
    private User owner;
    public Podcast(int userId,int channelid ,String contentName, Boolean isExlusive, String contentDescription, int playTime, int viewCount, int likeCount, Category category, String contentPath, String cover) {
        super(userId , channelid,contentName, isExlusive, contentDescription, playTime, viewCount, likeCount, category, contentPath, cover);
        this.owner = DataBaseController.getUserById(userId);
    }
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
}
