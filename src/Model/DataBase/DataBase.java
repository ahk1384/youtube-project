package Model.DataBase;

import Model.Account.User;
import Model.Channel.Channel;
import Model.Content.Content;
import Model.Content.Report;

import java.util.ArrayList;

public class DataBase {
    private static final ArrayList<User> users = new ArrayList<>();
    private static final ArrayList<Content> contents = new ArrayList<>();
    private static ArrayList<Report> reports = new ArrayList<>();
    private static ArrayList<Channel> channels = new ArrayList<>();
    private static ArrayList<User> banedUser = new ArrayList<>();

    public static ArrayList<Report> getReports() {
        return reports;
    }

    public static void setReports(ArrayList<Report> reports) {
        DataBase.reports = reports;
    }

    public static ArrayList<Channel> getChannels() {
        return channels;
    }

    public static void setChannels(ArrayList<Channel> channels) {
        DataBase.channels = channels;
    }

    public static ArrayList<Content> getContents() {
        return contents;
    }

    public static ArrayList<User> getBanedUser() {
        return banedUser;
    }

    public static void setBanedUser(ArrayList<User> banedUser) {
        DataBase.banedUser = banedUser;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

}
