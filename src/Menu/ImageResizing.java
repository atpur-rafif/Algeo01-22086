package Menu;

import Image.Grayscale;
import Image.ImageLoader;
import Image.ImageSaver;
import Image.Resize;
import java.io.File;
import java.util.Scanner;

public class ImageResizing {
    static Scanner scanner = new Scanner(System.in);
    public static void Display(){
        System.out.println("===================================Menu Image Resizing====================================");
        System.out.print("Masukkan path image: ");
        String pathImage = scanner.next(); 
        File pathExist = new File(pathImage); 
            while (!pathExist.exists()){
                System.out.println("file tidak ada, silahkan ganti namanya");
                System.out.print("Nama file: ");
                pathImage = scanner.next();
                pathExist = new File(pathImage); 
            }
        Grayscale image = ImageLoader.load(pathImage);
        System.out.print("Masukkan scale: ");
        Grayscale resized = Resize.resize(image, Double.parseDouble(scanner.next()));
        String savingPath = scanner.next();
        ImageSaver.save(resized, savingPath);
    }
        // var image = ImageLoader.load("./test/debug.png");
        // var resized = Resize.resize(image, 100);
        // ImageSaver.save(resized, "./debug-res.png");
}

