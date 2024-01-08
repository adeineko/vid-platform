package be.kdg.project.repository;

import be.kdg.project.domain.Channel;
import be.kdg.project.domain.Video;

import java.util.List;

public interface VideoRepositoryJdbc {
    List<Video> showAllVideos();
    List<Video> findByName(String name);
    Video findById(Long id);
    Video save(Video video);
    Video deleteById(Long id);
//    Video saveChId(Video video);
}
