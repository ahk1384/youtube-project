package View.UserPanel;

import Controller.Account.UserController;
import Controller.Content.ContentController;
import Controller.DataBase.DataBaseController;
import Model.Account.PremiumUser;
import Model.Channel.Channel;
import Model.Content.Content;

import java.util.Scanner;

public class OfferPanel {
    private static OfferPanel instance = null;
    ContentController contentController = ContentController.getInstance();
    UserController userController = UserController.getInstance();
    public static OfferPanel getInstance() {
        if (instance == null) {
            instance = new OfferPanel();
        }
        return instance;
    }
    public String showOffer() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Content content : contentController.offeredContent()) {
            StringBuilder result = new StringBuilder();
            if(content.getExlusive() && userController.getCurrentUser() instanceof PremiumUser) {
                result.append(content.toString());
                return result.toString();
            }
            else if (!content.getExlusive()){
                result.append(content.toString());
                return result.toString();
            }
        }
        return stringBuilder.toString();
    }
    public void run(Scanner s) {
        System.out.println("-----------------------------------------------\n" +
                "Suggestion Panel : \n"+
                "ViewPopularContents \n" +
                "ViewPopularChannels \n" +
                "Type 'Exit' to return to the main menu."+
                "\n================================================");
        while (true) {
            String sc = s.nextLine();
            if (sc.equals("Exit")) {
                System.out.println("Back to main menu" +
                        "\n-----------------------------------------------");
                return;
            }
            String[] commands = sc.split(" - ");
            if (commands[0].equals("ViewPopularContents")) {
                if (contentController.getTopContents().isEmpty()){
                    System.out.println("There is no content");
                }
                else{
                    System.out.println(showOffer());
                }
            } else if(commands[0].equals("ViewPopularChannels")) {
                for(Channel channel : contentController.topChannels())
                {
                    System.out.println(channel.toString());
                }
            }else {
                System.out.println("Invalid command");
            }
        }
    }


}
