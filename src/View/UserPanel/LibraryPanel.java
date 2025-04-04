package View.UserPanel;

import Controller.Account.PlaylistController;
import Controller.Account.UserController;
import Model.Account.NormalUser;
import Model.Account.Playlist;
import Model.Account.PremiumUser;
import Model.Channel.Channel;
import Model.Content.Content;

import java.lang.foreign.PaddingLayout;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryPanel {
    private UserController userController = UserController.getInstance();
    private static LibraryPanel instance = null;
    public static LibraryPanel getInstance() {
        if (instance == null) {
            instance = new LibraryPanel();
        }
        return instance;
    }
    public void run(Scanner s) {
        System.out.println("-----------------------------------------------\n" +
                "Library Panel: \n" +
                "ShowPlaylists \n" +
                "SelectPlaylist - PlaylistName\n" +
                "CreatePlaylist - PlaylistName\n" +
                "ShowSubscriptions \n" +
                "Exit"+
                "\n================================================");
        while (true){
            String sc = s.nextLine();
            if (sc.equals("Exit")) {
                System.out.println("Back to main menu" +
                        "\n-----------------------------------------------");
                return;
            }
            String[] commands = sc.split(" - ");
            if (commands[0].equals("ShowPlaylists")) {
                System.out.println(userController.showPlaylistNameAndContent());
            }
            else if (commands[0].equals("SelectPlaylist")) {
                ArrayList<Playlist> playlists= PlaylistController.getInstance().getPlaylists(userController.getUserId());
                int counter = 1;
                for (Playlist playlist : playlists) {
                    System.out.println(playlist.getPlaylistId() + " . " + playlist.getPlaylistName());
                }
                System.out.println("Enter the number of the playlist you want to select: \n");
                String sc1 = new Scanner(System.in).nextLine();
                String[] commands1 = sc1.split(" ");
                for(Content content : PlaylistController.getPlaylistById(userController.getUserId(),Integer.parseInt(commands1[0])).getContents()) {
                    System.out.println(content.getContentName());
                }
            }
            else if (commands[0].equals("CreatePlaylist")) {
                System.out.println(userController.createPlaylist(commands[1]));
                }
            else if (commands[0].equals("ShowSubscriptions")) {
                StringBuilder result = new StringBuilder("Channels Subscribed : \n");
                for (Channel channel : userController.getSubscriptions()){
                    result.append(channel.getChannelName()+". \n");
                }
                System.out.println(result);
            }else {
                System.out.println("Invalid command");
            }
        }
    }

}
