package be.kdg.project.services;

import be.kdg.project.domain.Channel;

import java.util.List;

public interface ChannelService {
    public List<Channel> getChannels();

    public Channel createChannel(Channel channel);

    boolean deleteChannel(String name);

    public Channel updateChannel(Channel channel);
    public void writeChannelsToJSON(List<Channel> channels);
}
