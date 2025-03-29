package Model.Content;

import Model.Account.Category;

import java.util.Date;

public class Video extends Content {
    private String subtitle;
    public Video(int ownerId ,int channelId ,String contentName, Boolean isExlusive, String contentDescription, int playTime, int viewCount, int likeCount, Category category, String contentPath, String cover, String subtitle) {
        super(ownerId,channelId,contentName, isExlusive, contentDescription, playTime, viewCount, likeCount, category, contentPath, cover);
        this.subtitle = subtitle;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
enum Quality {
    P360, P720, P1080;}
enum Format {
    MP4, MKV, MOV, WMV;}
class ShortVideo extends Video {
    private String musicSource;

    public ShortVideo(int ownerId ,int channelId ,String contentName, Boolean isExlusive, String contentDescription, int playTime, int viewCount, int likeCount, Category category, String contentPath, String cover, String subtitle, String musicSource) {
        super(ownerId,channelId,contentName, isExlusive, contentDescription, playTime, viewCount, likeCount, category, contentPath, cover,subtitle);
        this.musicSource = musicSource;
    }
    public String getMusicSource() {
        return musicSource;
    }
    public void setMusicSource(String musicSource) {
        this.musicSource = musicSource;
    }
}
class NormalVideo extends Video {
    private Quality quality;
    private Format format;

    public NormalVideo(int ownerId ,int channelId ,String contentName, Boolean isExlusive, String contentDescription, int playTime, int viewCount, int likeCount, Category category, String contentPath, String cover, String subtitle, Quality quality, Format format) {
        super(ownerId,channelId,contentName, isExlusive, contentDescription, playTime, viewCount, likeCount, category, contentPath, cover,subtitle);
        this.quality = quality;
        this.format = format;
    }
    public Quality getQuality() {
        return quality;
    }
    public void setQuality(Quality quality) {
        this.quality = quality;
    }
    public Format getFormat() {
        return format;
    }
    public void setFormat(Format format) {
        this.format = format;
    }
}
class LiveStream extends Video {
    private Date startTime;
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public LiveStream(int ownerId ,int channelId ,String contentName, Boolean isExlusive, String contentDescription, int playTime, int viewCount, int likeCount, Category category, String contentPath, String cover, String subtitle, Date startTime) {
        super(ownerId,channelId,contentName, isExlusive, contentDescription, playTime, viewCount, likeCount, category, contentPath, cover,subtitle);
        this.startTime = startTime;
    }
}
