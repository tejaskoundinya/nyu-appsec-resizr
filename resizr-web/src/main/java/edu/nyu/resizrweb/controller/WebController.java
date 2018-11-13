package edu.nyu.resizrweb.controller;

import edu.nyu.resizrweb.dto.RegistrationDto;
import edu.nyu.resizrweb.entity.Role;
import edu.nyu.resizrweb.entity.User;
import edu.nyu.resizrweb.repository.RoleRepository;
import edu.nyu.resizrweb.repository.UserRepository;
import edu.nyu.resizrweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Parts of jsp code borrowed from https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-jsp/
 */
@Controller
public class WebController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

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

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "register";
    }

    @RequestMapping(value = "/processregistration", method = RequestMethod.POST)
    public String registration(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        User user = new User();
        if (userRepository.findByUsername(username) == null) {
            user.setUsername(username);
            user.setPassword(password);
            user.setCreatedTime(new Date());
            userService.save(user);
        } else {
            return "register";
        }
        return "redirect:/";
    }
}
