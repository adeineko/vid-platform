package be.kdg.project.controllers;

import be.kdg.project.domain.Channel;
import be.kdg.project.services.ChannelServiceJdbcImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteChannel(@PathVariable(value = "id") Long id) {
       ChannelServiceJdbcImpl.deleteChannel(id);
//        model.addAttribute("channels", deletedChannel);
//        model.addAttribute("channels", "Channel deleted!");
//        if (deletedChannel != null) {
//            model.addAttribute("message", "Channel deleted!");
//        } else {
//            model.addAttribute("message", "Channel not found or unable to delete.");
//        }

        // Redirect to the channels page after deletion
        return new ResponseEntity<>(HttpStatus.OK);
//        return "channel/Channels";
    }

    @GetMapping("/{id}")
    public String getChannel(Model model, @PathVariable(value = "id") Long id) {
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
//            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
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
