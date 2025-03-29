package Model.Channel;

import Model.Account.Playlist;
import Model.Account.User;

import java.util.ArrayList;

public class Channel {
    private static int idCounter = 1;
    private int channelId;
    private String channelName;
    private String channelDescription;
    private String channelCover;
    private int channelOwnerId;
    private ArrayList<Playlist> playlists;
    private ArrayList<User> subscribers;
    private ArrayList<Integer> contentId;
    public Channel(String channelName, String channelDescription, String channelCover, int channelOwner) {
        this.channelId = idCounter++;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
        this.channelCover = channelCover;
        this.channelOwnerId = channelOwner;
        this.subscribers = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.contentId = new ArrayList<>();
    }

    public ArrayList<Integer> getContentId() {
        return contentId;
    }

    public void setContentId(ArrayList<Integer> contentId) {
        this.contentId = contentId;
    }

    public int getChannelId() {
        return channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public String getChannelCover() {
        return channelCover;
    }

    public void setChannelCover(String channelCover) {
        this.channelCover = channelCover;
    }

    public int getChannelOwnerId() {
        return channelOwnerId;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ArrayList<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(ArrayList<User> subscribers) {
        this.subscribers = subscribers;
    }
}
