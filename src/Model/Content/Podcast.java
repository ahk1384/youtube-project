package Model.Content;

import Controller.DataBase.DataBaseController;
import Model.Account.Category;
import Model.Account.User;

public class Podcast extends Content {
    private User owner;

    public Podcast(int userId, int channelid, String contentName, Boolean isExlusive, String contentDescription, int playTime, Category category, String contentPath, String cover) {
        super(userId, channelid, contentName, isExlusive, contentDescription, playTime, category, contentPath, cover);
        this.owner = DataBaseController.getUserById(userId);
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
