package CLI.Menu;

import CLI.IO.IOFile;
import CLI.IO.IONavigator;
import CLI.IO.MatrixReader;
import CLI.IO.IOStringFormatter;
import CLI.IO.IOPrompter;
import Matrix.Matrix;
import Matrix.MatrixDeterminant;
import Matrix.MatrixInverse;

public class InverseMenu {
    public static void Run(){
        IONavigator.next("Inverse");
        while(true){
            IOPrompter.printMultiLine(new String[]{
                "1. CLI",
                "2. File",
                "3. Back"
            });
            var choice = IOPrompter.getBoundedInt("Input Type: ", 1, 3);

            if(choice == 3) break;

            IONavigator.next("Method");
            IOPrompter.printMultiLine(new String[]{
                "1. Metode OBE", 
                "2. Metode Adjoin", 
            });
            var method = IOPrompter.getBoundedInt("Metode> ", 1, 3);
            IONavigator.back();


            IONavigator.next("Input");
            Matrix matrix = null;
            if     (choice == 1) matrix = MatrixReader.readCLI();
            else if(choice == 2) matrix = MatrixReader.readFileCLI();
            IONavigator.back();

            IONavigator.next("Result");
            var det = MatrixDeterminant.calculateWithOBE(matrix);
            if(det == 0){
                System.out.println("Matrix tidak memliki invers karena determinan = 0");
                IOPrompter.waitEnter();
                continue;
            }
            if(matrix.col != matrix.row){
                System.out.println("Matrix bukan berbentuk persegi");
                IOPrompter.waitEnter();
                continue;
            }

            Matrix inversedMatrix = null;
            if     (method == 1) inversedMatrix = MatrixInverse.calculateWithGaussJordan(matrix);
            else if(method == 2) inversedMatrix = MatrixInverse.calculateWithCofactor(matrix);

            var s = IOStringFormatter.matrix(inversedMatrix);
            System.out.println(s);
            IOFile.askToSave(s);
            IONavigator.back();

            IONavigator.reload();
        }
        IONavigator.back();
    }
}
