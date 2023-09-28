package Menu;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import Matrix.*;
import Vector.EquationSpace;
import FilePrinter.*;


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
            }
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



    public static void ResultMatrix(Matrix M){
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
            MatrixPrinter.printMatrixFileCLI(M);
        }
    }

    public static void ResultSingleValue(Matrix matrix, Double Result){
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
            DeterminantPrinter.printFileCLI(matrix, Result);
        }
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
