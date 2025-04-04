package Controller.Account;

import Controller.Channel.ChannelController;
import Controller.DataBase.DataBaseController;
import Model.Account.Admin;
import Model.Account.User;
import Model.Channel.Channel;
import Model.Content.Report;

import javax.xml.crypto.Data;
import java.util.regex.Pattern;

public class AdminController {
    private static AdminController instance;
    private ChannelController channelController = ChannelController.getInstance();
    private Admin admin;

    public AdminController() {
    }

    public static AdminController getInstance() {
        if (instance == null) {
            instance = new AdminController();
        }
        return instance;
    }

    public static AdminController getAdminController() {
        return instance;
    }

    private static Boolean checkPasswordStrength(String password) {
        String strongPasswordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.matches(strongPasswordPattern, password);
    }

    public String login(String userName, String password) {
        if (admin == null) {
            return "create an account first";
        }
        if (admin.getUserName().equals(userName) && admin.getPassword().equals(password)) {
            admin = Admin.getInstance(userName, password, admin.getName(), admin.getEmail(), admin.getPhoneNumber());
            return "Login successful";
        }
        return "Invalid username or password";
    }

    public String createAccount(String userName, String password, String name, String email, String phoneNumber) {
        if (admin != null) {
            return "Account already exists";
        }
        String result = checkSignUpInputs(userName, password, name, email, phoneNumber);
        if (!result.equals("Admin created")) {
            return result;
        }
        admin = Admin.getInstance(userName, password, name, email, phoneNumber);
        return "Admin created";
    }

    protected String checkSignUpInputs(String userName, String password, String name, String email, String phoneNumber) {
        String userNamePattern = "^[a-zA-Z0-9_]{3,15}$";
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        String phoneNumberPattern = "^\\+?[0-9]{10,13}$";
        if (!Pattern.matches(userNamePattern, userName)) {
            return "Invalid username format";
        }
        if (!checkPasswordStrength(password)) {
            return "Password is not strong enough";
        }
        if (!Pattern.matches(emailPattern, email)) {
            return "Invalid email format";
        }
        if (!Pattern.matches(phoneNumberPattern, phoneNumber)) {
            return "Invalid phone number format";
        }
        return "Admin created";
    }

    public String acceptReport(Report report) {
        DataBaseController.getContents().remove(report.getContentReportedID()-1);
        channelController.getContentId().remove(report.getContentReportedID()-1);
        DataBaseController.getReports().remove(report);
        return "Content with id :" + report.getContentReportedID() + " was removed and " + banUser(report.getUserReportedID());
    }

    public String AccountInfo(){
        return "UserName : " + admin.getUserName() + "\n" +
                "Name : " + admin.getName() + "\n" +
                "Email : " + admin.getEmail() + "\n" +
                "Phone Number : " + admin.getPhoneNumber() + "\n";
    }
    public String banUser(int id) {
        DataBaseController.getBanedUser().add(DataBaseController.getUserById(id));
        return "User : " + DataBaseController.getUserById(id).getUserName() + " ban successfully.";
    }

    public String unBanUser(int id) {
        DataBaseController.getBanedUser().remove(DataBaseController.getUserById(id));
        return "User : " + DataBaseController.getUserById(id).getUserName() + "unban successfully.";
    }

    public String showAllUsersInfo() {
        if (DataBaseController.getUsers().isEmpty()) {
            return "No user found. \n";
        }
        StringBuilder result = new StringBuilder();
        for (User user : DataBaseController.getUsers()) {
            result.append(showUserAccountInfo(user.getId()));
            result.append("---------------------------------\n");
        }
        return result.toString();
    }

    public String showUserAccountInfo(int userId) {
        User user = DataBaseController.getUserById(userId);
        String result = "User info : \n" + "User name : " + user.getUserName() + "\n" +
                "Name : " + user.getName() + "\n" +
                "Email : " + user.getEmail() + "\n" +
                "Phone number : " + user.getPhoneNumber() + "\n" +
                "Credit : " + user.getCredit() + "\n";
        return result;
    }

    public String showPopularContenetOnLike() {
        return "Most liked content : \n" + DataBaseController.getContents().stream().max((content1, content2) -> content1.getLikeCount() - content2.getLikeCount()).get().getContentName();
    }

    public String showPopularChannelOnSubscribers() {
        return "Most subscribed channel : \n" + DataBaseController.getChannels().stream().max((channel1, channel2) -> channel1.getSubscribers().size() - channel2.getSubscribers().size()).get().getChannelName();
    }

    public String showChannelAndContents() {
        if (DataBaseController.getChannels().isEmpty()) {
            return "No channel found";
        }
        StringBuilder result = new StringBuilder("Channels info: \n");
        for (Channel channel : DataBaseController.getChannels()) {
            result.append("Channel name : " + channel.getChannelName() + "\n");
            result.append("Channel owner : " + DataBaseController.getUserById(channel.getChannelOwnerId()).getUserName() + "\n");
            result.append("Channel subscribers : " + channel.getSubscribers().size() + "\n");
            result.append("Channel contents : \n");
            if (channel.getContentId().isEmpty()) {
                result.append("No content found\n");
            } else {
                for (Integer id : channel.getContentId()) {
                    result.append("Content name : " + DataBaseController.getContentById(id).getContentName() + "\n");
                }
            }
            result.append("-----------------------------------------------------\n");
        }
        return result.toString();
    }
}
