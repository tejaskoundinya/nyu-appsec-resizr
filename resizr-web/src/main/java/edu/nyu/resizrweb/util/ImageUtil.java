package edu.nyu.resizrweb.util;

import edu.nyu.resizrweb.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageUtil {
    private String separator = "_";
    private String extension = ".jpg";

    @Value("${edu.nyu.resizr.image.zone.urlPath}")
    private String basePath;

    @Value("${edu.nyu.resizr.ip}")
    private String ipAddress;

    public String fileNameFor(User user) {
        String fileName = "";
        fileName += user.getUsername() + separator;
        fileName += System.currentTimeMillis();
        fileName += extension;
        return fileName;
    }

    public String urlForImage(String fileName) {
        String url = "http://";
        url += ipAddress;
        url += basePath;
        url += "/" + fileName;
        return url;
    }
}