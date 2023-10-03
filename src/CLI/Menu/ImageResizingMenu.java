package CLI.Menu;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import CLI.IO.IONavigator;
import CLI.IO.IOPrompter;
import Image.Grayscale;
import Image.ImageLoader;
import Image.ImageSaver;
import Image.Resize;

public class ImageResizingMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void Run(){
        IONavigator.next("Image Resizing");

        var imagePath = IOPrompter.get("Masukkan path gambar: ", (var p) -> {
            if(!(new File(p)).exists()) return null;
            else return p;
        }, (var t) -> {
            return "File tidak ditemukan";
        });


        var scale = IOPrompter.get("Masukkan skala gambar: ", (var i) -> {
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
            savePath = IOPrompter.getString("Masukkan path: ");
            if(Files.exists(Path.of(savePath))){
                IOPrompter.printMultiLine(new String[]{
                    "File sudah ada, lakukan: ",
                    "1. Overwrite",
                    "2. Masukan ulang path",
                });
                var b = IOPrompter.getBoundedInt("", 1, 2);

                if(b == 1) break;
            } else { break; };
        } while(true);

        ImageSaver.save(resized, savePath);

        IONavigator.back();
    }
}

