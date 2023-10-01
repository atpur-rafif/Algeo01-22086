package Menu;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import Image.Grayscale;
import Image.ImageLoader;
import Image.ImageSaver;
import Image.Resize;

public class ImageResizing {
    static Scanner scanner = new Scanner(System.in);
    public static void Run(){
        System.out.println("===================================Menu Image Resizing====================================");
        System.out.print("Masukkan path image: ");
        var imagePath = Prompter.get("Image Path?", (var p) -> {
            if(!(new File(p)).exists()) return null;
            else return p;
        }, (var t) -> {
            return "File tidak ditemukan";
        });


        var scale = Prompter.get("Scaling?", (var i) -> {
            try {
                return Double.parseDouble(i);
            } catch (Exception e) {
                return null;
            }
        }, (var t) -> {
            return "Input tidak valid";
        });

        Grayscale image = ImageLoader.load(imagePath);
        Grayscale resized = Resize.resize(image, scale);

        String savePath = null;
        do{
            savePath = Prompter.getInlineString("Masukkan path: ");
            if(Files.exists(Path.of(savePath))){
                var b = Prompter.getBoundedInt(new String[]{
                    "File sudah ada, lakukan: ",
                    "1. Overwrite",
                    "2. Masukan ulang path",
                }, 1, 2);

                if(b == 1) break;
            } else { break; };
        } while(true);

        ImageSaver.save(resized, savePath);
    }
        // var image = ImageLoader.load("./test/debug.png");
        // var resized = Resize.resize(image, 100);
        // ImageSaver.save(resized, "./debug-res.png");
}

