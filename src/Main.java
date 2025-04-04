import Controller.Account.AdminController;
import Controller.Account.UserController;
import Controller.Channel.ChannelController;
import Controller.Content.ContentController;
import Model.Account.Category;
import Model.Content.Content;
import Model.Content.Format;
import Model.Content.Quality;
import View.MainPanel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MainPanel mainPanel = MainPanel.getInstance();
        mainPanel.run();
    }

}