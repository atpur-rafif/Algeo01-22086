package CLI;

import java.util.Scanner;

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
        while(true){
            IOPrompter.print(new String[]{
                "================================selamat datang=================================", 
                "program matrix application", 
                "1. interpolasi polinomial", 
                "2. regresi linear berganda", 
                "3. bicubic spline interpolation",    
                "4. image interpolation",   
                "5. sistem persamaan linear", 
                "6. determinan", 
                "7. inverse matriks", 
                "8. quit",
            });
            var choice = IOPrompter.getBoundedInt("", 1, 8);

            PrintListMenu.clear();
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
