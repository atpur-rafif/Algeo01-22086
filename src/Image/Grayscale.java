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

        int i, j;
        for(i = 0; i < height; ++i){
            for(j = 0; j < width; ++j){
                this.pixelValue[i][j] = 0;
            }
        }
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
                if(t < 0){
                    System.out.println(t);
                    t = 0;
                } else if(t > 255){
                    System.out.println(t);
                    t = 255;
                }
                Color c = new Color(t, t, t);
                buff.setRGB(j, i, c.getRGB());
            }
        }

        return buff;
    }

    private int getPixel(int x, int y) {
        return this.pixelValue[y][x];
    }

    public int getPixelCartesian(int x, int y){
        if(x < 0) x = 0;
        else if(x >= this.width) x = this.width - 1;

        if(y < 0) y = 0;
        else if(y >= this.height) y = this.height - 1;

        return this.pixelValue[(this.height - 1) - y][x];
    }

    public void setPixelCartesian(int x, int y, int value){
        this.pixelValue[(this.height - 1) - y][x] = value;
    }
}
