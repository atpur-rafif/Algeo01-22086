package Image;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Grayscale {
    private int[][] pixelValue;
    public int height;
    public int width;

    Grayscale(int width, int height){
        this.width = width;
        this.height = height;
        this.pixelValue = new int[height][width];
    }

    public static Grayscale fromImage(BufferedImage buff){
        var image = new Grayscale(buff.getWidth(), buff.getHeight());

        int i, j;
        for(i = 0; i < image.height; ++i){
            for(j = 0; j < image.width; ++j){
                Color c = new Color(buff.getRGB(j, i));
                image.pixelValue[i][j] = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
            }
        }

        return image;
    }

    public static BufferedImage toImage(Grayscale image) {
        var buff = new BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB);

        int i, j;
        for (i = 0; i < image.height; ++i) {
            for (j = 0; j < image.width; ++j) {
                int t = image.getPixel(j, i);
                Color c = new Color(t, t, t);
                buff.setRGB(j, i, c.getRGB());
            }
        }

        return buff;
    }

    public int getPixel(int x, int y) {
        return this.pixelValue[y][x];
    }
}
