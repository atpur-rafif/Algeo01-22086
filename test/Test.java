import Matrix.*;

import Application.*;
import Matrix.*;
import Point.*;
import Image.*;

public class Test {
    public static void main(String[] args){
        var image = ImageLoader.load("./tmp/debug.png");
        var resized = Resize.resize(image, 10);
        ImageSaver.save(resized, "./tmp/debug-10x.png");
    }
}