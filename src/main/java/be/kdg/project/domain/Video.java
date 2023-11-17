package be.kdg.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Video {
    private String title;
    private int views;
    private double revenue;
    private VideoGenre genre;
    private List<Channel> channels;

    public Video(String title, int views, double revenue, VideoGenre genre) {
        this.title = title;
        this.views = views;
        this.revenue = revenue;
        this.genre = genre;
        channels = new ArrayList<>();
    }


    public String getTitle() {
        return title;
    }

    public int getViews() {
        return views;
    }

    public double getRevenue() {
        return revenue;
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
        if (this.channels == null){
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
