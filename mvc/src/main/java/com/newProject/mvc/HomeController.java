package com.newProject.mvc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class HomeController {
    private final VideoService videoService;

// This is basically Dependency Injection but without using @Autowired.
    public HomeController(VideoService videoService){
        this.videoService = videoService;
    }

    //Spring MVC Model provides this object to pass data from the controller to the view (HTML template).
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("videos", videoService.getVideos());
        return "index";
    }
    @PostMapping("/new-video")
    //@ModelAttribute: Spring MVC’s annotation to parse an incoming HTML form an unpack it into a Video object.
    public String newVideo(@ModelAttribute Video newVideo){
        videoService.create(newVideo);
        return "redirect:/";
        //"redirect:/": Spring MVC directive that sends the browser an HTTP 302 Found to URL /. A 302 redirect is the
        // standard for a soft redirect. (301 is a permanent redirect, instructing the browser to not try the original path again.)
    }

    @PostMapping("/multi-field-search")
    public String multiFieldSearch(@ModelAttribute VideoSearch search, Model model){
        List<VideoEntity> searchResults = videoService.search(search);
        model.addAttribute("videos", searchResults);
        return "index";
    }

    @PostMapping("/universal-search")
    public String universalSearch(@ModelAttribute UniversalSearch search, Model model){
        List<VideoEntity> searchResult = videoService.search(search);
        model.addAttribute("videos", searchResult);
        return "index";
    }


//    @GetMapping("/react")
//    public String react() {
//        return "react";
//    }
}


