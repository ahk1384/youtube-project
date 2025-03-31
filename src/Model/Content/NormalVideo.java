package Model.Content;

import Model.Account.Category;


public class NormalVideo extends Video {
    private Quality quality;
    private Format format;

    public NormalVideo(int ownerId, int channelId, String contentName, Boolean isExlusive, String contentDescription, int playTime, Category category, String contentPath, String cover, String subtitle, Quality quality, Format format) {
        super(ownerId, channelId, contentName, isExlusive, contentDescription, playTime, category, contentPath, cover, subtitle);
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

