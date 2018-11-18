package edu.nyu.resizrweb.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j
public class ResizrErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(Model model) {
        log.trace("Entered /error endpoint");
        model.addAttribute("message", "There was an error");
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
