package be.kdg.project.services;

import be.kdg.project.domain.Channel;
import be.kdg.project.domain.Video;
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

    @Override
    public Channel deleteChannel(Long id) {
        return channelRepositoryJdbc.deleteById(id);
    }

    @Override
    public Channel getChannel(Long id) {
        return channelRepositoryJdbc.findById(id);
    }

    @Override
    public List<Video> findVideosByChannelId(Long channelId) {
        return channelRepositoryJdbc.showVideosByChannelId(channelId);
    }


}
