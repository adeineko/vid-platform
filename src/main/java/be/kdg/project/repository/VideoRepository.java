package be.kdg.project.repository;

import be.kdg.project.domain.Video;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class VideoRepository {
    public List<Video> readVideos() {
        return DataFactory.getVideos();
    }
}
