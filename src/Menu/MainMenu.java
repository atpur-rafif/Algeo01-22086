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
                    InterpolinomMenu.Run();
                    break;
                case "2": 
                    RegresiLinearMenu.Run();
                    break;
                case "3": 
                    BicubicSplineMenu.Run();
                    break;
                case "4": 
                    ImageResizing.Run();
                    break; 
                case "5":
                    SPLMenu.Run();
                    break;
                case "6": 
                    DeterminantMenu.Run();
                    break;
                case "7": 
                    InverseMenu.Run();
                    break;
                case "8": 
                    PrintListMenu.clear();
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
