package Account;
import Channel.Channel;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
public class User extends Account {
    private int credit ;
    playlist[] playlists ;
    private Channel channel ;
    private ArrayList<Channel> subscriptions ;
    private ArrayList<Category> likedCategory;

}
class NormalUser extends User {
    private final int maxPlaylist = 5;
    private final int maxContent = 5;
}
class PremiumUser extends User {
    private Date expirationDate;
}
