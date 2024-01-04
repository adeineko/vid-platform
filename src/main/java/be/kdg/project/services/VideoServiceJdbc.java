package be.kdg.project.services;

import be.kdg.project.domain.Channel;
import be.kdg.project.domain.Video;

import java.util.List;

public interface VideoServiceJdbc {
    public List<Video> showVideos();

    public Video addVideo(Video video);

    public Video deleteVideo(Long id);

    public Video getVideo(Long id);
    public Video saveChannelId(Video video);
}
