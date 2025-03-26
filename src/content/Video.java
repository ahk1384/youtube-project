package content;

import java.util.Date;

public class Video extends Content {
    private String subtitle;
}
enum Quality {
    P360, P720, P1080;}
enum Format {
    MP4, MKV, MOV, WMV;}
class ShortVideo extends Video {
    private String musicSource;
}
class NormalVideo extends Video {
    private Quality quality;
    private Format format;
}
class LiveStream extends Video {
    private Date startTime;
}
