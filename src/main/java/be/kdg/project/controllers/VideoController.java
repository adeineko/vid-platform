package be.kdg.project.controllers;

import be.kdg.project.domain.Video;
import be.kdg.project.services.ChannelServiceJdbcImpl;
import be.kdg.project.services.VideoServiceJdbc;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/videos")
public class VideoController {
    private final VideoServiceJdbc videoServiceJdbc;
    private final ChannelServiceJdbcImpl channelServiceJdbc;


    @Autowired
    public VideoController(VideoServiceJdbc videoServiceJdbc, ChannelServiceJdbcImpl channelServiceJdbc) {
        this.videoServiceJdbc = videoServiceJdbc;
        this.channelServiceJdbc = channelServiceJdbc;
    }

    @GetMapping
    public String getVideosPage(Model model) {
        model.addAttribute("video", videoServiceJdbc.showVideos());
        return "video/Videos";
    }

    @GetMapping("/add")
    public String getVideoPage(Model model) {
        model.addAttribute("getPage", new Video());
        model.addAttribute("channels", channelServiceJdbc.showChannels());
        return "video/AddVideos";
    }

    @PostMapping("create")
    public String addVideo(@Valid @ModelAttribute Video video, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("channels", channelServiceJdbc.showChannels());
            model.addAttribute("getPage", new Video());
            model.addAttribute("message2", errors.getAllErrors().stream().findFirst().orElse(null).getDefaultMessage());
            return "video/AddVideos";
        }
        videoServiceJdbc.addVideo(video);
//        videoServiceJdbc.saveChannelId(video);
        model.addAttribute("channels", channelServiceJdbc.showChannels());
        model.addAttribute("getPage", new Video());
        model.addAttribute("message", "Video added!");
        return "video/AddVideos";
    }

    @GetMapping("/{id}")
    public String getVideo(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("video", videoServiceJdbc.getVideo(id));
        return "video/details";
    }

    @PostMapping("delete/{id}")
    public String deleteChannel(@PathVariable(value = "id") Long id) {
        try {
            videoServiceJdbc.deleteVideo(id);
            return "redirect:/videos";
        } catch (ResponseStatusException e) {
            // case where the entity with the specified ID doesn't exist
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return "redirect:/videos?error=notfound";
            }
            return "redirect:/videos?error=server";
        }
    }
}
