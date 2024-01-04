package be.kdg.project.repository;

import be.kdg.project.domain.Channel;
import be.kdg.project.domain.Comment;
import be.kdg.project.domain.Video;
import be.kdg.project.domain.VideoGenre;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataFactory {
    private static List<Video> videos;
    private static List<Comment> comments;
    private static List<Channel> channels;

    public static void seed() {
        Channel channel1 = new Channel(1, "Channel 1", LocalDate.now(), 10);
        Channel channel2 = new Channel(2, "Channel 2", LocalDate.now(), 10);
        Channel channel3 = new Channel(3, "Channel 3", LocalDate.now(), 10);
        Channel channel4 = new Channel(4, "Channel 4", LocalDate.now(), 10);

  //      Video video1 = new Video(1, "Video 1", 100, "link1", VideoGenre.Action, Channel channel);
//        video1.addChannel(channel1);
//        video1.addChannel(channel4);
//        Video video2 = new Video(2, "Video 1", 100, "link2", VideoGenre.Action);
//        video2.addChannel(channel2);
//        video2.addChannel(channel3);
//        Video video3 = new Video(3, "Video 1", 100, "link3", VideoGenre.Action);
//        video3.addChannel(channel3);
//        Video video4 = new Video(4, "Video 1", 100, "link4", VideoGenre.Action);
//        video4.addChannel(channel4);

//        Comment comment1 = new Comment("Comment 1");
//        comment1.setVideo(video1);
//        Comment comment2 = new Comment("Comment 1");
//        comment2.setVideo(video2);
//        Comment comment3 = new Comment("Comment 1");
//        comment3.setVideo(video3);
//        Comment comment4 = new Comment("Comment 1");
//        comment4.setVideo(video4);

        channels.add(channel1);
        channels.add(channel2);
        channels.add(channel3);
        channels.add(channel4);
//
//        videos.add(video1);
//        videos.add(video2);
//        videos.add(video3);
//        videos.add(video4);
//
//        comments.add(comment1);
//        comments.add(comment2);
//        comments.add(comment3);
//        comments.add(comment4);
    }

    static {
        videos = new ArrayList<>();
        comments = new ArrayList<>();
        channels = new ArrayList<>();
        seed();
    }

    public static List<Video> getVideos() {
        return videos;
    }

    public static List<Comment> getComments() {
        return comments;
    }

    public static List<Channel> getChannels() {
        return channels;
    }
}
