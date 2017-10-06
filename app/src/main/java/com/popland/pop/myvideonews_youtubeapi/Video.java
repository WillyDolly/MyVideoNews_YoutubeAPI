package com.popland.pop.myvideonews_youtubeapi;

/**
 * Created by hai on 26/07/2016.
 */
public class Video {
    public String title;
    public String publishedAt;
    public String url;
    public String videoId;

    public Video(String title, String publishedAt, String thumbnail, String videoId) {
        this.title = title;
        this.publishedAt = publishedAt;
        this.url = thumbnail;
        this.videoId = videoId;
    }
}
