import Matrix.MatrixPrinter;
import Menu.IOFile;
import Menu.MainMenu;
import Menu.StringFormatter;
import Vector.EquationSpace;

public class Test {
    public static void main(String[] args){
        //MainMenu.InterfaceProgram();
        var o = IOFile.readObscureFormat();
        var m = o.matrix;
        var v = o.vector;
        MatrixPrinter.print(m);
        for(int i = 0; i < v.basisCount; ++i){
            System.out.print(v.get(i) + " ");
        }
    }
}