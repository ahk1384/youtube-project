package Model.Account;

import Model.Content.Content;

import java.util.ArrayList;

public class Playlist {
    private static int idCounter = 1;
    private int playlistId;
    private String playlistName;
    private ArrayList<Content> contents;
    private final int playlistLimit = 10 ;

    public int getPlaylistLimit() {
        return playlistLimit;
    }

    public Playlist(String playlistName) {
        this.playlistId = idCounter++;
        this.playlistName = playlistName;
        this.contents = new ArrayList<>();
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public ArrayList<Content> getContents() {
        return contents;
    }
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
    public void setContents(ArrayList<Content> contents) {
        this.contents = contents;
    }
}
