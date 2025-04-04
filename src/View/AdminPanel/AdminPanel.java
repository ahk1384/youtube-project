package View.AdminPanel;

import Controller.Account.AdminController;
import Controller.Account.UserController;
import Controller.Content.ContentController;
import Controller.Content.ReportController;
import Controller.DataBase.DataBaseController;
import Model.Account.User;
import Model.Content.Content;
import Model.Content.Report;

import java.util.Scanner;

public class AdminPanel {
    private ContentController contentController = ContentController.getInstance();
    private UserController userController = UserController.getInstance();
    private AdminController adminController = AdminController.getInstance();
    private ReportController reportController = ReportController.getInstance();
    private static AdminPanel instance = null;
    public static AdminPanel getInstance() {
        if (instance == null) {
            instance = new AdminPanel();
        }
        return instance;
    }
    public void run(Scanner s){
        System.out.println("------------------------------------\n" +
                "Admin Panel: \n" +
                "AccountInfo \n" +
                "ViewPopularContents \n"+
                "Users \n" +
                "Reports \n" +
                "Contents \n" +
                "Exit" +
                "\n================================================");
        while (true) {
            String [] command = s.nextLine().split(" - ");
            if (command[0].equals("Exit")) {
                System.out.println("Back to main " +
                        "\n-----------------------------------------------"
                );
                break;
            }
            else if (command[0].equals("Login")){
                adminController.login(command[1], command[2]);
            }
            else if(command[0].equals("AccountInfo")){
                System.out.println(adminController.AccountInfo());
            }
            else if (command[0].equals("ViewPopularContents")){
                if (contentController.getTopContents().isEmpty()){
                    System.out.println("There is no content");
                }
                for(Content content : contentController.getTopContents()){
                    System.out.println(contentController.showContentInfo(content.getContentId()).toString());
                }
            }
            else if(command[0].equals("Users")){
                if (userController.getUsers().isEmpty()){
                    System.out.println("There is no user");
                }
                for(User user : userController.getUsers()){
                    System.out.println("Username: " + user.getUserName() + "\nName: " + user.getName() + "\nEmail: " + user.getEmail() + "\nPhone number: " + user.getPhoneNumber()+"\n=================================");
                }
            }
            else if(command[0].equals("Contents")){
                if (contentController.getContents().isEmpty()){
                    System.out.println("There is no content");
                }
                for(Content content : contentController.getContents()){
                    System.out.println(contentController.showContentInfo(content.getContentId()).toString());
                }
            }
            else if(command[0].equals("Reports")){
                if (reportController.getReports().isEmpty()){
                    System.out.println("There is no report");
                }
                for (Report report : reportController.getReports()) {
                    System.out.println(reportController.showReport(report));
                }
            }
            else if (command[0].equals("ManageReport")){
                if (command[2].equals("C")){
                    System.out.println(adminController.acceptReport(reportController.getReportbyId(Integer.parseInt(command[1]))));
                }
            }
            else if (command[0].equals("UnbanUser")){
                System.out.println(adminController.unBanUser(Integer.parseInt(command[1])));
            }
            else {
                System.out.println("Invalid command");
            }
        }

    }

}
