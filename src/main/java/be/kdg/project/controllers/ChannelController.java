package be.kdg.project.controllers;

import be.kdg.project.domain.Channel;
import be.kdg.project.services.ChannelServiceJdbc;
import be.kdg.project.services.ChannelServiceJdbcImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

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
    @PostMapping("delete/{id}")
    public String deleteChannel(@PathVariable(value = "id") Long id) {
        try {
            ChannelServiceJdbcImpl.deleteChannel(id);
            return "redirect:/channels";
        } catch (ResponseStatusException e) {
            // case where the entity with the specified ID doesn't exist
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return "redirect:/channels?error=notfound";
            }
            return "redirect:/channels?error=server";
        }
    }

    @GetMapping("/{id}")
    public String getChannel(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("videos", ChannelServiceJdbcImpl.findVideosByChannelId(id));
        model.addAttribute("channel", ChannelServiceJdbcImpl.getChannel(id));
        return "channel/details";
    }

    @GetMapping("/add")
    public String getChannelPage(Model model) {
        model.addAttribute("getPage", new Channel());
        return "channel/AddChannel";
    }

    @PostMapping("create")
    public String addChannel(@Valid @ModelAttribute Channel channel, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("getPage", new Channel());
            model.addAttribute("message2", errors.getAllErrors().stream().findFirst().orElse(null).getDefaultMessage());
            return "channel/AddChannel";
        }
        ChannelServiceJdbcImpl.addChannel(channel);
        model.addAttribute("getPage", new Channel());
        model.addAttribute("message", "Channel added!");
        return "channel/AddChannel";
    }
}
