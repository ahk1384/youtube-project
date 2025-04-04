package View.UserPanel;

import Controller.Account.UserController;
import Controller.Channel.ChannelController;
import Controller.Content.ContentController;
import Model.Account.Category;
import Model.Channel.Channel;
import Model.Content.Content;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UserPanel {
    private static UserPanel instance = null;
    public static UserPanel getInstance() {
        if (instance == null) {
            instance = new UserPanel();
        }
        return instance;
    }
    private UserController userController = UserController.getInstance();
    private ChannelController channelController = ChannelController.getInstance();
    private ContentController contentController = ContentController.getInstance();
    private UserInfoPanel userInfoPanel = UserInfoPanel.getInstance();
    private SearchAndPlayPanel searchPanel = SearchAndPlayPanel.getInstance();
    private OfferPanel offerPanel = OfferPanel.getInstance();
    public void run(Scanner s) {
        System.out.println("-----------------------------------------------\n" +
                "User Panel: \n" +
                "UserInfo - N\n" +
                "SearchAndPlay - N\n" +
                "Offer - N\n" +
                "Exit"+
                "\n================================================");

        while (true) {
            String sc = s.nextLine();
            if (sc.equals("Exit")) {
                System.out.println("Back to main menu" +
                        "\n-----------------------------------------------");
                break;
            }
            String[] command = sc.split(" - ");
            if (command[0].equals("Signup")) {
                System.out.println(userController.createAccount(command[1], command[2], command[3] + " " + command[4], command[5], command[6],command[7]));
            } else if (command[0].equals("Login")) {
                System.out.println(userController.login(command[1], command[2]));
            } else if (command[0].equals("Logout")) {
                System.out.println(userController.logout());
            } else if (command[0].equals("FavouriteCategories")) {
                ArrayList<Category> categories = new ArrayList<>();
                String[] categoryArray = command[1].split(",");
                for (String categoryName : categoryArray) {
                    categories.add(Category.valueOf(categoryName.trim().toUpperCase()));
                }
                if (userController.addLikedCategory(categories)) {
                    System.out.println("Liked Categories Added Successfully");
                } else {
                    System.out.println("Failed to Add Liked Categories");
                }
            }
            else if (command[0].equals("AccountInfo")) {
                userInfoPanel.showUserInfo();
            } else if (command[0].equals("CreatePlaylist")) {
                if (command[1].equals("U")) {
                    System.out.println(userController.createPlaylist(command[2]));
                } else {
                    System.out.println("Invalid Input");
                }
            } else if (command[0].equals("Play")) {
                System.out.println(contentController.playContent(userController.getUserId(), Integer.parseInt(command[1])));
            } else if (command[0].equals("Like")) {
                System.out.println(userController.likeContent(Integer.parseInt(command[1])));
            } else if (command[0].equals("Report")) {
                System.out.println(userController.setReport(Integer.parseInt(command[1]), command[2]));
            } else if (command[0].equals("Search")) {
                searchPanel.search(command[1]);
            } else if (command[0].equals("Subscribe")) {
                System.out.println(userController.subscribeChannel(Integer.parseInt(command[1])));
            } else if (command[0].equals("ShowPlaylists")) {
                System.out.println(userController.showPlaylistNameAndContent());
            } else if (command[0].equals("ShowSubscriptions")) {
                System.out.println(userController.showSubscription());
            }else if (command[0].equals("GetSuggestions")) {
                System.out.println(offerPanel.showOffer());
            }else if (command[0].equals("CreateChannel")){
                System.out.println(channelController.createChannel(command[1], command[2], command[3] ,userController.getUserId()));
            }
            else if (command[0].equals("AddToPlaylist")){
                System.out.println(userController.addContentToPlaylist(Integer.parseInt(command[1]), Integer.parseInt(command[2])));
            }
            else if (command[0].equals("ShowChannels")) {
                if (channelController.showChannelsInfo().isEmpty()) {
                    System.out.println("No channels found");
                    continue;
                }
                for (Channel channel : channelController.showChannelsInfo()) {
                    System.out.println(channel.toString());
                }
            } else if (command[0].equals("ShowChannel")) {
                System.out.println(channelController.showChannelInfo(Integer.parseInt(command[1])).toString());
            } else if (command[0].equals("ShowFavouritesCategory")){
                System.out.println(userController.showLikedCategory());
            }
            else if (command[0].equals("AddComment")) {
                System.out.println(userController.setComment(Integer.parseInt(command[1]), command[2]));
            } else if (command[0].equals("Sort")) {
                if (command[1].equals("L")) {
                    if (contentController.sortedContents(0).isEmpty()) {
                        System.out.println("No contents found");
                        continue;
                    }
                    for (Content c : contentController.sortedContents(0)) {
                        System.out.println(c.getContentName());
                    }
                } else if (command[1].equals("R")) {
                    if (contentController.sortedContents(1).isEmpty()) {
                        System.out.println("No contents found");
                        continue;
                    }
                    for (Content c : contentController.sortedContents(1)) {
                        System.out.println(c.getContentName());
                    }
                } else if (command[1].equals("V")) {
                    if (contentController.sortedContents(2).isEmpty()) {
                        System.out.println("No contents found");
                        continue;
                    }
                    for (Content c : contentController.sortedContents(2)) {
                        System.out.println(c.getContentName());
                    }
                } else {
                    System.out.println("Invalid Input");
                }
            }
            else if (command[0].equals("GetPremium")) {
                System.out.println(userController.buyOrExtendPremium(command[1]));
            }
            else if (command[0].equals("IncreaseCredit")) {
                System.out.println(userController.addCredit(Integer.parseInt(command[1])));
            }
            else if (command[0].equals("Filter")) {
                if (command[1].equals("C")) {
                    Category category = Category.valueOf(command[2].toUpperCase());
                    if (contentController.filterContent(category).isEmpty()) {
                        System.out.println("No Content Found");
                    }
                    for (Content content : contentController.filterContent(category)) {
                        System.out.println(content.toString());
                    }
                } else if (command[1].equals("D")) {
                    System.out.println(contentController.filterContent(LocalDate.parse(command[2])));
                    if (contentController.filterContent(LocalDate.parse(command[2])).isEmpty()) {
                        System.out.println("No Content Found");

                    }
                    for (Content content : contentController.filterContent(LocalDate.parse(command[2]))) {
                        System.out.println(content.toString());
                    }
                } else if (command[1].equals("V")) {
                    if(contentController.filterContent("Video").isEmpty()) {
                        System.out.println("No Video Found");
                        continue;
                    }
                    for (Content content : contentController.filterContent("Video")) {
                        System.out.println(content.toString());
                    }
                } else if (command[1].equals("P")) {
                    if(contentController.filterContent("Podcast").isEmpty()) {
                        System.out.println("No Podcasts Found");
                        continue;
                    }
                    for (Content content : contentController.filterContent("Podcast")) {
                        System.out.println(content.toString());
                    }
                } else {
                    System.out.println("Invalid Input");
                }
            } else if (command[0].equals("GetPremium")) {
                System.out.println(userController.buyOrExtendPremium(command[1]));
            }
            else if (command[0].equals("IncreaseCredit")) {
                System.out.println(userController.addCredit(Integer.parseInt(command[1])));
            }
            else if (command[0].equals("BuyOrExtendPremium")) {
                System.out.println(userController.buyOrExtendPremium());
            } else {
                System.out.println("Invalid Command");
            }
        }
    }
}
