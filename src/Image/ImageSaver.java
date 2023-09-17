package Image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageSaver {
    public static void save(Grayscale image, String path){
        var buff = new BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB);
        
        int i, j;
        for(i = 0; i < image.height; ++i){
            for(j = 0; j < image.width; ++j){
                int t = image.getPixel(j, i);
                t = 255 - t;
                Color c = new Color(t, t, t);
                buff.setRGB(j, i, c.getRGB());
            }
        }

        var file = new File(path);
        try {
            ImageIO.write(buff, "jpg", file);
        } catch (IOException e) {
            System.out.print(file);
        }
    }
}
