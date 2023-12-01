package be.kdg.project.services;

import be.kdg.project.domain.Channel;
import be.kdg.project.repository.ChannelRepository;
import be.kdg.project.repository.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {
    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public Channel getChannel(String name) {
        return channelRepository.readChannels().stream().filter(c -> c.getName()
                        .equals(name))
                        .findFirst()
                        .orElse(null);
    }

    public List<Channel> getChannels() {
        return channelRepository.readChannels();
    }

    public Channel createChannel(Channel channel) {
        return channelRepository.createChannel(channel);
    }



}
