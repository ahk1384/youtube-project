package Controller.Account;

import Controller.DataBase.DataBaseController;
import Model.Account.*;
import Model.Channel.Channel;
import Model.Content.Comment;
import Model.Content.Content;
import Model.Content.Report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class UserController {
    private static UserController instance;
    private static User currentUser;
    private static User nowRegistredUser;
    private static ArrayList<Category> likedCategory;
    PlaylistController playlist = new PlaylistController();
    private NormalUser normalUser;

    public UserController() {
        likedCategory = new ArrayList<>();

    }

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public int getUserId() {
        return currentUser.getId();
    }

    public static UserController getUserController() {
        return instance;
    }

    private static Boolean checkPasswordStrength(String password) {
        String strongPasswordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.matches(strongPasswordPattern, password);
    }

    public ArrayList<Channel> getSubscriptions() {
        return currentUser.getSubscriptions();
    }

    public ArrayList<Category> getLikedCategory() {
        return likedCategory;
    }


    public Boolean addLikedCategory(ArrayList<Category> likedCategory) {
        nowRegistredUser.setLikedCategory(likedCategory);
        return true;
    }

    public String createAccount(String userName, String password, String name, String email, String phoneNumber, String cover) {
        String result = checkSignUpInputs(userName, password, name, email, phoneNumber);
        if (!result.equals("User created")) {
            return result;
        }
        nowRegistredUser = new NormalUser(userName, password, name, email, phoneNumber, cover, likedCategory);
        nowRegistredUser.getPlaylists().add(playlist.createPlaylist("Liked"));
        nowRegistredUser.getPlaylists().add(playlist.createPlaylist("Watch Later"));
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
        for (User user : DataBaseController.getUsers()) {
            if (user.getUserName().equals(userName)) {
                return "Username already exists";
            }
            if (user.getEmail().equals(email)) {
                return "Email already exists";
            }
        }
        return "User created";
    }

    public String login(String userName, String password) {
        if (DataBaseController.getUsers().isEmpty()) {
            return "create an account first";
        }
        for (User user : DataBaseController.getUsers()) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                if (DataBaseController.getBanedUser().contains(user)) {
                    return "You have ban";
                }
                currentUser = user;
                nowRegistredUser = user;
                return "Login successful";
            }
        }
        return "Invalid username or password";
    }

    public String logout() {
        if (currentUser == null) {
            return "Login first";
        }
        currentUser = null;
        return "Logout successful";
    }

    public User showUserInfo() {
        if (currentUser == null) {
            return null;
        }
        return currentUser;
    }

    public User showUserInfo(int userId) {
        User user = DataBaseController.getUserById(userId);
        return user;
    }

    public String editUserInfo(String name) {
        if (currentUser == null) {
            return "Login first";
        }
        currentUser.setName(name);
        return "User info updated";
    }

    public String getUserName(int id) {
        return DataBaseController.getUserById(id).getUserName();
    }

    public String changePassword(String newPassword) {
        String oldPassword = currentUser.getPassword();
        if (currentUser == null) {
            return "Login first";
        }
        if (!currentUser.getPassword().equals(oldPassword)) {
            return "Wrong password";
        }
        if (!checkPasswordStrength(newPassword)) {
            return "Password is not strong enough";
        }
        currentUser.setPassword(newPassword);
        return "Password changed";
    }

    public String createPlaylist(String playlistName) {
        if (currentUser == null) {
            return "Login first";
        } else {
            if (currentUser instanceof PremiumUser) {
                currentUser.getPlaylists().add(playlist.createPlaylist(playlistName));
                return "Playlist created";
            } else if (currentUser instanceof NormalUser) {
                if (currentUser.getPlaylists().size() < ((NormalUser) currentUser).getMaxPlaylist() + 2) {
                    currentUser.getPlaylists().add(playlist.createPlaylist(playlistName));
                    return "Playlist created";
                } else {
                    return "You can't create more playlists";
                }

            }
        }
        return "playlist not created";
    }

    public String buyOrExtendPremium(String subscriptionPack) {
        SubscriptionPack subscription = SubscriptionPack.valueOf(subscriptionPack.toUpperCase());
        if (currentUser == null) {
            return "Login first";
        }
        if (currentUser instanceof PremiumUser) {
            if (currentUser.getCredit() >= subscription.getPrice()) {
                currentUser.setCredit(currentUser.getCredit() - subscription.getPrice());
                ((PremiumUser) currentUser).setExpirationDate(((PremiumUser) currentUser).getExpirationDate().plusDays(subscription.getAllowedDays()));
                ((PremiumUser) currentUser).setSubscriptionPack(subscription);
                return "Your " + subscriptionPack + " subscription is activated";
            }
            return "You don't have enough credit";
        } else if (currentUser instanceof NormalUser) {
            if (currentUser.getCredit() >= subscription.getPrice()) {
                PremiumUser premiumUser = new PremiumUser(currentUser.getUserName(), currentUser.getPassword(), currentUser.getName(), currentUser.getEmail(), currentUser.getPhoneNumber(), currentUser.getCover(), currentUser.getLikedCategory());
                premiumUser.setCredit(currentUser.getCredit());
                premiumUser.setPlaylists(currentUser.getPlaylists());
                premiumUser.setChannel(currentUser.getChannel());
                if (currentUser.getCredit() >= subscription.getPrice()) {
                    premiumUser.setId(currentUser.getId());
                    DataBaseController.getUsers().remove(currentUser);
                    DataBaseController.getUsers().add(premiumUser);
                    currentUser = premiumUser;
                    premiumUser.setCredit(currentUser.getCredit() - subscription.getPrice());
                    premiumUser.setExpirationDate(LocalDate.now().plusDays(subscription.getAllowedDays()));
                    premiumUser.setSubscriptionPack(subscription);
                    return "Your " + subscriptionPack + " subscription is activated";
                }
            } else {
                return "Not enough credit";
            }
        }
        return "Premium not bought";
    }

    public String buyOrExtendPremium() {
        if (currentUser == null) {
            return "Login first";
        }
        if (currentUser instanceof PremiumUser) {
            if (currentUser.getCredit() >= SubscriptionPack.GOLD.getPrice()) {
                currentUser.setCredit(currentUser.getCredit() - SubscriptionPack.GOLD.getPrice());
                ((PremiumUser) currentUser).setExpirationDate(((PremiumUser) currentUser).getExpirationDate().plusDays(SubscriptionPack.GOLD.getAllowedDays()));
                ((PremiumUser) currentUser).setSubscriptionPack(SubscriptionPack.GOLD);
                return "Your Gold subscription is activated";
            } else if (currentUser.getCredit() >= SubscriptionPack.SILVER.getPrice()) {
                currentUser.setCredit(currentUser.getCredit() - SubscriptionPack.SILVER.getPrice());
                ((PremiumUser) currentUser).setExpirationDate(((PremiumUser) currentUser).getExpirationDate().plusDays(SubscriptionPack.SILVER.getAllowedDays()));
                ((PremiumUser) currentUser).setSubscriptionPack(SubscriptionPack.SILVER);
                return "Your Silver subscription is activated";
            } else if (currentUser.getCredit() >= SubscriptionPack.BRONZE.getPrice()) {
                currentUser.setCredit(currentUser.getCredit() - SubscriptionPack.BRONZE.getPrice());
                ((PremiumUser) currentUser).setExpirationDate(((PremiumUser) currentUser).getExpirationDate().plusDays(SubscriptionPack.BRONZE.getAllowedDays()));
                ((PremiumUser) currentUser).setSubscriptionPack(SubscriptionPack.BRONZE);
                return "Your Bronze subscription is activated";
            } else {
                return "You don't have enough credit";
            }
        } else if (currentUser instanceof NormalUser) {
            if (currentUser.getCredit() >= SubscriptionPack.BRONZE.getPrice()) {
                PremiumUser premiumUser = new PremiumUser(currentUser.getUserName(), currentUser.getPassword(), currentUser.getName(), currentUser.getEmail(), currentUser.getPhoneNumber(), currentUser.getCover(), currentUser.getLikedCategory());
                premiumUser.setCredit(currentUser.getCredit());
                premiumUser.setPlaylists(currentUser.getPlaylists());
                premiumUser.setChannel(currentUser.getChannel());
                if (currentUser.getCredit() >= SubscriptionPack.GOLD.getPrice()) {
                    premiumUser.setExpirationDate(LocalDate.now().plusDays(SubscriptionPack.GOLD.getAllowedDays()));
                    premiumUser.setSubscriptionPack(SubscriptionPack.GOLD);
                    return "Your Gold subscription is activated";
                } else if (currentUser.getCredit() >= SubscriptionPack.SILVER.getPrice()) {
                    premiumUser.setExpirationDate(LocalDate.now().plusMonths(SubscriptionPack.SILVER.getAllowedDays()));
                    premiumUser.setSubscriptionPack(SubscriptionPack.SILVER);
                    return "Your Silver subscription is activated";
                } else if (currentUser.getCredit() >= SubscriptionPack.BRONZE.getPrice()) {
                    premiumUser.setExpirationDate(LocalDate.now().plusDays(SubscriptionPack.BRONZE.getAllowedDays()));
                    premiumUser.setSubscriptionPack(SubscriptionPack.BRONZE);
                    return "Your Bronze subscription is activated";
                }
            } else {
                return "Not enough credit";
            }
        }
        return "Premium not bought";
    }

    public String addCredit(int amount) {
        if (currentUser == null) {
            return "Login first";
        }
        currentUser.setCredit(currentUser.getCredit() + amount);
        return "Credit added";
    }

    public String searchContentAndChannelName(String target) {
        String result = "Content : \n" +
                DataBaseController.searchContent(target) +
                "Channel : \n" +
                DataBaseController.searchChannelName(target);
        return result;
    }

    public String showSubscription() {
        if (currentUser == null) {
            return "Login first";
        }
        StringBuilder result = new StringBuilder();
        for (Channel channel : currentUser.getSubscriptions()) {
            result.append(channel.getChannelName() + "\n");
        }
        return result.toString();
    }

    public String showLikedCategory() {
        if (currentUser == null) {
            return "Login first";
        }
        StringBuilder result = new StringBuilder("Liked Category : \n");
        if (currentUser.getLikedCategory().isEmpty()) {
            result.append("No liked category");
        } else {
            for (Category category : currentUser.getLikedCategory()) {
                result.append(category.toString() + "\n");
            }
        }
        return result.toString();
    }

    public String setReport(int contentId, String description) {
        if (currentUser == null) {
            return "Login first";
        }
        int userReportedId = DataBaseController.getContentById(contentId).getOwnerId();
        DataBaseController.addReport(new Report(currentUser.getId(), userReportedId, contentId, description));
        return "Report send to admin";
    }

    public String likeContent(int contentId) {
        if (currentUser == null) {
            return "Login first";
        }
        Content content = DataBaseController.getContentById(contentId);
        if (content == null) {
            return "Content not found";
        }
        if (content.getOwnerId() == currentUser.getId()) {
            return "You can't like your own content";
        }
        if (currentUser.getLikedContent().contains(contentId)) {
            return "You already liked this content";
        }
        currentUser.getLikedContent().add(contentId);
        content.setLikeCount(content.getLikeCount() + 1);
        return "Content liked";

    }

    public String dislikeContent(int contentId) {
        if (currentUser == null) {
            return "Login first";
        }
        Content content = DataBaseController.getContentById(contentId);
        if (content == null) {
            return "Content not found";
        }
        if (!currentUser.getLikedContent().contains(contentId)) {
            return "You didn't like this content";
        }
        currentUser.getLikedContent().remove(Integer.valueOf(contentId));
        content.setLikeCount(content.getLikeCount() - 1);
        return "Content disliked";
    }

    public String subscribeChannel(int channelId) {
        if (currentUser == null) {
            return "Login first";
        }
        Channel channel = DataBaseController.getChannelById(channelId);
        if (channel == null) {
            return "Channel not found";
        }
        if (channel.getChannelOwnerId() == currentUser.getId()) {
            return "You can't subscribe to your own channel";
        }
        if (currentUser.getSubscriptions().contains(channel)) {
            return "You already subscribed to this channel";
        }
        currentUser.getSubscriptions().add(channel);
        channel.getSubscribers().add(currentUser);
        return "You subscribed to this channel";
    }

    public String showPlaylistNameAndContent() {
        StringBuilder result = new StringBuilder();
        if (currentUser.getPlaylists().isEmpty()) {
            return "You don't have any playlist";
        }
        for (Playlist playlist : currentUser.getPlaylists()) {
            result.append("Playlist : ");
            result.append(playlist.getPlaylistName() + "\n");
            result.append("Contents : \n");
            if (playlist.getContents().isEmpty()) {
                result.append("No content in this playlist\n");
            } else {
                for (Content content : playlist.getContents()) {
                    result.append(content.toString() + "\n");
                }
            }

        }
        return result.toString();
    }

    public ArrayList<Channel> showLikedChannel() {
        /*StringBuilder result = new StringBuilder("Subscribed channel: \n");
        if (currentUser.getSubscriptions().isEmpty()) {
            result.append("No sbscribed channel");
        } else {
            for (Channel channel : currentUser.getSubscriptions()) {
                result.append(channel.getChannelName() + "\n");
            }
        }*/
        return currentUser.getSubscriptions();
    }

    public String setComment(int contentId, String description) {
        if (currentUser == null) {
            return "Login first";
        }
        Content content = DataBaseController.getContentById(contentId);
        if (content == null) {
            return "Content not found";
        }
        if (content.getOwnerId() == currentUser.getId()) {
            return "You can't comment on your own content";
        }
        Comment comment = new Comment(currentUser.getId(), description);
        content.getComments().add(comment);
        return "comment saved successfully";
    }

    public String addContentToPlaylist(int contentId, int playlistId) {
        if (currentUser instanceof PremiumUser) {
            Content content = DataBaseController.getContentById(contentId);
            if (content == null) {
                return "Content not found";
            }
            Playlist playlist = PlaylistController.getPlaylistById(currentUser.getId(), playlistId);
            if (playlist == null) {
                return "Playlist not found";
            }
            if (playlist.getContents().contains(content)) {
                return "Content already in this playlist";
            }
            playlist.getContents().add(content);
            return "Content added to playlist";
        } else if (currentUser instanceof NormalUser) {
            Content content = DataBaseController.getContentById(contentId);
            if (content == null) {
                return "Content not found";
            }
            Playlist playlist = PlaylistController.getPlaylistById(currentUser.getId(), playlistId);
            if (playlist == null) {
                return "Playlist not found";
            }
            if (playlist.getContents().contains(content)) {
                return "Content already in this playlist";
            }
//            if (content instanceof Podcast) {
            if (playlist.getContents().size() <= playlist.getPlaylistLimit()) {
                playlist.getContents().add(content);
                return "Content added to playlist successfully.";
            } else {
                return "You can't add more than 10 contents to a playlist.";
            }
//            } else {
//                return "You can only add podcasts to a playlist.";
//            }
        }
        return "Content not added to playlist";
    }

    public ArrayList<Playlist> getPlaylists() {
        return currentUser.getPlaylists();
    }

    public ArrayList<Integer> getWatchedContent() {
        return currentUser.getWatchedContent();
    }

    public ArrayList<User> getUsers() {
        return DataBaseController.getUsers();
    }

    public ArrayList<Content> showPlaylistContents(int playlistId) {
//        StringBuilder result = new StringBuilder();
        Playlist playlist = PlaylistController.getPlaylistById(currentUser.getId(), playlistId);
//        result.append("Contents : \n");
//        if (playlist.getContents().isEmpty()) {
//            result.append("No content in this playlist\n");
//        } else {
//            for (Content content : playlist.getContents()) {
//                result.append(ContentController.getInstance().showContentInfo(content.getContentId()) + "\n");
//            }
//        }
        return playlist.getContents();
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
