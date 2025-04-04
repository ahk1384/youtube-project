package Model.Account;

import Model.Content.Content;

import java.util.ArrayList;

public class Playlist {
    private static int idCounter = 1;
    private final int playlistLimit = 10;
    private final int playlistId;
    private String playlistName;
    private ArrayList<Content> contents;

    public Playlist(String playlistName) {
        this.playlistId = idCounter++;
        this.playlistName = playlistName;
        this.contents = new ArrayList<>();
    }
    @Override
    public String toString() {
        return "Playlist:" +
                "\nplaylistId : " + playlistId +
                "\nplaylistName : " + playlistName +
                "\ncontents : " + contents ;
    }
    public int getPlaylistLimit() {
        return playlistLimit;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public ArrayList<Content> getContents() {
        return contents;
    }

    public void setContents(ArrayList<Content> contents) {
        this.contents = contents;
    }
}
