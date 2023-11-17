package be.kdg.project.repository;

import be.kdg.project.domain.Channel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChannelRepository {
    public List<Channel> readChannels() {
        return DataFactory.getChannels();
    }

    public Channel createChannel(Channel channel) {
        DataFactory.getChannels().add(channel);
        return channel;
    }
}
