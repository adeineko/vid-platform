package be.kdg.project.services;

import be.kdg.project.domain.Channel;
import be.kdg.project.domain.Video;
import be.kdg.project.repository.VideoRepositoryJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VideoServiceJdbcImpl implements VideoServiceJdbc {
    private final VideoRepositoryJdbc videoRepositoryJdbc;

    @Autowired
    public VideoServiceJdbcImpl(VideoRepositoryJdbc videoRepositoryJdbc) {
        this.videoRepositoryJdbc = videoRepositoryJdbc;
    }

    @Override
    public List<Video> showVideos() {
        return videoRepositoryJdbc.showAllVideos();
    }

    @Override
    public Video addVideo(Video video) {
        return videoRepositoryJdbc.save(video);
    }

    @Override
    public Video deleteVideo(Long id) {
        return videoRepositoryJdbc.deleteById(id);
    }

    @Override
    public Video getVideo(Long id) {
        return videoRepositoryJdbc.findById(id);
    }

    @Override
    public Video saveChannelId(Video video) {
        return videoRepositoryJdbc.saveChId(video);
    }
}
