package Model.Content;

import Model.Account.Category;

import java.time.LocalDate;

public class LiveStream extends Video {
    private LocalDate startTime;

    public LiveStream(int ownerId, int channelId, String contentName, Boolean isExlusive, String contentDescription, int playTime, Category category, String contentPath, String cover, String subtitle, LocalDate startTime) {
        super(ownerId, channelId, contentName, isExlusive, contentDescription, playTime, category, contentPath, cover, subtitle);
        this.startTime = startTime;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }
}