package Controller.Channel;

import Controller.Account.PlaylistController;
import Controller.Account.UserController;
import Controller.DataBase.DataBaseController;
import Model.Account.Playlist;
import Model.Account.User;
import Model.Channel.Channel;
import Model.Content.Content;
import Model.Content.LiveStream;
import Model.Content.ShortVideo;

import java.time.LocalDate;
import java.util.ArrayList;

public class ChannelController {
    Channel channel;

    private static ChannelController instance;

    private ChannelController() {
    }
    public static ChannelController getInstance() {
        if (instance == null) {
            instance = new ChannelController();
        }
        return instance;
    }


    public Boolean createChannel(String name, String description, String cover, int ownerID) {
        Channel channel = new Channel(name, description, cover, ownerID);
        this.channel = channel;
        return DataBaseController.getChannels().add(channel);
    }

    public String addPlaylist(String PlaylistName) {
        Playlist playlist = PlaylistController.getInstance().createPlaylist(PlaylistName);
        return channel.getPlaylists().add(playlist) ? "Playlist added. \n" : "Playlist not added. \n";

    }

    public String switchChannel(int channelId) {
        channel = DataBaseController.getChannelById(channelId);
        return "Channel switched. \n";
    }

    public String addContent(int contetnID, int channelId) {
        Playlist playlist = channel.getPlaylistById(channel.getAllContentPlaylistId());
        Content content = DataBaseController.getContentById(contetnID);
        Channel channel1 = DataBaseController.getChannelById(channelId);
        if (content instanceof ShortVideo) {
            if (content.getPlayTime() > 30) {
                return "your Short vidoe should be less than 30 seconds. \n";
            }
        }
        channel1.getContentId().add(contetnID);
        channel1.getPlaylists().stream().findFirst();
        return "Content added. \n";
    }

    public Boolean addContent(int contetnID, int playlistId , int channelId) {
        addContent(contetnID , channelId);
        Channel channel1 = DataBaseController.getChannelById(channelId);
        Playlist playlist = channel1.getPlaylistById(playlistId);
        return true;
    }

    public ArrayList<User> showSubscribers() {
        ArrayList<User> subscribers = new ArrayList<>();
        for (int i = 0; i < channel.getSubscribers().size(); i++) {
            subscribers.add(channel.getSubscribers().get(i));
        }
        return subscribers;
    }

    public ArrayList<Playlist> showPlaylists() {
//        StringBuilder playlists = new StringBuilder();
//        for (int i = 0; i < channel.getPlaylists().size(); i++) {
//            playlists.append(channel.getPlaylists().get(i).getPlaylistName());
//            playlists.append("\n");
//        }
        return channel.getPlaylists();
    }

    public ArrayList<Content> showContent() {
        ArrayList<Content> content = new ArrayList<>();
        if (channel.getContentId().isEmpty()) {
            return null;
        }
        for (int i = 0; i < channel.getContentId().size(); i++) {
            content.add(DataBaseController.getContentById(channel.getContentId().get(i)));
        }
        return content;
    }

    public Boolean addSubscriber(int userId) {
        if (channel.getSubscribers().contains(DataBaseController.getUserById(userId))) {
            return false;
        }
        return channel.getSubscribers().add(DataBaseController.getUserById(userId));
    }

    public Channel showChannelInfo(int channelId) {
        Channel channel = DataBaseController.getChannelById(channelId);
        return channel;
    }
    public ArrayList<Channel> showChannelsInfo() {
        if (DataBaseController.getChannels().isEmpty()) {
            return null;
        }
        return DataBaseController.getChannels();
    }

    public Boolean removeSubscriber(int userId) {
        if (channel.getSubscribers().contains(DataBaseController.getUserById(userId))) {
            return channel.getSubscribers().remove(DataBaseController.getUserById(userId));
        }
        return false;
    }

    public Boolean editChannelInfo(String name) {
        channel.setChannelName(name);
        return true;
    }
    public ArrayList<Integer> getContentId()
    {
        return channel.getContentId();
    }

    public int getId() {
        return channel.getChannelId();
    }
}
