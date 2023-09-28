package Menu;

import java.util.Scanner;

import Matrix.Matrix;
import Matrix.MatrixInverse;
import Matrix.MatrixReader;

public class InverseMenu {
    static Scanner scanner = new Scanner(System.in);
    
    public static void Run(){
        PrintListMenu.clear();
        
        while(true){
            var choice = Prompter.getBoundedInt(new String[]{
                "=============================Inverse Menu=================================", 
                "1. Metode OBE", 
                "2. Metode Adjoin", 
                "3. Back",
            }, 1, 3);

            if(choice == 3) break;

            Matrix matrix = null;

            var ioType = Prompter.getIOType();
            if(ioType == IOType.CLI){
                matrix = MatrixReader.readCLI();
            } else if(ioType == IOType.File){
                matrix = MatrixReader.readFileCLI();
            }

            Matrix inversedMatrix = null;
            if     (choice == 1) inversedMatrix = MatrixInverse.calculateWithGaussJordan(matrix);
            else if(choice == 2) inversedMatrix = MatrixInverse.calculateWithCofactor(matrix);

            System.out.println(StringFormatter.matrix(inversedMatrix));
            IOFile.askToSave(StringFormatter.matrix(inversedMatrix));
        
        }
    }
}
