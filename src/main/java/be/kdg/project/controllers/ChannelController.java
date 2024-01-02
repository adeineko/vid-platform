package be.kdg.project.controllers;

import be.kdg.project.domain.Channel;
import be.kdg.project.services.ChannelServiceImpl;
import be.kdg.project.services.ChannelServiceJdbcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public String getChannel(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("channel", ChannelServiceJdbcImpl.getChannel(id));
        return "channel/details";
    }

    @GetMapping("/add")
    public String getChannelPage(Model model) {
        model.addAttribute("getPage", new Channel());
        System.out.println("sent:" + new Channel());
        return "channel/AddChannel";
    }

    @PostMapping("create")
    public String addChannel(@ModelAttribute Channel channel, Model model) {
        ChannelServiceJdbcImpl.addChannel(channel);
        model.addAttribute("getPage", new Channel());
        System.out.println("get:" + channel);
        model.addAttribute("message", "Channel added!");
        return "channel/AddChannel";
    }

}
