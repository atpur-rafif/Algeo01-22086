package CLI;

import java.util.Scanner;

import CLI.IO.IONavigator;
import CLI.IO.IOPrompter;
import CLI.Menu.BicubicSplineMenu;
import CLI.Menu.DeterminantMenu;
import CLI.Menu.ImageResizingMenu;
import CLI.Menu.PolynomialInterpolationMenu;
import CLI.Menu.InverseMenu;
import CLI.Menu.MultipleRegressionMenu;
import CLI.Menu.SPLMenu;


public class MainMenu {
    static Scanner scanner = new Scanner(System.in);
    
    public static void InterfaceProgram(){
        IONavigator.next("Matrix");
        while(true){
            IOPrompter.printMultiLine(new String[]{
                "1. Interpolasi Polinomial", 
                "2. Regresi Linear Berganda", 
                "3. Bicubic Spline Interpolation",    
                "4. Image Interpolation",   
                "5. Sistem Persamaan Linear", 
                "6. Determinan", 
                "7. Inverse Matriks", 
                "8. Keluar",
            });
            var choice = IOPrompter.getBoundedInt("Menu> ", 1, 8);

            if     (choice == 1) PolynomialInterpolationMenu.Run();
            else if(choice == 2) MultipleRegressionMenu.Run();
            else if(choice == 3) BicubicSplineMenu.Run();
            else if(choice == 4) ImageResizingMenu.Run();
            else if(choice == 5) SPLMenu.Run();
            else if(choice == 6) DeterminantMenu.Run();
            else if(choice == 7) InverseMenu.Run();
            else if(choice == 8) break;
        }
    }
}
