package CLI.IO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import Vector.EuclideanSpace;
import Matrix.Matrix;

public class IOFile {
    static Pattern doublePattern = Pattern.compile("[0-9|.|-]");
    static Scanner scanner = new Scanner(System.in);
    static String choice;

    public static File createFile(Path path) throws IOException{
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

    public static ObscureFormat parseObscureFormat(String str) throws Exception{
        str = str.trim();

        int row = 0;
        int col = -1;

        int cCol = 0;
        boolean finished = false;
        var buffer = new ArrayList<Double>();
        String current = "";
        var len = str.length();
        for(int i = 0; i < len; ++i){
            var c = str.charAt(i);

            if(finished) throw new Exception("Invalid format");

            if(doublePattern.matcher(String.valueOf(c)).find()){
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
            var input = IOPrompter.getString("Masukkan path: ");
            path = Path.of(input);
            if(Files.exists(path)){
                String str = null;
                try {
                    str = Files.readString(path);
                    var t = parseObscureFormat(str);
                    if(t.vector.basisCount + 1 != t.matrix.col) throw new Exception("Format file tidak sesuai");
                    return t;
                } catch (Exception e) {
                    System.out.println("Format file tidak sesuai");
                    continue;
                }
            } else System.out.println("File tidak ditemukan");
        } while(true);
    }

    public static void save(Path path, String content) throws IOException{
        var f = createFile(path);
        var w = new FileWriter(f);
        w.write(content);
        w.close();
    }

    public static void askToSave(String content){
        var confirmation = IOPrompter.getBoolean("Save dalam file (Y/N)? ");
        if(!confirmation) return;

        Path path = null;

        do {
            var input = IOPrompter.getString("Masukkan path: ");
            path = Path.of(input);
            if(Files.exists(path)){
                IOPrompter.printMultiLine(new String[]{
                    "File sudah ada, lakukan: ",
                    "1. Overwrite",
                    "2. Masukan ulang path",
                    "3. Back"
                });
                var b = IOPrompter.getBoundedInt("", 1, 3);

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

    public static Matrix parseMatrix(String str) throws Exception{
        str = str.trim();

        int row = 0;
        int col = -1;

        int cCol = 0;
        boolean finished = false;
        var buffer = new ArrayList<Double>();
        String current = "";
        var len = str.length();
        for(int i = 0; i < len; ++i){
            var c = str.charAt(i);

            if(finished) throw new Exception("Invalid format");

            if(doublePattern.matcher(String.valueOf(c)).find()){
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


                if(col == cCol){
                    col = cCol;
                    cCol = 0;
                    row += 1;
                } else throw new Exception("Invalid format");
            }
        }

        var p = 0;
        var m = new Matrix(row, col);
        for(int i = 0; i < row; ++i){
            for(int j = 0; j < col; ++j){
                m.set(i, j, buffer.get(p));
                p += 1;
            }
        }

        return m;
    }

    public static Matrix readMatrix(){
        Path path = null;
        do{
            var input = IOPrompter.getString("Masukkan path: ");
            path = Path.of(input);
            if(Files.exists(path)){
                String str = null;
                try {
                    str = Files.readString(path);
                    var t = parseMatrix(str);
                    return t;
                } catch (Exception e) {
                    System.out.println("Format file tidak sesuai");
                    continue;
                }
            } else System.out.println("File tidak ditemukan");
        } while(true);
    }
}
