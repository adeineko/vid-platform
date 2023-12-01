package be.kdg.project.services;

import be.kdg.project.domain.Channel;
import be.kdg.project.repository.ChannelRepository;
import be.kdg.project.repository.JSonWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;
    private JSonWriter jSonWriter;


    @Autowired
    public ChannelServiceImpl(ChannelRepository channelRepository, JSonWriter jSonWriter) {
        this.channelRepository = channelRepository;
        this.jSonWriter = jSonWriter;
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

    @Override
    public boolean deleteChannel(String name) {
        return channelRepository.deleteChannel(name);
    }

    @Override
    public Channel updateChannel(Channel channel) {
        return channelRepository.updateChannel(channel);
    }

    @Override
    public void writeChannelsToJSON(List<Channel> channels) {
        jSonWriter.writeChannels(channels);
    }
}
