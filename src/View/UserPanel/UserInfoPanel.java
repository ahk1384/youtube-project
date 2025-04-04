package View.UserPanel;

import Controller.Account.UserController;
import Model.Account.User;

import java.util.Scanner;

public class UserInfoPanel {
    private UserController userController = UserController.getInstance();
    private static UserInfoPanel instance = null;
    public static UserInfoPanel getInstance() {
        if (instance == null) {
            instance = new UserInfoPanel();
        }
        return instance;
    }
    public void showUserInfo() {
        User user = userController.showUserInfo();
        System.out.println(user.toString());
    }

    public void run(Scanner s) {
        System.out.println("-----------------------------------------------\n" +
                "User Info Panel: \n" +
                "AccountInfo\n" +
                "EditUserInfo - N\n" +
                "EditUserInfo - P\n" +
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
            if (commands[0].equals("AccountInfo")) {
                User user = userController.showUserInfo();
                System.out.println(user.toString());
            }
            else if (commands[0].equals("EditUserInfo")) {
                if (commands[1].equals("N")) {
                    System.out.println(userController.editUserInfo(commands[2]));
                } else if (commands[1].equals("P")) {
                    System.out.println(userController.changePassword(commands[3]));
                } else {
                    System.out.println("Invalid Input");
                }
            }

            else {
                System.out.println("Invalid command");
            }

        }
    }
}
