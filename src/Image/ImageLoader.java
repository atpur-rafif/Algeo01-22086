package Image;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageLoader {
    public static Grayscale load(String path){
        BufferedImage buff = null;
        try {
            buff = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.print(e);
        }

        return new Grayscale(buff);
    }
}
