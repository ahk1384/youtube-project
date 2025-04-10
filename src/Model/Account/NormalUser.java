package Model.Account;

import java.util.ArrayList;

public class NormalUser extends User {
    private final int maxPlaylist = 5;
    private final int maxContent = 5;

    public NormalUser(String userName, String password, String name, String email, String phoneNumber, String cover,ArrayList<Category> likedCategory) {
        super(userName, password, name, email, phoneNumber,cover, likedCategory);
    }

    public int getMaxPlaylist() {
        return maxPlaylist;
    }

    public int getMaxContent() {
        return maxContent;
    }

}
