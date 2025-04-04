package Controller.Content;

import Controller.Account.UserController;
import Controller.Channel.ChannelController;
import Controller.DataBase.DataBaseController;
import Model.Account.*;
import Model.Channel.Channel;
import Model.Content.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContentController {

    private static ContentController instance;
    private Content content;
    private UserController userController = UserController.getInstance();
    public ContentController() {
    }

    public Content getContentById(int contentId) {
        return DataBaseController.getContentById(contentId);
    }
    public static ContentController getInstance() {
        if (instance == null) {
            instance = new ContentController();
        }
        return instance;
    }

    public Content playContent(int userId, int contentId) {
        User user = DataBaseController.getUserById(userId);
        Content content = DataBaseController.getContentById(contentId);
        if (user instanceof NormalUser && content.getExlusive()) {
            return null;
        }
        content.setViewCount(content.getViewCount() + 1);
        return DataBaseController.getContentById(contentId);
    }

    public ArrayList<Content> filterContent(Category category) {
        return DataBaseController.getContentsByCategory(category);
    }

    public ArrayList<Content> filterContent(String ContentType) {
        ArrayList<Content> contents = new ArrayList<>();
        for (Content content : DataBaseController.getContents()) {
            if (content.getClass().getSimpleName().equals(ContentType)) {
                contents.add(content);
            }
        }
        return contents;

    }

    public String timeIntToString(int time) {
        int hour = time / 3600;
        int minute = (time % 3600) / 60;
        int second = time % 60;
        return String.valueOf(hour)+":"+String.valueOf(minute)+":"+String.valueOf(second);
    }

    public Content showContentInfo(int contentId) {
        return DataBaseController.getContentById(contentId);
    }
    public ArrayList<Content> filterContent(LocalDate startTime, LocalDate endTime) {
        return DataBaseController.getContentsByDate(startTime, endTime);
    }

    public ArrayList<Content> filterContent(LocalDate endTime) {
        return DataBaseController.getContentsByDate(endTime);
    }

    public Boolean createNormalVideo(int ownerId, int channelId, String contentName, Boolean isExlusive, String contentDescription, int playTime, Category category, String contentPath, String cover, String subtitle, Quality quality, Format format) {
        content = new NormalVideo(ownerId, channelId, contentName, isExlusive, contentDescription, playTime, category, contentPath, cover, subtitle, quality, format);
        DataBaseController.getContents().add(content);
        ChannelController.getInstance().addContent(content.getContentId(),channelId);
        return true;
    }

    public Boolean createShortVideo(int ownerId, int channelId, String contentName, Boolean isExlusive, String contentDescription, int playTime,Category category, String contentPath, String cover, String subtitle, String musicSource) {
        content = new ShortVideo(ownerId, channelId, contentName, isExlusive, contentDescription, playTime, category, contentPath, cover, subtitle, musicSource);
        DataBaseController.getContents().add(content);
        ChannelController.getInstance().addContent(content.getContentId(),channelId);
        return true;
    }

    public Boolean createLiveStream(int ownerId, int channelId, String contentName, Boolean isExlusive, String contentDescription, int playTime, Category category, String contentPath, String cover, String subtitle, LocalDate streamLink) {
        content = new LiveStream(ownerId, channelId, contentName, isExlusive, contentDescription, playTime, category, contentPath, cover, subtitle, streamLink);
        DataBaseController.getContents().add(content);
        ChannelController.getInstance().addContent(content.getContentId(),channelId);
        return true;
    }

    public Boolean createPodcast(int userId, int channelId, String contentName, Boolean isExlusive, String contentDescription, int playTime, Category category, String contentPath, String cover) {
        content = new Podcast(userId, channelId, contentName, isExlusive, contentDescription, playTime, category, contentPath, cover);
        DataBaseController.getContents().add(content);
        ChannelController.getInstance().addContent(content.getContentId(),channelId);
        return true;
    }

    private ArrayList<Content> getLiveStreams() {
        ArrayList<Content> liveStreams = new ArrayList<>();
        int counter = 0;
        for (Channel channel : topChannelsForUser()) {
            for (Content content : topContentForUser(channel)) {
                if (content instanceof LiveStream)
                    if (((LiveStream) content).getStartTime().equals(LocalDate.now()) && counter < 3) {
                        liveStreams.add(content);
                        counter++;
                    }
            }
        }
        return liveStreams;
    }

    private ArrayList<Content> getNewVideos() {
        ArrayList<Content> newVideos = new ArrayList<>();
        int counter = 0;
        for (Channel channel : topChannelsForUser()) {
            for (Content content : topContentForUser(channel)) {
                if (content.getUploadDate().isAfter(LocalDate.now().minusDays(5)) && counter < 10) {
                    newVideos.add(content);
                    counter++;
                }
            }
        }
        return newVideos;
    }

    private ArrayList<Content> getNewVideosOnCategory() {
        ArrayList<Content> newVideos = new ArrayList<>();
        int counter = 0;
        for (Channel channel : topChannels()) {
            for (Content content : topContentForUser(channel)) {
                if (content.getUploadDate().isAfter(LocalDate.now().minusDays(5))) {
                    if (UserController.getInstance().getLikedCategory().contains(content.getCategory()) && counter < 15) {
                        newVideos.add(content);
                        counter++;
                    }
                }
            }
        }
        return newVideos;
    }

    private ArrayList<Content> getOldVideos() {
        ArrayList<Content> oldVideos = new ArrayList<>();
        int counter = 0;
        for (Channel channel : topChannelsForUser()) {
            for (Content content : topContentForUser(channel)) {
                if (content.getUploadDate().isBefore(LocalDate.now().minusDays(5)) && counter < 10) {
                    oldVideos.add(content);
                    counter++;
                }
            }
        }
        return oldVideos;
    }

    private ArrayList<Content> getWatchLater() {
        int counter = 0;
        ArrayList<Content> watchLater = new ArrayList<>();
        Playlist playlist = UserController.getInstance().getPlaylists().stream().filter(playlist1 -> playlist1.getPlaylistName().equals("Watch Later")).findFirst().orElse(null);
        if (playlist == null) {
            return watchLater;
        }
        for (Content content : playlist.getContents().reversed()) {
            if (counter <= 3) {
                watchLater.add(content);
                counter++;
            } else {
                break;
            }
        }
        return watchLater;
    }

    private ArrayList<Content> getTopContentForUser() {
        ArrayList<Content> topContent = new ArrayList<>();
        int allCounter = 0;
        int counter = 0;
        for (Channel channel : topChannelsForUser()) {
            for (Content content : topContentForUser(channel)) {
                if (counter < 2 && allCounter < 10) {
                    topContent.add(content);
                    counter++;
                    allCounter++;
                } else {
                    counter = 0;
                    break;
                }
            }
            if (allCounter >= 10) {
                return topContent;
            }
        }
        return topContent;
    }
    public ArrayList<Content> getTopContents(){
        ArrayList<Content> topContent = new ArrayList<>();
        int allCounter = 0;
        int counter = 0;
        for (Channel channel : topChannels()) {
            for (Content content : topContent()) {
                if (counter < 2 && allCounter < 10) {
                    topContent.add(content);
                    counter++;
                    allCounter++;
                } else {
                    counter = 0;
                    break;
                }
            }
            if (allCounter >= 10) {
                return topContent;
            }
        }
        return topContent;
    }
    private ArrayList<Channel> topChannelsForUser() {
        ArrayList<Channel> topChannels;
        if (UserController.getInstance().getSubscriptions().isEmpty()){
            return new ArrayList<>();
        }
        topChannels = UserController.getInstance().getSubscriptions().stream().sorted((c1, c2) -> c2.getSubscribers().size() - c1.getSubscribers().size()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return topChannels;
    }

    private ArrayList<Channel> topChannels() {
        ArrayList<Channel> topChannels;
        if (DataBaseController.getChannels().isEmpty()) {
            return new ArrayList<>();
        }
        topChannels = DataBaseController.getChannels().stream().sorted((c1, c2) -> c2.getSubscribers().size() - c1.getSubscribers().size()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return topChannels;
    }

    private ArrayList<Content> topContentForUser(Channel channel) {
        ArrayList<Content> topContent;
        ArrayList<Content> contents = new ArrayList<>();
        if (DataBaseController.getContents().isEmpty()) {
            return contents;
        }
        for (int id : channel.getContentId()) {
            if (!isWatchedBefore(id, UserController.getInstance().getWatchedContent())) {
                contents.add(DataBaseController.getContentById(id));
            }
        }
        topContent = contents.stream().sorted((c1, c2) -> c2.getLikeCount() - c1.getLikeCount()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return topContent;
    }

    private ArrayList<Content> topContent() {
        ArrayList<Content> topContent;
        if (DataBaseController.getContents().isEmpty()) {
            return new ArrayList<>();
        }
        topContent = DataBaseController.getContents().stream().sorted((c1, c2) -> c2.getLikeCount() - c1.getLikeCount()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return topContent;
    }

    private Boolean isWatchedBefore(int contentId, ArrayList<Integer> watchedContent) {
        return watchedContent.contains(contentId);
    }

    private ArrayList<Content> addToListWithoutDuplicates(ArrayList<Content> list1, ArrayList<Content> list2) {
        for (Content content : list2) {
            if (!list1.contains(content)) {
                list1.add(content);
            }
        }
        return list1;
    }

    public ArrayList<Content> offeredContent() {
        ArrayList<Content> offeredContent = new ArrayList<>();
        ArrayList<Content> liveStreams = getLiveStreams();
        ArrayList<Content> newVideos = getNewVideos();
        ArrayList<Content> newVideosOnCategory = getNewVideosOnCategory();
        ArrayList<Content> oldVideos = getOldVideos();
        ArrayList<Content> topContent = getTopContentForUser();
        ArrayList<Content> watchLater = getWatchLater();
        if (!liveStreams.isEmpty()) {
            addToListWithoutDuplicates(offeredContent, liveStreams);
        }
        if (!newVideos.isEmpty()) {
            addToListWithoutDuplicates(offeredContent, newVideos);
        }
        if (!newVideosOnCategory.isEmpty()) {
            addToListWithoutDuplicates(offeredContent, newVideosOnCategory);
        }
        if (!oldVideos.isEmpty()) {
            addToListWithoutDuplicates(offeredContent, oldVideos);
        }
        if (!topContent.isEmpty()) {
            addToListWithoutDuplicates(offeredContent, topContent);
        }
        if (!watchLater.isEmpty()) {
            addToListWithoutDuplicates(offeredContent, watchLater);
        }
        if (offeredContent.size() < 10) {
            int counter = 0;
            for (Content content : DataBaseController.getContents()) {
                if (counter < 10) {
                    offeredContent.add(content);
                }
            }
        }
        return offeredContent;
    }

    public ArrayList<Content> sortedContents(int type) {
        StringBuilder result = new StringBuilder();
        ArrayList<Content> content = DataBaseController.contentSorter(type);
        return content;
    }

    public ArrayList<Content> getContents() {
        return DataBaseController.getContents();
    }
}
