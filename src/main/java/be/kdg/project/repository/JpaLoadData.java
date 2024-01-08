//package be.kdg.project.repository;
//
//import be.kdg.project.domain.Channel;
//import be.kdg.project.domain.Video;
//import be.kdg.project.domain.VideoGenre;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.time.LocalDate;
//
//public class JpaLoadData {
//    private final ChannelRepositoryJpa channelRepository;
//    private final VideoRepositoryJpa videoRepository;
//
//    @Autowired
//    public JpaLoadData(ChannelRepositoryJpa channelRepository, VideoRepositoryJpa videoRepository) {
//        this.channelRepository = channelRepository;
//        this.videoRepository = videoRepository;
//    }
//
//    @PostConstruct
//    public void loadData() {
//        // Load channels
//        Channel channel1 = new Channel("Beyond Fireship", LocalDate.of(2022, 9, 4), 329000);
//        Channel channel2 = new Channel("Tech Acad", LocalDate.of(2017, 3, 28), 75000);
//
//        channelRepository.save(channel1);
//        channelRepository.save(channel2);
//
//        // Load videos
//        Video video1 = new Video("Next.js 13-The Basics", 512000, "https://youtu.be/__mSgDEOyv8?si=PzO5bV1nShiWz30p", VideoGenre.Educational);
//        Video video2 = new Video("Time is Relative, even in JS", 96000, "https://youtu.be/acemrBKuDqw?si=0pDWXFSWkD6oMq0A", VideoGenre.Educational);
//
//        videoRepository.save(video1);
//        videoRepository.save(video2);
//
//        // Establish relations between channels and videos
//        channel1.getVideos().add(video1);
//        channel1.getVideos().add(video2);
//        channel2.getVideos().add(video1);
//
//        channelRepository.save(channel1);
//        channelRepository.save(channel2);
//    }
//}
