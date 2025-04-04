package View.UserPanel;

import Controller.Account.UserController;
import Controller.Channel.ChannelController;
import Controller.Content.ContentController;
import Model.Account.Category;
import Model.Account.User;
import Model.Content.Content;
import Model.Content.Format;
import Model.Content.Quality;

import java.time.LocalDate;
import java.util.Scanner;

public class ManageChannelPanel {
    private static ManageChannelPanel instance = null;
    private ChannelController channelController = ChannelController.getInstance();
    private ContentController contentController = ContentController.getInstance();
    private UserController userController = UserController.getInstance();
    public static ManageChannelPanel getInstance() {
        if (instance == null) {
            instance = new ManageChannelPanel();
        }
        return instance;
    }
    private int stringToTimeInt(String time) {
        String[] timeArr = time.split(":");
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (timeArr.length==3){
            hour = Integer.parseInt(timeArr[0]);
            minute = Integer.parseInt(timeArr[1]);
            second = Integer.parseInt(timeArr[2]);
        }
        else if(timeArr.length == 2){
            minute = Integer.parseInt(timeArr[0]);
            second = Integer.parseInt(timeArr[1]);
        }
        return hour * 3600 + minute * 60 + second;
    }
    public void run(Scanner s) {
        System.out.println("-----------------------------------------------\n" +
                "Manage Channel Panel: \n" +
                "AddContent - ContentId\n" +
                "AddPlaylist - PlaylistId\n" +
                "ShowChannelInfo \n" +
                "EditChannelInfo - N\n" +
                "EditChannelInfo - P\n" +
                "ShowSubscribers \n" +
                "Exit"+
                "\n================================================");
        while (true){
            String sc = s.nextLine();
            if (sc.equals("Exit")) {
                System.out.println("Back to main menu" +
                        "\n-----------------------------------------------");
                break;
            }
            String[] commands = sc.split(" - ");
            if (commands[0].equals("Login")){
                System.out.println(userController.login(commands[1],commands[2]));
            }
            else if(commands[0].equals("Logout")){
                System.out.println(userController.logout());
            }
            else if(commands[0].equals("Accountinfo")){
                User user = userController.showUserInfo();
                System.out.println("Username: " + user.getUserName() + "\nName: " + user.getName() + "\nEmail: " + user.getEmail() + "\nPhone number: " + user.getPhoneNumber());
            }
            else if (commands[0].equals("Publish")) {
                if (commands[1].equals("P")) {
                    Boolean isExclusive = commands[2].equals("Y");

                    if(contentController.createPodcast(userController.getUserId(),channelController.getId(),commands[3],isExclusive,commands[4],stringToTimeInt(commands[5]), Category.valueOf(commands[6].toUpperCase()),commands[7],commands[8])){
                        System.out.println("Podcast Created Successfully");
                    } else {
                        System.out.println("Failed to Create Podcast");
                    }
                }
                else if(commands[1].equals("NV")){
                    Boolean isExclusive = commands[2].equals("Y");
                    if(contentController.createNormalVideo(userController.getUserId(),channelController.getId(),commands[3],isExclusive,commands[4],stringToTimeInt(commands[5]), Category.valueOf(commands[6].toUpperCase()),commands[7],commands[8],commands[9], Quality.valueOf("P"+commands[10].toUpperCase()), Format.valueOf(commands[11].toUpperCase()))){
                        System.out.println("Normal Video Created Successfully");
                    } else {
                        System.out.println("Failed to Create Normal Video");
                    }
                }
                else if(commands[1].equals("LS")){
                    Boolean isExclusive = commands[2].equals("Y");
                    if(contentController.createLiveStream(userController.getUserId(),channelController.getId(),commands[3],isExclusive,commands[4],stringToTimeInt(commands[5]), Category.valueOf(commands[6].toUpperCase()),commands[7],commands[8],commands[9], LocalDate.parse(commands[10]))){
                        System.out.println("Live Stream Created Successfully");
                    } else {
                        System.out.println("Failed to Create Live Stream");
                    }
                }
                else if(commands[1].equals("SV")){
                    Boolean isExclusive = commands[2].equals("Y");
                    if(contentController.createShortVideo(userController.getUserId(),channelController.getId(),commands[3],isExclusive,commands[4],stringToTimeInt(commands[5]), Category.valueOf(commands[6].toUpperCase()),commands[7],commands[8],commands[9],commands[10])){
                        System.out.println("Short Video Created Successfully");
                    } else {
                        System.out.println("Failed to Create Short Video");
                    }
                }


            } else if (commands[0].equals("CreatePlaylist")) {
                System.out.println(channelController.addPlaylist(commands[1]));
            } else if (commands[0].equals("ViewChannel")) {
                System.out.println(channelController.showChannelInfo(channelController.getId()).toString());
            } else if (commands[0].equals("ShowChannelContent")) {
                if(channelController.showContent().isEmpty()){
                    System.out.println("No Content");
                }
                for (Content content : channelController.showContent()) {
                    System.out.println(content.toString());
                }
            }

            else if (commands[0].equals("EditChannelInfo")) {
                if (commands[1].equals("N")) {
                    System.out.println(channelController.editChannelInfo(commands[2]));
                } else {
                    System.out.println("Invalid Input");
                }
            } else if (commands[0].equals("ShowChannelSubscribers")) {
                if (channelController.showSubscribers().isEmpty()){
                    System.out.println("No Subscribers");
                }
                else {
                    System.out.println("Subscribers:");
                }
                for(User user : channelController.showSubscribers()){
                    System.out.println(user.getUserName() + "\n");
                }
            }else {
                System.out.println("Invalid command");
            }
        }
    }
}
