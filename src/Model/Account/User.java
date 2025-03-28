package Model.Account;

import Model.Channel.Channel;
import Model.DataBase.DataBase;

import java.util.ArrayList;
import java.util.Date;

import java.util.regex.Pattern;

public class User extends Account {
    private int credit;
    Playlist[] Playlists;
    private Channel channel;
    private ArrayList<Channel> subscriptions;
    private ArrayList<Category> likedCategory;
    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Playlist[] getPlaylists() {
        return Playlists;
    }

    public void setPlaylists(Playlist[] playlists) {
        Playlists = playlists;
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
        DataBase.addUser(this);

    }


}
class NormalUser extends User {
    private final int maxPlaylist = 5;
    private final int maxContent = 5;
    public int getMaxPlaylist() {
        return maxPlaylist;
    }

    public int getMaxContent() {
        return maxContent;
    }
    public NormalUser(String userName, String password, String name, String email, String phoneNumber, ArrayList<Category> likedCategory) {
        super(userName, password, name, email, phoneNumber, likedCategory);
    }
}
class PremiumUser extends User {
    private Date expirationDate;
    public Date getExpirationDate() {
        return expirationDate;
    }
    public PremiumUser(String userName, String password, String name, String email, String phoneNumber, ArrayList<Category> likedCategory) {
        super(userName, password, name, email, phoneNumber, likedCategory);
    }
}
