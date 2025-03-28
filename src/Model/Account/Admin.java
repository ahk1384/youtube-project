package Model.Account;

import Model.DataBase.DataBase;

import java.util.regex.Pattern;

public class Admin extends Account {
    private static Admin instance;

    private Admin(String userName, String password, String name, String email, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static Admin getInstance(String userName, String password, String name, String email, String phoneNumber) {
        if (instance == null) {
            instance = new Admin(userName, password, name, email, phoneNumber);
        }
        return instance;
    }


}