package Menu;

import java.util.Scanner;
import Matrix.*;
import Application.*;

public class MainMenu {
    static Scanner scanner = new Scanner(System.in);
    
    public static void InterfaceProgram(){
        boolean isRunning = true;
        while(isRunning == true){
            PrintListMenu(new String[]{
                "================================Selamat Datang=================================", 
                "Program Matrix Application", 
                "1. Interpolasi Polinomial", 
                "2. Regresi Linear Berganda", 
                "3. Bicubic Spline Interpolation",    
                "4. Image Interpolation",   
                "5. Quit",
            });
            System.out.print("input> ");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    InterpolasiMenu();
                    break;
                
                case "2": 
                    RegresiLinearMenu();
                    break;

                case "3": 
                    BicubicSplineMenu();
                    break;

                case "5": 
                    ImageInterpolation();
                    break; 

                case "6": 
                    isRunning = false;
                    break; 
                default:
                    System.out.println("Input tidak valid");
                    break;
            }

        }
        
    }
    
    private static void InterpolasiMenu(){

    }

    private static void RegresiLinearMenu(){
        boolean isRegresi = true;
        while(isRegresi == true){
            PrintListMenu(new String[]{
                "=============================Regresi Linear Menu=================================", 
                "1. CLI", 
                "2. File", 
                "3. Back",
                "*Note: Pilih menggunakan angka yang sesuai",
            });

            System.out.print("> ");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    boolean isCLI = true; 
                    while(isCLI == true){
                        System.out.println("Masukkan sample Point: "); 
                        Matrix samplePoint = MatrixReader.read();
                        double solvedSamplePoint[] =  MultipleLinear.solve(samplePoint);  
                        for(int i = 0; i < solvedSamplePoint.length; ++i){
                            System.out.println(solvedSamplePoint[i]);
                        }
                        isCLI = false; 
                    }
                    break;
                
                case "2": 
                    break;

                case "3": 
                    isRegresi = false; 
                    break;

                default:
                    System.out.println("Input tidak valid");
                    break;
            }

        }
        
    }


    private static void BicubicSplineMenu(){
        
    }

    private static void ImageInterpolation(){

    }
    //Printer untuk string dari semua menu
    private static void PrintListMenu(String[] strings) {
        for (String str : strings) {
            System.out.println(str);
        }
    }


}
