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

    public boolean deleteChannel(String name) {
        DataFactory.getChannels().removeIf(c -> c.getName().equals(name));
        return true;
    }

    public Channel updateChannel(Channel channel) {
        List<Channel> channels = DataFactory.getChannels();

        for (int i = 0; i < channels.size(); i++) {
            Channel existingChannel = channels.get(i);
            if (existingChannel.getName().equals(channel.getName())) {
                channels.set(i, channel);
                return channel;
            }
        }
        return null;
    }
}
