package Model.Content;

import Model.Account.Category;

public class Video extends Content {
    private String subtitle;

    public Video(int ownerId, int channelId, String contentName, Boolean isExlusive, String contentDescription, int playTime, Category category, String contentPath, String cover, String subtitle) {
        super(ownerId, channelId, contentName, isExlusive, contentDescription, playTime, category, contentPath, cover);
        this.subtitle = subtitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}

