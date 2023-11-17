package be.kdg.project.controllers;

import be.kdg.project.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/channels")
public class ChannelController {
    private final ChannelService channelService;
    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping
    public String getChannelsPage(Model model){
        model.addAttribute("channels",channelService.getChannels());
        return "channel/Channels";
    }
}
