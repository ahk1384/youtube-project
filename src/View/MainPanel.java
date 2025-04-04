package View;

import Controller.Account.AdminController;
import Controller.Channel.ChannelController;
import View.AdminPanel.AdminPanel;
import View.UserPanel.*;

import java.util.Scanner;

public class MainPanel {
    private static MainPanel instance = null;
    public static MainPanel getInstance() {
        if (instance == null) {
            instance = new MainPanel();
        }
        return instance;
    }
    public void run() {
        AdminController admin = AdminController.getInstance();
        admin.createAccount("amirhkz1384", "@Amir22111384gh", "Amir hossien", "amirhossienghasemi@gmail.com", "09121234567");
        System.out.println("MainPanel: \n" +
                "1.Admin \n" +
                "2.User \n" +
                "3.UserInfo \n" +
                "4.Channel\n" +
                "5.Feed\n" +
                "6.ManageChannel\n" +
                "7.Content\n" +
                "8.Library");
        Scanner s = new Scanner(System.in);
        while (true) {

            String sc = s.nextLine();
            if (sc.equals("Exit")) {
                return;
            }
            if (sc.equals("Admin")) {
                AdminPanel adminPanel = AdminPanel.getInstance();
                adminPanel.run(s);
            }
            else if (sc.equals("UserInfo")){
                UserInfoPanel userInfoPanel = UserInfoPanel.getInstance();
                userInfoPanel.run(s);
            }
            else if (sc.equals("User")) {
                UserPanel userPanel = UserPanel.getInstance();
                userPanel.run(s);
            }
            else if (sc.equals("Channel")) {
                ShowChannelsPanel showChannelsPanel = ShowChannelsPanel.getInstance();
                showChannelsPanel.run(s);
            }
            else if (sc.equals("Feed")) {
                OfferPanel offerPanel = OfferPanel.getInstance();
                offerPanel.run(s);
            }
            else if(sc.equals("ManageChannel")){
                ManageChannelPanel manageChannelPanel = ManageChannelPanel.getInstance();
                manageChannelPanel.run(s);
            }
            else if(sc.equals("Content")) {
                SearchAndPlayPanel searchAndPlayPanel = SearchAndPlayPanel.getInstance();
                searchAndPlayPanel.run(s);
            }
            else if (sc.equals("Library")){
                LibraryPanel libraryPanel = LibraryPanel.getInstance();
                libraryPanel.run(s);
            }
            else {
                System.out.println("Invalid command");
            }
        }

    }
}
