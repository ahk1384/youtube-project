package Controller.DataBase;

import Model.Account.Category;
import Model.Account.Playlist;
import Model.Account.User;
import Model.Channel.Channel;
import Model.Content.Content;
import Model.Content.Report;
import Model.DataBase.DataBase;

import java.time.LocalDate;
import java.util.ArrayList;

public class DataBaseController {
    private static final ArrayList<User> banedUser = new ArrayList<>();

    public static ArrayList<Report> getReports() {
        return DataBase.getReports();
    }

    public static void setReports(ArrayList<Report> reports) {
        DataBase.setReports(reports);
    }

    public static ArrayList<Channel> getChannels() {
        return DataBase.getChannels();
    }

    public static void setChannels(ArrayList<Channel> channels) {
        DataBase.setChannels(channels);
    }

    public static ArrayList<Content> getContents() {
        return DataBase.getContents();
    }

    public static ArrayList<User> getBanedUser() {
        return DataBase.getBanedUser();
    }

    public static void setBanedUser(ArrayList<User> banedUser) {
        DataBase.setBanedUser(banedUser);
    }

    public static ArrayList<User> getUsers() {
        return DataBase.getUsers();
    }

    public static User getUserById(int userId) {
        return DataBase.getUsers().stream().filter(user -> user.getId() == userId).findFirst().orElse(null);
    }

    public static Content getContentById(int contentId) {
        return DataBase.getContents().stream().filter(content -> content.getContentId() == contentId).findFirst().orElse(null);
    }

    public static Channel getChannelById(int channelId) {
        return DataBase.getChannels().stream().filter(channel -> channel.getChannelId() == channelId).findFirst().orElse(null);
    }

    public static void addReport(Report report) {
        DataBase.getReports().add(report);
    }

    public static void addContent(Content content) {
        DataBase.getContents().add(content);
    }

    public static void addUser(User user) {
        DataBase.getUsers().add(user);
    }

    public static void removeUser(User user) {
        DataBase.getUsers().remove(user);
    }

    public static void removeContent(Content content) {
        DataBase.getContents().remove(content);
    }

    public static void removeReport(Report report) {
        DataBase.getReports().remove(report);
    }

    public static String searchContent(String target) {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (Content content : DataBase.getContents()) {
            if (content.getContentName().contains(target)) {
                result.append(counter++ + " : " + content.getContentName() + "\n");
            }
        }
        if (result.isEmpty()) {
            return "Nothing Found in Contnet\n";
        }
        return result.toString();
    }

    public static String searchChannelName(String target) {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (Channel channel : DataBase.getChannels()) {
            if (channel.getChannelName().contains(target)) {
                result.append(counter++ + " : " + channel.getChannelName() + "\n");
            }
        }
        if (result.isEmpty()) {
            return "Nothing Found in Channel";
        }
        return result.toString();
    }

    public static ArrayList<Content> getContentsByType(String contentType) {
        ArrayList<Content> contents = new ArrayList<>();
        for (Content content : DataBase.getContents()) {
            if (content.getClass().getSimpleName().equals(contentType)) {
                contents.add(content);
            }
        }
        return contents;
    }

    public static ArrayList<Content> getContentsByCategory(Category category) {
        ArrayList<Content> contents = new ArrayList<>();
        for (Content content : DataBase.getContents()) {
            if (content.getCategory().equals(category)) {
                contents.add(content);
            }
        }
        return contents;
    }

    public static ArrayList<Content> getContentsByDate(LocalDate startTime, LocalDate endTime) {
        ArrayList<Content> contents = new ArrayList<>();
        for (Content content : DataBase.getContents()) {
            if (content.getUploadDate().isAfter(startTime) && content.getUploadDate().isBefore(endTime)) {
                contents.add(content);
            }
        }
        return contents;
    }

    public static ArrayList<Content> getContentsByDate(LocalDate endTime) {
        ArrayList<Content> contents = new ArrayList<>();
        for (Content content : DataBase.getContents()) {
            if (content.getUploadDate().isBefore(endTime)) {
                contents.add(content);
            }
        }
        return contents;
    }

    public ArrayList<Content> ContentSorter(int type) {
        // 1 for like count
        // 2 for view count
        ArrayList<Content> content = DataBaseController.getContents();
        content.sort((o1, o2) -> {
            switch (type) {
                case 1:
                    return o1.getLikeCount() - o2.getLikeCount();
                case 2:
                    return o1.getViewCount() - o2.getViewCount();
                default:
                    return 0;
            }
        });
        return content;
    }

}
