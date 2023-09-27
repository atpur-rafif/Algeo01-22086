package Menu;

import java.util.Scanner;


public class MainMenu {
    static Scanner scanner = new Scanner(System.in);
    
    public static void InterfaceProgram(){
        boolean isRunning = true;
        while(isRunning == true){
            PrintListMenu.Print(new String[]{
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
            });
            System.out.print("input> ");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    InterpolinomMenu.Display();
                    break;
                case "2": 
                    RegresiLinearMenu.Display();
                    break;
                case "3": 
                    BicubicSplineMenu.Display();
                    break;
                case "4": 
                    ImageResizing.Display();
                    break; 
                case "5":
                    SPLMenu.Display();
                    break;
                case "6": 
                    DeterminantMenu.Display();
                    break;
                case "7": 
                    InverseMenu.Display();
                    break;
                case "8": 
                    isRunning = false;
                    break; 
                default:
                PrintListMenu.clear();
                System.out.println("Input tidak valid");
                    break;
            }
        }
    }
}
