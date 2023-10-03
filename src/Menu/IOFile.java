package Menu;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import Vector.EquationSpace;
import Vector.EuclideanSpace;
import FilePrinter.*;
import Matrix.Matrix;

public class IOFile {
    static Scanner scanner = new Scanner(System.in);
    static String choice;

    public static File createFile_v2(Path path) throws IOException{
        var iterator = path.getParent().iterator();
        var currentPath = iterator.next();
        while(iterator.hasNext()){
            currentPath = currentPath.resolve(iterator.next());
            if(!Files.exists(currentPath)){
                try {
                    Files.createDirectories(currentPath);
                } catch (Exception e) {}
            }
        }

        path.toFile().createNewFile();
        return path.toFile();
    }

    public static class ObscureFormat{
        public EuclideanSpace vector;
        public Matrix matrix;
        ObscureFormat(Matrix matrix, EuclideanSpace vector){
            this.matrix = matrix;
            this.vector = vector;
        }
    }

    public static ObscureFormat parseObscureFormat(String str){
        str = str.trim();

        int row = 0;
        int col = -1;

        int cCol = 0;
        boolean finished = false;
        var buffer = new ArrayList<Double>();
        var pattern = Pattern.compile("[0-9|.]");
        String current = "";
        var len = str.length();
        for(int i = 0; i < len; ++i){
            var c = str.charAt(i);

            if(finished) throw new Error("Invalid format");

            if(pattern.matcher(String.valueOf(c)).find()){
                current += c;
            }

            if((c == ' ' || c == '\n' || i == len - 1) && (current.length() != 0)){
                var v = Double.parseDouble(current);
                buffer.add(v);
                current = "";
                cCol += 1;
            }

            if(c == '\n' || i == len - 1){
                if(col == -1){
                    col = cCol;
                }

                if(col == cCol && i != len - 1){
                    col = cCol;
                    cCol = 0;
                    row += 1;
                } else {
                    finished = true;
                }
            }
        }
        System.out.println(row + " " + col);

        var p = 0;
        var m = new Matrix(row, col);
        for(int i = 0; i < row; ++i){
            for(int j = 0; j < col; ++j){
                m.set(i, j, buffer.get(p));
                p += 1;
            }
        }

        var v = new EuclideanSpace(buffer.size() - p);
        for(int i = p; i < buffer.size(); ++i){
            v.set(i - p, buffer.get(i));
        }

        return new ObscureFormat(m, v);
    }

    public static ObscureFormat readObscureFormat(){
        Path path = null;
        do{
            var input = Prompter.getInlineString("Masukkan path: ");
            path = Path.of(input);
            if(Files.exists(path)){
                String str = null;
                try {
                    str = Files.readString(path);
                    return parseObscureFormat(str);
                } catch (IOException e) {
                    System.out.println("Format file tidak sesuai");
                    continue;
                }
            } else System.out.println("File tidak ditemukan");
        } while(true);
    }

    public static void save(Path path, String content) throws IOException{
        var f = createFile_v2(path);
        var w = new FileWriter(f);
        w.write(content);
        w.close();
    }

    public static void askToSave(String content){
        var confirmation = Prompter.getBoolean("Save dalam file");
        if(!confirmation) return;

        Path path = null;

        do {
            var input = Prompter.getInlineString("Masukkan path: ");
            path = Path.of(input);
            if(Files.exists(path)){
                var b = Prompter.getBoundedInt(new String[]{
                    "File sudah ada, lakukan: ",
                    "1. Overwrite",
                    "2. Masukan ulang path",
                    "3. Back"
                }, 1, 3);

                if(b == 1) break;
                if(b == 3) return;
            } else { break; }
        } while (true);

        try {
			save(path, content);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void createFile(String fileName){
        fileName = fileName + ".txt";
        File myMatrix = new File(fileName); 
        while (myMatrix.exists()){
            System.out.println("Nama file sudah ada, silahkan ganti namanya");
            System.out.print("Nama file: ");
            fileName = scanner.next();
            fileName = fileName + ".txt";
            myMatrix = new File(fileName); 
        }
        System.out.println("File " + myMatrix.getName() + " dibuat");
    }

    public static void EquationValue(EquationSpace Result){
        while(true){
            PrintListMenu.Print(new String[]{
                "====================================Saving=====================================", 
                "Simpan dalam file?(Y/N)", 
            });
            System.out.print("> ");
            choice = scanner.next(); 
            if(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N")){
                break;
            }
            System.out.println("Input invalid, masukkan lagi");
        }
        if(choice.equalsIgnoreCase("Y")){
            MultiplePrinter.printFileCLI(Result);
        }
    }
}
