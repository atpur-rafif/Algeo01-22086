package CLI.Menu;

import Matrix.Matrix;
import Matrix.MatrixDeterminant;

import java.util.Scanner;

import CLI.IO.IOFile;
import CLI.IO.IONavigator;
import CLI.IO.IOPrompter;

public class DeterminantMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void Run(){
        IONavigator.next("Determinant");
        while(true){
            IOPrompter.printMultiLine(new String[]{
                "1. CLI",
                "2. File",
                "3. Back"
            });
            var choice = IOPrompter.getBoundedInt("Input Type> ", 1, 3);

            if(choice == 3) break;

            IONavigator.next("Method");
            IOPrompter.printMultiLine(new String[]{
                "1. Metode OBE", 
                "2. Metode Kofaktor", 
            });
            var method = IOPrompter.getBoundedInt("Metode> ", 1, 2);
            IONavigator.back();

            IONavigator.next("Input");
            Matrix matrix = null;
            if     (choice == 1) matrix = IOPrompter.getMatrix();
            else if(choice == 2) matrix = IOFile.readMatrix();
            IONavigator.back();

            var methodInString = "";
            if(method == 1) methodInString = "OBE"; 
            if(method == 2) methodInString = "Kofaktor"; 

            IONavigator.next("Result " + methodInString);
            Double determinant = null;
            if     (method == 1) determinant = MatrixDeterminant.calculateWithOBE(matrix);
            else if(method == 2) determinant = MatrixDeterminant.calculateWithCofactor(matrix);

            var s = "Determinan matrix: " + determinant;
            IOFile.askToSave(s);
            IONavigator.back();

            IONavigator.reload();
        }
        IONavigator.back();
    }
}
