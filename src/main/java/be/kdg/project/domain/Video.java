package be.kdg.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Video {
    private int id;
    private String title;
    private int views;
    private String link;
    private VideoGenre genre;
    private List<Channel> channels;

    public Video(int id, String title, int views, String link, VideoGenre genre) {
        this.id = id;
        this.title = title;
        this.views = views;
        this.link = link;
        this.genre = genre;
        channels = new ArrayList<>();
    }

    public Video() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getViews() {
        return views;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setViews(int views) {
        this.views = views;
    }


    public void setGenre(VideoGenre genre) {
        this.genre = genre;
    }

    public VideoGenre getGenre() {
        return genre;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public void addChannel(Channel channel) {
        if (this.channels == null) {
            this.channels = new ArrayList<>();
        }
        this.channels.add(channel);
    }

    @Override
    public String toString() {
        return "Video " +
                "title='" + title;
    }
}
