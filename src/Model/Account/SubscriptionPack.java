package Model.Account;

public enum SubscriptionPack {
    Gold(180,14),Silver(60 ,9),Bronze(30,5);
    private int price;
    private int allowedDays;
    SubscriptionPack(int allowedDays , int price) {
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
