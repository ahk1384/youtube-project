package View.UserPanel;

import Controller.Account.PlaylistController;
import Controller.Account.UserController;
import Controller.Content.ContentController;
import Controller.DataBase.DataBaseController;
import Model.Account.Playlist;

import java.util.Scanner;

public class SearchAndPlayPanel {
    private UserController userController = UserController.getInstance();
    private ContentController contentController = ContentController.getInstance();
    private static SearchAndPlayPanel instance = null;
    public static SearchAndPlayPanel getInstance() {
        if (instance == null) {
            instance = new SearchAndPlayPanel();
        }
        return instance;
    }
    public void search(String command) {
        System.out.println(userController.searchContentAndChannelName(command));
    }

    public String playContent(int contentId) {
        if (contentController.getContentById(contentId) == null) {
            return "Content Not Found";
        }
        if (contentController.playContent(userController.getUserId(),contentId) == null){
            return "You Cant Play This Content";
        }
        return contentController.showContentInfo(contentController.playContent(userController.getUserId(),contentId).getContentId()).toString();
    }
    public void run(Scanner s) {
        System.out.println("-----------------------------------------------\n" +
                "Search And Play Panel: \n" +
                "Search - C\n" +
                "Search - M\n" +
                "Select - C\n" +
                "Select - M\n" +
                "Play - ContentId\n" +
                "Like - ContentId\n" +
                "Dislike - ContentId\n" +
                "Report - ContentId - Reason\n" +
                "AddComment - ContentId - Comment\n" +
                "AddToPlaylist - ContentId - PlaylistId\n" +
                "Exit"+
                "\n================================================");
        while (true) {
            String sc = s.nextLine();
            if (sc.equals("Exit")) {
                System.out.println("Back to main menu" +
                        "\n-----------------------------------------------");
                return;
            }
            String[] commands = sc.split(" - ");
            if (commands[0].equals("Login")){
                System.out.println(userController.login(commands[1],commands[2]));}
            else if(commands[0].equals("Logout")){
                System.out.println(userController.logout());
            } else if (commands[0].equals("Search")) {
                search(commands[1]);
            } else if (commands[0].equals("Play")) {
                System.out.println(playContent(Integer.parseInt(commands[1])));
            } else if (commands[0].equals("Like")) {
                System.out.println(userController.likeContent(Integer.parseInt(commands[1])));
            } else if (commands[0].equals("Dislike")) {
                System.out.println(userController.dislikeContent(Integer.parseInt(commands[1])));
            } else if (commands[0].equals("Report")) {
                System.out.println(userController.setReport(Integer.parseInt(commands[1]), commands[2]));
            } else if (commands[0].equals("AddComment")) {
                System.out.println(userController.setComment(Integer.parseInt(commands[1]), commands[2]));
            } else if (commands[0].equals("AddToPlaylist")) {
                System.out.println(userController.addContentToPlaylist(Integer.parseInt(commands[2]), Integer.parseInt(commands[1])));
            } else if (commands[0].equals("ShowPlaylists")) {
                for (Playlist playlist : PlaylistController.getInstance().getPlaylists(userController.getUserId())) {
                    System.out.println("Playlist name: " + playlist.getPlaylistName() + "\n Playlist id: " + playlist.getPlaylistId());
                }
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }
}
