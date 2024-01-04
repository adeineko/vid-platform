package be.kdg.project.domain;

import jakarta.validation.constraints.PositiveOrZero;

import java.util.ArrayList;
import java.util.List;

public class Video {
    private int id;
    private String title;
    @PositiveOrZero(message = "Views must be a non-negative number")
    private int views;
    private String link;
    private VideoGenre genre;
    private Channel channel;

    public Video(int id, String title, int views, String link, VideoGenre genre, Channel channel) {
        this.id = id;
        this.title = title;
        this.views = views;
        this.link = link;
        this.genre = genre;
        this.channel = channel;
    }

    public Video(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Video() {
    }

    public Video(int id, String title, int views, String link, VideoGenre genre) {
        this.id = id;
        this.title = title;
        this.views = views;
        this.link = link;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public VideoGenre getGenre() {
        return genre;
    }

    public void setGenre(VideoGenre genre) {
        this.genre = genre;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

//    public void addChannel(Channel channel) {
////        if (this.channels == null) {
////            this.channels = new ArrayList<>();
////        }
////        this.channels.add(channel);
//        return channels
//    }

    @Override
    public String toString() {
        return "Video" +
                "title='" + title + '\'' +
                ", views=" + views +
                ", link='" + link + '\'' +
                ", genre=" + genre +
                ", channels=" + channel;
    }
}
