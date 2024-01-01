package be.kdg.project.controllers;

import be.kdg.project.domain.Channel;
import be.kdg.project.services.ChannelServiceImpl;
import be.kdg.project.services.ChannelServiceJdbcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/channels")
public class ChannelController {
    private final ChannelServiceJdbcImpl ChannelServiceJdbcImpl;

    @Autowired
    public ChannelController(ChannelServiceJdbcImpl channelServiceJdbcImpl) {
        this.ChannelServiceJdbcImpl = channelServiceJdbcImpl;
    }

    @GetMapping
    public String getChannelsPage(Model model) {
        model.addAttribute("channels", ChannelServiceJdbcImpl.showChannels());
        return "channel/Channels";
    }

    @GetMapping("/add")
    public String getChannelPage(Model model) {
        model.addAttribute("getPage", new Channel());
        return "channel/AddChannel";
    }

    @PostMapping("create")
    public String addChannel(@ModelAttribute Channel channel, Model model) {
        ChannelServiceJdbcImpl.addChannel(channel);
        model.addAttribute("addChannel", new Channel());
        model.addAttribute("message", "Channel added!");
        return "channel/AddChannel";
    }
}
