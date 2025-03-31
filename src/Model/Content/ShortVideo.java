package Model.Content;

import Model.Account.Category;

public class ShortVideo extends Video {
    private String musicSource;

    public ShortVideo(int ownerId, int channelId, String contentName, Boolean isExlusive, String contentDescription,int playTime ,Category category, String contentPath, String cover, String subtitle, String musicSource) {
        super(ownerId, channelId, contentName, isExlusive, contentDescription,playTime,category, contentPath, cover, subtitle);
        this.musicSource = musicSource;
    }

    public String getMusicSource() {
        return musicSource;
    }

    public void setMusicSource(String musicSource) {
        this.musicSource = musicSource;
    }
}