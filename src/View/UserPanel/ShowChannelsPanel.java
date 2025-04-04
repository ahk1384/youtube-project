package View.UserPanel;

import Controller.Account.UserController;
import Controller.Channel.ChannelController;
import Controller.DataBase.DataBaseController;
import Model.Channel.Channel;

import java.util.Scanner;

public class ShowChannelsPanel {
    private static ShowChannelsPanel instance = null;
    ChannelController channelController = ChannelController.getInstance();
    UserController userController = UserController.getInstance();
    public static ShowChannelsPanel getInstance() {
        if (instance == null) {
            instance = new ShowChannelsPanel();
        }
        return instance;
    }
    public void run(Scanner s) {
        System.out.println("-----------------------------------------------\n" +
                "Show Channels Panel: \n" +
                "ShowChannels"+
                "ShowChannelInfo \n" +
                "Subscribe - ChannelId\n" +
                "Exit"+
                "\n================================================");

        while (true) {
            String sc = s.nextLine();
            if (sc.equals("Exit")) {
                System.out.println("Back to main menu" +
                        "\n-----------------------------------------------");
                break;
            }
            String[] commands = sc.split(" - ");
            if (commands[0].equals("ShowChannels")) {
                if (channelController.showChannelsInfo().isEmpty()) {
                    System.out.println("No channels found");
                    continue;
                }
                for (Channel channel : channelController.showChannelsInfo()) {
                    System.out.println(channel.toString());
                }
            } else if (commands[0].equals("ShowChannel")) {
                if (channelController.showChannelInfo(Integer.parseInt(commands[1])) == null) {
                    System.out.println("Channel not found");
                    continue;
                }
                System.out.println(channelController.showChannelInfo(Integer.parseInt(commands[1])).toString());
            } else if (commands[0].equals("Subscribe")) {
                System.out.println(userController.subscribeChannel(Integer.parseInt(commands[1])));
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }
}
