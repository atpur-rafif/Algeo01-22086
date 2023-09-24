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
                "5. Sistem Persamaan Linear", 
                "6. Determinan", 
                "7. Inverse Matriks", 
                "8. Quit",
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

                case "4": 
                    ImageInterpolation();
                    break; 

                case "5":
                    SPLMenu();
                    break;
                
                case "6": 
                    determinantMenu();
                    break;
                case "8": 
                    isRunning = false;
                    break; 
                default:
                System.out.print("\033\143");
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
            System.out.print("\033\143");
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
                    System.out.println("Masukkan sample Point");
                    
                    System.out.print("Masukkan banyak peubah: ");
                    int n = Integer.parseInt(scanner.next()); 

                    System.out.print("Masukkan banyak sampel: ");
                    int m = Integer.parseInt(scanner.next());
                    Matrix samplePoint = MatrixReader.read(m, n);
                    double solvedSamplePoint[] =  MultipleLinear.solve(samplePoint); 
                    System.out.print("Hasil: ["); 
                    for(int i = 0; i < solvedSamplePoint.length; ++i){
                        System.out.print(solvedSamplePoint[i]+ " ");
                    }
                    System.out.println("]"); 
                    
                    break;
                
                case "2": 
                    break;

                case "3": 
                    isRegresi = false; 
                    System.out.print("\033\143");
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

    private static void SPLMenu(){
        boolean isSPL = true; 
        while(isSPL == true){

        }
    }

    private static void determinantMenu(){
        boolean isDeterminant = true; 
        while(isDeterminant == true){

        }

    }
    //Printer untuk string dari semua menu




    private static void PrintListMenu(String[] strings) {
        for (String str : strings) {
            System.out.println(str);
        }
    }




}
