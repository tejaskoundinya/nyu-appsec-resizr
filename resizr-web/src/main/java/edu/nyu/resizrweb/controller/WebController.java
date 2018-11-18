package edu.nyu.resizrweb.controller;

import edu.nyu.resizrweb.dto.RegistrationDto;
import edu.nyu.resizrweb.entity.ImageEntity;
import edu.nyu.resizrweb.entity.Role;
import edu.nyu.resizrweb.entity.User;
import edu.nyu.resizrweb.repository.ImageRepository;
import edu.nyu.resizrweb.repository.RoleRepository;
import edu.nyu.resizrweb.repository.UserRepository;
import edu.nyu.resizrweb.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Parts of jsp code borrowed from https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-jsp/
 */
@Log4j
@Controller
public class WebController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping("/")
    public String home(Map<String, Object> model) {
        log.trace("Entered / endpoint");
        return "redirect:/dashboard";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        log.trace("Entered /login endpoint");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping("/upload")
    public String uploadImage(Map<String, Object> model) {
        log.trace("Entered /upload endpoint");
        return "upload";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
        log.trace("Entered /register endpoint");
        model.addAttribute("userForm", new User());

        return "register";
    }

    @RequestMapping(value = "/processregistration", method = RequestMethod.POST)
    public String registration(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        log.trace("Entered /processregistration endpoint");
        log.debug("New user registration request with data: " + username);
        User user = new User();
        if (userRepository.findByUsername(username) == null) {
            user.setUsername(username);
            user.setPassword(password);
            user.setCreatedTime(new Date());
            user.setEnabled(1);
            userService.save(user);
        } else {
            log.debug("Trying to register user with username \'" + username + "\', which already exists. Aborting.");
            return "register";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String showHistory(Model model) {
        log.trace("Entered /history endpoint");
        User user = userService.findByUsername("test");
        List<ImageEntity> imageEntities = imageRepository.findAllByUser(user);
        model.addAttribute("images", imageEntities);
        return "history";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String showDashboard() {
        log.trace("Entered /dashboard endpoint");
        return "dashboard";
    }
}
