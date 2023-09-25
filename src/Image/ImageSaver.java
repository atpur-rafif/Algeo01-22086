package Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageSaver {
    public static void save(Grayscale image, String path){
        var file = new File(path);
        try {
            ImageIO.write(Grayscale.toImage(image), "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
