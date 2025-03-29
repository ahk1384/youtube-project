package Controller.Account;

import Controller.DataBase.DataBaseController;
import Model.Account.NormalUser;
import Model.Account.Playlist;
import Model.Content.Content;
import Model.DataBase.DataBase;
import Model.Account.User;
public class PlaylistController {
    private static PlaylistController instance;
    private Playlist currentPlaylist;
    public static PlaylistController getInstance() {
        if (instance == null) {
            instance = new PlaylistController();
        }
        return instance;
    }
    public PlaylistController() {
    }
    public Playlist createPlaylist(String playlistName) {
        Playlist playlist = new Playlist(playlistName);
        currentPlaylist = playlist;
        return playlist;
    }

    public Boolean addContentToPlaylist(Content content) {
        if(currentPlaylist.getContents().add(content)){
            return true;
        }
        return false;
    }
    public static Playlist getPlaylistById(int userid ,int id){
       User user = DataBaseController.getUserById(userid);
       return user.getPlaylists().stream().filter(playlist -> playlist.getPlaylistId() == id).findFirst().orElse(null);

    }


}
