package Model.Account;

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
    @Override
    public String toString() {
        return "AdminInfo:" +
                "\nid :" + id +
                "\nuserName : " + userName +
                "\nname : " + name +
                "\nemail : " + email +
                "\nphoneNumber : " + phoneNumber;
    }


}