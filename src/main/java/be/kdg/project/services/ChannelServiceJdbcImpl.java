package be.kdg.project.services;

import be.kdg.project.domain.Channel;
import be.kdg.project.repository.ChannelRepository;
import be.kdg.project.repository.ChannelRepositoryJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceJdbcImpl implements ChannelServiceJdbc {
    private final ChannelRepositoryJdbc channelRepositoryJdbc;

    @Autowired
    public ChannelServiceJdbcImpl(ChannelRepositoryJdbc channelRepositoryJdbc) {
        this.channelRepositoryJdbc = channelRepositoryJdbc;
    }

    @Override
    public List<Channel> showChannels() {
        return channelRepositoryJdbc.showAllChannels();
    }

    @Override
    public Channel addChannel(Channel channel) {
        return channelRepositoryJdbc.save(channel);
    }
}
