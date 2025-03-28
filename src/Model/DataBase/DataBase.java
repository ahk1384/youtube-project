package Model.DataBase;

import Model.Account.User;
import Model.Content.Content;
import Model.Content.Report;

import java.util.ArrayList;

public class DataBase {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Content> contents = new ArrayList<>();
    private static ArrayList<Report> reports = new ArrayList<>();
    public static void addReport(Report report) {
        reports.add(report);
    }
    public static void addContent(Content content){
        contents.add(content);
    }
    public static void addUser(User user){
        users.add(user);
    }
    public static void removeUser(User user){
        users.remove(user);
    }
    public static void removeContent(Content content){
        contents.remove(content);
    }
    public static void removeReport(Report report){
        reports.remove(report);
    }
    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User getUserById(int userId) {
        return users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null);
    }
}
