import Controller.Account.AdminController;
import Controller.Account.UserController;
import Model.Account.Admin;
import Model.Account.Category;
import Model.Account.User;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UserController user=UserController.getInstance();
        AdminController admin=AdminController.getInstance();
        System.out.println(admin.login("amirhkz1384", "@Amir22111384gh"));
        System.out.println(admin.createAccount("amirhkz1384","@mir22111384gh","Amir hossien","amirhossienghasemi@gmail.com","09121234567"));
        System.out.println(admin.login("amirhkz1384","@Amir22111384gh"));
        System.out.println(admin.createAccount("amirhkz1384","@Amir22111384gh","Amir hossien","amirhossienghasemi@gmail.com","09121234567"));
        System.out.println(user.login("amirhkz1384","@Amir22111384gh"));
        System.out.println(user.createAccount("amirhkz1384","@fASgj4878fg","Amir hossien","amirhossienghasemi@gmail.com","09121234567"));
        System.out.println(user.login("amirhkz1384","384gh"));
        System.out.println(user.createAccount("amirhk1384","@Amir22111384gh","Amir hossien","amirhossienghasemi@gmail.com","09121234567"));

    }

}