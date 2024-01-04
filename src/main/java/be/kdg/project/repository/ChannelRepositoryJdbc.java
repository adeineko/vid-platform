package be.kdg.project.repository;

import be.kdg.project.domain.Channel;
import be.kdg.project.domain.Video;

import java.util.List;

public interface ChannelRepositoryJdbc {
    List<Channel> showAllChannels();
    List<Video> showVideosByChannelId(Long channelId);
    List<Channel> findByName(String name);
    Channel findById(Long id);
    Channel save(Channel channel);
    Channel deleteById(Long id);
}
