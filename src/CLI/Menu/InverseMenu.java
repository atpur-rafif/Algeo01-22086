package CLI.Menu;

import CLI.StringFormatter;
import CLI.IO.IOFile;
import CLI.IO.IOType;
import CLI.IO.IOPrompter;
import Matrix.Matrix;
import Matrix.MatrixDeterminant;
import Matrix.MatrixInverse;
import Matrix.MatrixReader;

public class InverseMenu {
    public static void Run(){
        while(true){
            var choice = IOPrompter.getBoundedInt(new String[]{
                "=============================Inverse Menu=================================", 
                "1. Metode OBE", 
                "2. Metode Adjoin", 
                "3. Back",
            }, 1, 3);

            if(choice == 3) break;

            Matrix matrix = null;

            var ioType = IOPrompter.getIOType();
            if     (ioType == IOType.CLI) matrix = MatrixReader.readCLI();
            else if(ioType == IOType.File) matrix = MatrixReader.readFileCLI();

            var det = MatrixDeterminant.calculateWithOBE(matrix);
            if(det == 0){
                System.out.println("Matrix tidak memliki invers karena determinan = 0");
                break;
            }

            Matrix inversedMatrix = null;
            if     (choice == 1) inversedMatrix = MatrixInverse.calculateWithGaussJordan(matrix);
            else if(choice == 2) inversedMatrix = MatrixInverse.calculateWithCofactor(matrix);

            System.out.println(StringFormatter.matrix(inversedMatrix));
            IOFile.askToSave(StringFormatter.matrix(inversedMatrix));
        
        }
    }
}
