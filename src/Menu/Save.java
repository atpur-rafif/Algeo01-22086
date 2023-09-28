package Menu;
import java.io.File;
import java.util.Scanner;
import Matrix.*;
import Vector.EquationSpace;
import FilePrinter.*;


public class Save {
    static Scanner scanner = new Scanner(System.in);
    static String choice;

    public static void CreateFile(String fileName){
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
