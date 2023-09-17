package Image;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Grayscale {
    private int[][] pixelValue;
    public int height;
    public int width;

    Grayscale(BufferedImage buff){
        this.height = buff.getHeight();
        this.width = buff.getWidth();
        this.pixelValue = new int[height][width];

        int i, j;
        for(i = 0; i < this.height; ++i){
            for(j = 0; j < this.width; ++j){
                Color c = new Color(buff.getRGB(j, i));
                this.pixelValue[i][j] = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
            }
        }
    }

    BufferedImage toImage() {
        var buff = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);

        int i, j;
        for (i = 0; i < this.height; ++i) {
            for (j = 0; j < this.width; ++j) {
                int t = this.getPixel(j, i);
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
