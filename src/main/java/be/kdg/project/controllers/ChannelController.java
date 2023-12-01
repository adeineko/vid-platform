package be.kdg.project.controllers;

import be.kdg.project.services.ChannelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/channels")
public class ChannelController {
    private final ChannelServiceImpl channelServiceImpl;
    @Autowired
    public ChannelController(ChannelServiceImpl channelServiceImpl) {
        this.channelServiceImpl = channelServiceImpl;
    }

    @GetMapping
    public String getChannelsPage(Model model){
        model.addAttribute("channels", channelServiceImpl.getChannels());
        return "channel/Channels";
    }
}
