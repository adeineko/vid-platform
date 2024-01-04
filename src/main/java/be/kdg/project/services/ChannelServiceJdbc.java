package be.kdg.project.services;

import be.kdg.project.domain.Channel;

import java.util.List;

public interface ChannelServiceJdbc {

    public List<Channel> showChannels();
    public Channel addChannel(Channel channel);
    public Channel deleteChannel(Long id);
    public Channel getChannel(Long id);
}
