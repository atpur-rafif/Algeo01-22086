import Menu.IOFile;
import Menu.MainMenu;
import Menu.StringFormatter;
import Vector.EquationSpace;

public class Test {
    public static void main(String[] args){
        //MainMenu.InterfaceProgram();
        EquationSpace eq = new EquationSpace(16);
        for(int i = 0; i < 16; ++i){
            eq.set(i, i);
        }
        System.out.println(StringFormatter.polynomialEquation(eq));
    }
}