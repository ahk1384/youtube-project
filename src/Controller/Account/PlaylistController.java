package Controller.Account;

import Controller.DataBase.DataBaseController;
import Model.Account.Playlist;
import Model.Account.User;
import Model.Content.Content;

public class PlaylistController {
    private static PlaylistController instance;
    private Playlist currentPlaylist;

    public PlaylistController() {
    }

    public static PlaylistController getInstance() {
        if (instance == null) {
            instance = new PlaylistController();
        }
        return instance;
    }

    public static Playlist getPlaylistById(int userid, int id) {
        User user = DataBaseController.getUserById(userid);
        return user.getPlaylists().stream().filter(playlist -> playlist.getPlaylistId() == id).findFirst().orElse(null);

    }

    public Playlist createPlaylist(String playlistName) {
        Playlist playlist = new Playlist(playlistName);
        currentPlaylist = playlist;
        return playlist;
    }

    public Boolean addContentToPlaylist(Content content) {
        return currentPlaylist.getContents().add(content);
    }

    public Boolean addContentToPlaylist(int userid, int PlaylistId, Content content) {
        currentPlaylist = PlaylistController.getPlaylistById(userid, PlaylistId);
        return currentPlaylist.getContents().add(content);
    }


}
