package Model.Channel;

import Controller.DataBase.DataBaseController;
import Model.Account.Playlist;
import Model.Account.User;

import java.util.ArrayList;

public class Channel {
    private static int idCounter = 1;
    private final int channelId;
    private String channelName;
    private String channelDescription;
    private String channelCover;
    private final int channelOwnerId;
    private final int allContentPlaylistId;
    private ArrayList<Playlist> playlists;
    private ArrayList<User> subscribers;
    private ArrayList<Integer> contentId;

    public Channel(String channelName, String channelDescription, String channelCover, int channelOwner) {
        Playlist allContent = new Playlist("AllContent");
        allContentPlaylistId = allContent.getPlaylistId();
        this.channelId = idCounter++;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
        this.channelCover = channelCover;
        this.channelOwnerId = channelOwner;
        this.subscribers = new ArrayList<>();
        this.playlists = new ArrayList<>();
        playlists.add(allContent);
        this.contentId = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder playlistsString = new StringBuilder();
        StringBuilder subscribersString = new StringBuilder();
        StringBuilder contentString = new StringBuilder();
        if (contentId.isEmpty()) {
            contentString.append("No content");
        } else {
            for (Integer contentId : contentId) {
                contentString.append(DataBaseController.getContentById(contentId)).append(", ");
            }
        }
        if (subscribers.isEmpty()) {
            subscribersString.append("No subscribers");
        } else {
            for (User subscriber : subscribers) {
                subscribersString.append(subscriber.getUserName()).append(", ");
            }
        }
        if (playlists.isEmpty()) {
            playlistsString.append("No playlists");
        } else {
            for (Playlist playlist : playlists) {
                playlistsString.append(playlist.getPlaylistName()).append(", ");
            }
        }
        return "ChannelInfo:" +
                "\nchannelId :" + channelId +
                "\nchannelName : " + channelName +
                "\nchannelDescription : " + channelDescription +
                "\nplaylists : " + playlistsString +
                "\nsubscribers : " + subscribersString;
    }

    public int getAllContentPlaylistId() {
        return allContentPlaylistId;
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

    public Playlist getPlaylistById(int id) {
        return playlists.stream().filter(playlist -> playlist.getPlaylistId() == id).findFirst().orElse(null);
    }


}
