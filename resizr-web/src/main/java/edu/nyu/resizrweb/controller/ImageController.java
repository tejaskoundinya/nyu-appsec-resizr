package edu.nyu.resizrweb.controller;

import com.google.common.hash.Hashing;
import edu.nyu.resizrweb.dto.SuccessMessageDto;
import edu.nyu.resizrweb.entity.ImageEntity;
import edu.nyu.resizrweb.entity.User;
import edu.nyu.resizrweb.io.impl.ImageIOHelper;
import edu.nyu.resizrweb.repository.ImageRepository;
import edu.nyu.resizrweb.repository.UserRepository;
import edu.nyu.resizrweb.service.UserService;
import edu.nyu.resizrweb.util.ImageUtil;
import edu.nyu.resizrweb.util.Resizer;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Log4j
@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageIOHelper imageIOHelper;

    @Autowired
    private ImageUtil imageUtil;

    @Autowired
    private Resizer resizer;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadImage(@RequestParam(value = "width") Integer width, @RequestParam(value = "image", required = true) MultipartFile image, Model model) {
        log.trace("Entered /image/upload endpoint");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if (width <= 0) {
            log.error("User with username: " + user.getUsername() + " tried to resize image to 0 pixels");
            model.addAttribute("message", "Please enter a number greater than 0 as width of the resized image");
            return "error";
        }
        if(image != null) {
            if (!image.getContentType().equalsIgnoreCase("image/jpeg") && !image.getContentType().equalsIgnoreCase("image/png")) {
                log.error("User with username: " + user.getUsername() + " uploaded a non image file");
                model.addAttribute("message", "The file you uploaded it not an image. Please try again with an image file.");
                return "error";
            }
            try {
                String fileName = imageUtil.fileNameFor(user);
                String[] split = fileName.split("/");
                String name = split[split.length - 1];
                String resizedFileName = name.replaceAll("^", "resized_");
                String ext = resizedFileName.split("[.]")[resizedFileName.split("[.]").length - 1];
                String resizedHash = Hashing.sha256()
                        .hashString(resizedFileName, StandardCharsets.UTF_8)
                        .toString();
                log.debug("Trying to resize image to size: " + width + " x " + width);
                resizer.resize(image.getInputStream(), resizedHash + "." + ext, width);
                String originalHash = Hashing.sha256()
                        .hashString(fileName, StandardCharsets.UTF_8)
                        .toString();
                image.transferTo(imageIOHelper.createFile(originalHash + "." + ext));
                log.debug("Image resized and moved to image repository");

                String uploadUrl = imageUtil.urlForImage(originalHash + "." + ext);
                String resizedUrl = imageUtil.urlForImage(resizedHash + "." + ext);
                ImageEntity imageEntity = new ImageEntity();
                imageEntity.setUser(user);
                imageEntity.setUploadUrl(uploadUrl);
                imageEntity.setResizedUrl(resizedUrl);
                imageEntity.setUploadTime(new Date());
                imageEntity.setWidth(width);
                imageEntity = imageRepository.save(imageEntity);
                log.info("Image uploaded with ID: " + imageEntity.getId() + " by user: " + user.getUsername());
            } catch (IOException e) {
                // TODO: Handle error
                log.error("File upload error: " + e + " by user: " + user.getUsername());
                model.addAttribute("message", "There was an error with reading the file");
                return "error";
            }
        } else {
            log.error("User " + user.getUsername() + " tried to upload with not image selected");
            model.addAttribute("message", "No image uploaded");
            return "error";
        }
        return "redirect:/dashboard";
    }
}
