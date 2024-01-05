package be.kdg.project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "VIDEOS")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "views")
    @PositiveOrZero(message = "Views must be a non-negative number")
    private int views;
    @Column(name = "link")
    private String link;
    @Column(name = "genre")
    @Enumerated(EnumType. STRING)
    private VideoGenre genre;
    @Column(name = "channel_id")
    private int channel;
    @ManyToMany
    @JoinTable(
            name = "CHANNEL_VIDEO_RELATION",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    private List<Channel> channels;
    public Video(int id, String title, int views, String link, VideoGenre genre, int channel) {
        this.id = id;
        this.title = title;
        this.views = views;
        this.link = link;
        this.genre = genre;
        this.channel = channel;
    }

    public Video(int id, String title, int views, String link, VideoGenre genre) {
        this.id = id;
        this.title = title;
        this.views = views;
        this.link = link;
        this.genre = genre;
    }

    public Video(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Video() {
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

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

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
