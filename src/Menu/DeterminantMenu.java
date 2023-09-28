package Menu;

import Matrix.Matrix;
import Matrix.MatrixDeterminant;
import Matrix.MatrixDeterminantWithOBE;
import Matrix.MatrixReader;

import java.util.Scanner;

public class DeterminantMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void Run(){
        while(true){
            var choice = Prompter.getBoundedInt(new String[]{
                "=============================Determinant Menu=================================", 
                "1. Metode OBE", 
                "2. Metode Kofaktor", 
                "3. Back",
            }, 1, 3);

            if(choice == 3) break;

            Matrix matrix = null;

            var ioType = Prompter.getIOType();
            if     (ioType == IOType.CLI) matrix = MatrixReader.readCLI();
            else if(ioType == IOType.File) matrix = MatrixReader.readFileCLI();

            Double determinant = null;
            if     (choice == 1) determinant = MatrixDeterminant.calculateWithOBE(matrix);
            else if(choice == 2) determinant = MatrixDeterminant.calculateWithCofactor(matrix);

            System.out.println("\nDeterminan matrix: " + determinant + "\n");
            IOFile.askToSave(Double.toString(determinant));
        }
    }
}
