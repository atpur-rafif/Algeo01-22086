package CLI.Menu;

import java.util.Scanner;

import CLI.IO.IOFile;
import CLI.IO.IONavigator;
import CLI.IO.IOPrompter;
import CLI.IO.MatrixReader;
import Matrix.Matrix;
import Matrix.MatrixLinearEquation;
import Matrix.MatrixLinearEquationType;

public class SPLMenu {
    static Scanner scanner = new Scanner(System.in);
    public static void Run(){
        IONavigator.next("Linear Equations");
        while(true){
            IOPrompter.printMultiLine(new String[]{
                "1. CLI",
                "2. File",
                "3. Back"
            });
            var choice = IOPrompter.getBoundedInt("Input Type>", 1, 3);

            if(choice == 3) break;

            IONavigator.next("Input");
            Matrix matrix = null;
            if(choice == 1){
                var r = IOPrompter.getInteger("Masukkan banyaknya persamaan: ");
                var c = IOPrompter.getInteger("Masukkan banyaknya koefisien: ");
                System.out.println("Masukan matrix augmented: ");
                matrix = MatrixReader.readCLI(r, c + 1);
            } else if(choice == 2){
                matrix = MatrixReader.readFileCLI();
            }
            IONavigator.back();

            IONavigator.next("Method");
            IOPrompter.printMultiLine(new String[]{
                "1. Metode Gauss",
                "2. Methode GaussJordan",
                "3. Methode Crammer",
            });
            var method = IOPrompter.getBoundedInt("Metode: ", 1, 3);
            IONavigator.back();

            IONavigator.next("Result");
            String r = null;
            if(method == 1){
                r = MatrixLinearEquation.solutionAugmented(matrix, MatrixLinearEquationType.Gaussian);
            } else if(method == 2){
                r = MatrixLinearEquation.solutionAugmented(matrix, MatrixLinearEquationType.GausJordan);
            } else if(method == 3){
                r = MatrixLinearEquation.solutionAugmented(matrix, MatrixLinearEquationType.Crammer);
            }
            System.out.println(r);
            IOFile.askToSave(r);
            IONavigator.back();

            IONavigator.reload();
            break;
        }
        IONavigator.back();
    }
}
