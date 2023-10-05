import CLI.MainMenu;
import CLI.IO.IOFile;
import CLI.IO.IOPrompter;
import CLI.IO.IOStringFormatter;
import CLI.IO.MatrixReader;
import Matrix.Matrix;
import Matrix.MatrixArithmetic;

public class Test {
    public static void main(String[] args){
        //MainMenu.InterfaceProgram();
        var m = IOFile.readMatrix();
        System.out.println(IOStringFormatter.matrix(m));
    }
}