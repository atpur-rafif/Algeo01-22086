import Matrix.*;

import Application.*;
import Matrix.*;
import Image.ImageLoader;
import Image.ImageSaver;
import Image.ResizingMatrix;

public class Test {
    public static void main(String[] args){
        var p = ImageLoader.load("./test/Image.jpeg");
        ImageSaver.save(p, "./tmp/lmao.jpeg");
    }
}