package Image;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class ImageLoader {
    static Scanner scanner = new Scanner(System.in);
    public static Grayscale load(String path){
        BufferedImage buff = null;
        try {
            buff = ImageIO.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Grayscale.fromImage(buff);
    }
}
