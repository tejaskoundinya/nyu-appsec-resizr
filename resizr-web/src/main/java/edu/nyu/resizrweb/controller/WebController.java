package edu.nyu.resizrweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Parts of jsp code borrowed from https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-jsp/
 */
@Controller
public class WebController {
    @RequestMapping("/")
    public String home(Map<String, Object> model) {
        return "home";
    }

    @RequestMapping("/login")
    public String login(Map<String, Object> model) {
        return "login";
    }

    @RequestMapping("/upload")
    public String uploadImage(Map<String, Object> model) {
        return "upload";
    }
}
