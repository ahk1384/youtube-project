package Model.Account;

public enum SubscriptionPack {
    GOLD(180, 14), SILVER(60, 9), BRONZE(30, 5);
    private final int price;
    private final int allowedDays;

    SubscriptionPack(int allowedDays, int price) {
        this.price = price;
        this.allowedDays = allowedDays;
    }

    public int getPrice() {
        return price;
    }

    public int getAllowedDays() {
        return allowedDays;
    }
}
