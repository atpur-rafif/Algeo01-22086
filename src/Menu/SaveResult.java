package Menu;
import java.util.Scanner;
import Matrix.*;
import Vector.EquationSpace;
import Vector.VectorSpace;
import FilePrinter.*;


public class SaveResult {
    static Scanner scanner = new Scanner(System.in);
    static String choice;
    public static void saveResultMatrix(Matrix M){
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

    public static void saveResultSingleValue(Matrix matrix, Double Result){
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
            SinglePrinterwithMatrix.printFileCLI(matrix, Result);
        }
    }
    public static void multipleValue(EquationSpace Result){
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
