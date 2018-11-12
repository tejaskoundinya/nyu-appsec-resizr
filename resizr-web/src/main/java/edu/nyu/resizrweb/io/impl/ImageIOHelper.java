package edu.nyu.resizrweb.io.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class ImageIOHelper {
    @Value("${edu.nyu.resizr.image.zone.path}")
    private String basePath;

    public File readFile(String fileName) {
        File file = new File(filePath(fileName));
        if(file.exists() && file.isFile()) {
            return file;
        }
        return null;
    }

    public File writeFile(String fileName, String data) {
        File file = new File(filePath(fileName));
        if(file.exists() && file.isFile()) {
            try {
                FileWriter fr = new FileWriter(file);
                fr.write(data);
                fr.close();
                return file;
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    public File createFile(String fileName) {
        return new File(filePath(fileName));
    }

    public String filePath(String fileName) {
        String filePath = basePath + "/";
        filePath += fileName;
        return filePath;
    }
}
