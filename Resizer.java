import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Class that contains methods to resize an image
 */
public class Resizer {
    /**
     * A public method that resizes an image in the sourcePath and writes it into destinationPath
     * with size n x n where n is height or the width of the source image, whichever is smaller
     * @param sourcePath The path of the source image to be resized
     * @param destinationPath The destination of the resized image
     * @return A message of success or failure
     */
    public String resize(String sourcePath, String destinationPath) {
        BufferedImage sourceImage = null;
        int imageType;
        try {
            File sourceFile = new File(getClass().getResource(sourcePath).toURI());
            sourceImage = ImageIO.read(sourceFile);
            imageType = sourceImage.getType();
            if (imageType == 0) {
                imageType = BufferedImage.TYPE_INT_ARGB;
            }
        } catch (Exception e) {
            return "Error in loading the image";
        }
        int shortest = (sourceImage.getHeight() <= sourceImage.getWidth()) ? sourceImage.getHeight() : sourceImage.getWidth();
        BufferedImage destinationImage = resize(sourceImage, imageType, shortest);
        try {
            ImageIO.write(destinationImage, "png", new File(destinationPath));
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
     * The main method
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please use the following format to resize images:");
            System.out.println("java Resizer <source path> <destination path>");
            return;
        }
        Resizer resizer = new Resizer();
        String message = resizer.resize(args[0], args[1]);
        System.out.println(message);
    }
}
