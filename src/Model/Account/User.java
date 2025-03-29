package Model.Account;

import Controller.DataBase.DataBaseController;
import Model.Channel.Channel;

import java.util.ArrayList;

public class User extends Account {
    private int credit = 0;
    private ArrayList<Playlist> playlists;
    private Channel channel;
    private ArrayList<Channel> subscriptions;
    private ArrayList<Category> likedCategory;
    private ArrayList<Integer> likedContent;

    public ArrayList<Integer> getLikedContent() {
        return likedContent;
    }

    public void setLikedContent(ArrayList<Integer> likedContent) {
        this.likedContent = likedContent;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public ArrayList<Channel> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ArrayList<Channel> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public ArrayList<Category> getLikedCategory() {
        return likedCategory;
    }
    public void setLikedCategory(ArrayList<Category> likedCategory) {
        this.likedCategory = likedCategory;
    }
    public User(String userName, String password, String name, String email, String phoneNumber, ArrayList<Category> likedCategory) {
        this.id = idCounter++;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.likedCategory = likedCategory;
        DataBaseController.addUser(this);
        this.playlists = new ArrayList<>();
    }


}