import Matrix.*;

import Application.*;
import Image.ImageLoader;
import Image.ImageSaver;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Test {
    public static void main(String[] args){
        var g = ImageLoader.load("./test/Image.jpeg");
        System.out.print(g.height);

        ImageSaver.save(g, "./tmp/Image.jpeg");
    }
}