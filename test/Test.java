import Image.ImageLoader;
import Image.ImageSaver;
import Image.Resize;
import Matrix.*;
import Matrix.OBE.OBERunner;
import Vector.EuclideanSpace;
import Vector.VectorSpace;

public class Test {
    public static void main(String[] args){
        var image = ImageLoader.load("./test/debug.png");
        var resized = Resize.resize(image, 100);
        ImageSaver.save(resized, "./tmp/debug-res.png");
    }
}