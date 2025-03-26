package Channel;

import Account.User;
import Account.Playlist;

import java.util.ArrayList;

public class Channel {
    private int channelId;
    private String channelName;
    private String channelDescription;
    private String channelCover;
    private String channelOwner;
    private ArrayList<Playlist> Playlists;
    private ArrayList<User> subscribers;

}
