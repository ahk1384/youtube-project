package Controller.Account;

import Model.Account.Admin;

import java.util.regex.Pattern;

public class AdminController {
    private static AdminController instance;

    public static AdminController getInstance() {
        if (instance == null) {
            instance = new AdminController();
        }
        return instance;
    }

    public AdminController() {
    }

    public static AdminController getAdminController() {
        return instance;
    }

    private Admin admin;

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

    private static Boolean checkPasswordStrength(String password) {
        String strongPasswordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.matches(strongPasswordPattern, password);
    }

}
