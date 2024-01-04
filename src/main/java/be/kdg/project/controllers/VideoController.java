package be.kdg.project.controllers;

import be.kdg.project.domain.Channel;
import be.kdg.project.domain.Video;
import be.kdg.project.services.VideoService;
import be.kdg.project.services.VideoServiceJdbc;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/videos")
public class VideoController {
    private final VideoServiceJdbc videoServiceJdbc;

    @Autowired
    public VideoController(VideoServiceJdbc videoServiceJdbc) {
        this.videoServiceJdbc = videoServiceJdbc;
    }

    @GetMapping
    public String getVideosPage(Model model) {
        model.addAttribute("video", videoServiceJdbc.showVideos());
        return "video/Videos";
    }
    @GetMapping("/add")
    public String getVideoPage(Model model) {
        model.addAttribute("getPage", new Video());
        return "video/AddVideos";
    }

    @PostMapping("create")
    public String addVideo(@Valid @ModelAttribute Video video, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("getPage", new Video());
            model.addAttribute("message2", errors.getAllErrors().stream().findFirst().orElse(null).getDefaultMessage());
            return "video/AddVideos";
        }
        videoServiceJdbc.addVideo(video);
        model.addAttribute("getPage", new Video());
        model.addAttribute("message", "Video added!");
        return "video/AddVideos";
    }
}
