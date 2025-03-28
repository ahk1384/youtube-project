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
    private String channelOwner;
    private ArrayList<Playlist> Playlists;
    private ArrayList<User> subscribers;
    public Channel(String channelName, String channelDescription, String channelCover, String channelOwner) {
        this.channelId = idCounter++;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
        this.channelCover = channelCover;
        this.channelOwner = channelOwner;
        this.subscribers = new ArrayList<>();
        this.Playlists = new ArrayList<>();
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

    public String getChannelOwner() {
        return channelOwner;
    }

    public void setChannelOwner(String channelOwner) {
        this.channelOwner = channelOwner;
    }

    public ArrayList<Playlist> getPlaylists() {
        return Playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        Playlists = playlists;
    }

    public ArrayList<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(ArrayList<User> subscribers) {
        this.subscribers = subscribers;
    }
}
