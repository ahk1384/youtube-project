package Model.Account;

import java.time.LocalDate;
import java.util.ArrayList;

public class PremiumUser extends User {
    private LocalDate expirationDate;
    private SubscriptionPack subscriptionPack;

    public PremiumUser(String userName, String password, String name, String email, String phoneNumber,String cover, ArrayList<Category> likedCategory) {
        super(userName, password, name, email, phoneNumber,cover, likedCategory);
    }

    public SubscriptionPack getSubscriptionPack() {
        return subscriptionPack;
    }

    public void setSubscriptionPack(SubscriptionPack subscriptionPack) {
        this.subscriptionPack = subscriptionPack;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}

