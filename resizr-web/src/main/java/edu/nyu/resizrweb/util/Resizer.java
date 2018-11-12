package edu.nyu.resizrweb.util;

import edu.nyu.resizrweb.io.impl.ImageIOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class that contains methods to resize an image
 */
@Component
public class Resizer {
    @Autowired
    private ImageIOHelper imageIOHelper;
    /**
     * A public method that resizes an image in the sourcePath and writes it into destinationPath
     * with size n x n where n is height or the width of the source image, whichever is smaller
     * @param sourceStream The source image
     * @param destinationPath The destination of the resized image
     * @return A message of success or failure
     */
    public String resize(InputStream sourceStream, String destinationPath, Integer width) {
        BufferedImage sourceImage = null;
        int imageType;
        try {
            sourceImage = ImageIO.read(sourceStream);
            imageType = sourceImage.getType();
            if (imageType == 0) {
                imageType = BufferedImage.TYPE_INT_ARGB;
            }
        } catch (Exception e) {
            return "Error in loading the image";
        }
//        int shortest = (sourceImage.getHeight() <= sourceImage.getWidth()) ? sourceImage.getHeight() : sourceImage.getWidth();
        BufferedImage destinationImage = resize(sourceImage, imageType, width);//shortest);
        String targetFormat = getImageExtension(destinationPath);
        try {
            ImageIO.write(destinationImage, targetFormat, imageIOHelper.createFile(destinationPath));
        } catch (IOException e) {
            return e.getMessage();
        }
        return "Image resize successful";
    }

    /**
     * A private method to perform the image resize using ImageIO
     * @param sourceImage The BufferedImage instance of the source image
     * @param imageType The image type
     * @param size The size of the square image after resize
     * @return The resized image
     */
    private BufferedImage resize(BufferedImage sourceImage, int imageType, int size) {
        BufferedImage bufferedImage = new BufferedImage(size, size, imageType);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(sourceImage, 0, 0, size, size, null);
        graphics.dispose();
        return bufferedImage;
    }

    /**
     * Method to get the image extension
     * @param path The path of the destination image
     * @return The extension of the image
     */
    public String getImageExtension(String path) {
        String ext = "png";
        String[] parts = path.split("[.]");
        if (parts.length > 1) {
            switch (parts[parts.length - 1]) {
                case "jpeg":
                    ext = "jpg";
                    break;
                case "jpg":
                    ext = "jpg";
                    break;
                default:
                    ext = "png";
            }
        }
        return ext;
    }
}