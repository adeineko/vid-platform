package be.kdg.project.repository;

import be.kdg.project.domain.Channel;
import be.kdg.project.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepositoryJpa extends JpaRepository<Channel, Long> {
    @Query("SELECT c FROM Channel c")
    List<Channel> showAllChannels();
    @Query("SELECT v FROM Video v INNER JOIN v.channels c WHERE c.id = :channelId")
    List<Video> showVideosByChannelId(Long channelId);

    List<Channel> findByName(String name);

//    Channel findById(Long id);

    @Override
    Channel save(Channel channel);

    @Override
    void deleteById(Long id);

//    @Query("DELETE FROM Video v WHERE v.channels.id = :channelId")
//    void deleteVideosForChannel(Long channelId);
}
