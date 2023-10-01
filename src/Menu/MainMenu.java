package Menu;

import java.util.Scanner;


public class MainMenu {
    static Scanner scanner = new Scanner(System.in);
    
    public static void InterfaceProgram(){
        while(true){
            var choice = Prompter.getBoundedInt(new String[]{
                "================================Selamat Datang=================================", 
                "Program Matrix Application", 
                "1. Interpolasi Polinomial", 
                "2. Regresi Linear Berganda", 
                "3. Bicubic Spline Interpolation",    
                "4. Image Interpolation",   
                "5. Sistem Persamaan Linear", 
                "6. Determinan", 
                "7. Inverse Matriks", 
                "8. Quit",
            }, 1, 8);

            PrintListMenu.clear();
            if     (choice == 1) InterpolinomMenu.Run();
            else if(choice == 2) RegresiLinearMenu.Run();
            else if(choice == 3) BicubicSplineMenu.Run();
            else if(choice == 4) ImageResizing.Run();
            else if(choice == 5) SPLMenu.Run();
            else if(choice == 6) DeterminantMenu.Run();
            else if(choice == 7) InverseMenu.Run();
            else if(choice == 8) break;
        }
    }
}
