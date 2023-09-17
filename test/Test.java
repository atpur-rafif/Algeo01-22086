import Matrix.*;

import Application.*;
import Matrix.*;
import Image.ImageLoader;
import Image.ImageSaver;
import Image.Resize;
import Image.ResizingMatrix;

public class Test {
    public static void main(String[] args){
        var image = ImageLoader.load("./tmp/Image.jpeg");
        var resized = Resize.resize(image, 5);
        ImageSaver.save(resized, "./tmp/Image-5x.jpeg");
    }
}