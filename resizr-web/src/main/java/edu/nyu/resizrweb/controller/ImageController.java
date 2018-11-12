package edu.nyu.resizrweb.controller;

import edu.nyu.resizrweb.dto.SuccessMessageDto;
import edu.nyu.resizrweb.entity.User;
import edu.nyu.resizrweb.io.impl.ImageIOHelper;
import edu.nyu.resizrweb.util.ImageUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageIOHelper imageIOHelper;

    @Autowired
    private ImageUtil imageUtil;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadImage(@RequestParam(value = "image", required = false) MultipartFile zoneImage) {
        log.trace("Entered upload image endpoint");
        User user = new User();
        if(zoneImage != null) {
            try {
                String fileName = imageUtil.fileNameFor(user);
                zoneImage.transferTo(imageIOHelper.createFile(fileName));
                String url = imageUtil.urlForImage(fileName);
                // TODO: Set url to image object
            } catch (IOException e) {
                // TODO: Handle error
                log.error("File error: " + e);
            }
        }
        return ResponseEntity.ok(new SuccessMessageDto("Success"));
    }
}
