package be.kdg.project.repository;

import be.kdg.project.domain.Channel;

import java.util.List;

public interface ChannelRepositoryJdbc {
    List<Channel> showAllChannels();
    Channel createChannel(Channel channel);
    List<Channel> findByName(String name);
    Channel findById(int id);
    Channel save(Channel channel);
}
