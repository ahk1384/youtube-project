package Controller.Account;

import Model.Account.Category;
import Model.Account.User;
import Model.DataBase.DataBase;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class UserController {
    private static UserController instance;
    private static User currentUser;
    private static ArrayList<Category> likedCategory;

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public UserController() {
        likedCategory = new ArrayList<>();
    }

    public static UserController getUserController() {
        return instance;
    }

    public String createAccount(String userName, String password, String name, String email, String phoneNumber) {
        String result = checkSignUpInputs(userName, password, name, email, phoneNumber);
        if (!result.equals("User created")) {
            return result;
        }
        new User(userName, password, name, email, phoneNumber, likedCategory);
        return "User created";
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
        for (User user : DataBase.getUsers()) {
            if (user.getUserName().equals(userName)) {
                return "Username already exists";
            }
            if (user.getEmail().equals(email)) {
                return "Email already exists";
            }
        }
        return "User created";
    }

    private static Boolean checkPasswordStrength(String password) {
        String strongPasswordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        if (Pattern.matches(strongPasswordPattern, password)) {
            return true;
        } else {
            return false;
        }
    }

    public String login(String userName, String password) {
        if (DataBase.getUsers().isEmpty()) {
            return "create an account first";
        }
        for (User user : DataBase.getUsers()) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                currentUser = user;
                return "Login successful";
            }
        }
        return "Invalid username or password";
    }
}
