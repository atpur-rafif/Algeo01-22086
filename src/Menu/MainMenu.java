package Menu;

import java.util.Scanner;
import Matrix.*;
import Vector.EquationSpace;
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

                case "7": 
                    InverseMenu();
                    break;
                case "8": 
                    isRunning = false;
                    break; 
                default:
                clear();
                System.out.println("Input tidak valid");
                    break;
            }

        }
        
    }
    
    private static void InterpolasiMenu(){

    }

    private static void RegresiLinearMenu(){
        boolean isRegresi = true;
        System.out.print("\033\143");
        while(isRegresi == true){
            RepetitivePrinter(2);
            System.out.print("> ");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    System.out.println("Masukkan sample Point");
                    System.out.print("Masukkan banyak peubah: ");
                    int n = Integer.parseInt(scanner.next()); 
                    System.out.print("Masukkan banyak sampel: ");
                    int m = Integer.parseInt(scanner.next());
                    Matrix samplePoint = MatrixReader.readCLI(m, n);
                    EquationSpace solvedSamplePoint =  MultipleLinear.solve(samplePoint); 

                    System.out.print("Hasil: ");
                    for(int i = 0; i < solvedSamplePoint.basisCount; ++i){
                        System.out.print(solvedSamplePoint.get(i) + " ");
                    }
                    System.out.println("\n");
                    
                    break; 
                case "2": 
                    System.out.println("Masukkan sample Point");
                    Matrix samplePointFile = MatrixReader.readFileCLI();
                    EquationSpace solvedSamplePointFile =  MultipleLinear.solve(samplePointFile); 

                    for(int i = 0; i < solvedSamplePointFile.basisCount; ++i){
                        System.out.print(solvedSamplePointFile.get(i) + " ");
                    }
                    System.out.println("\n");
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
        clear();
        boolean isDeterminant = true; 
        String inputChoice;
        double Result; 
        var DeterminantMatrix = new Matrix(0, 0); 
        while(isDeterminant == true){
            boolean isInput = true;
            PrintListMenu(new String[]{
                "=============================Determinant Menu=================================", 
                "1. Metode OBE", 
                "2. Metode Kofaktor", 
                "3. Back",
                "*Note: Pilih menggunakan angka yang sesuai",
            });

            System.out.print("> ");
            String choice = scanner.next(); 
            switch(choice){
                case "1": 
                    clear();
                    while(isInput){
                        RepetitivePrinter(6);   
                        System.out.print("> "); 
                        inputChoice = scanner.next();
                        switch(inputChoice){
                            case "1": 
                                DeterminantMatrix = MatrixReader.readCLI(); 
                                Result = MatrixDeterminantWithOBE.calculate(DeterminantMatrix);
                                System.out.println("Determinan: " + Result);
                                break;
                            case "2":
                                DeterminantMatrix = MatrixReader.readFileCLI(); 
                                Result = MatrixDeterminantWithOBE.calculate(DeterminantMatrix);
                                System.out.println("Determinan: " + Result);
                                break;
                            case "3": 
                                clear();
                                isInput = false;
                                break;
                            default: 
                                clear();
                                System.out.println("Input tidak valid");
                                break;
                            }
                        }
                    break;

                case "2":
                    clear();
                    while(isInput){
                        RepetitivePrinter(6);   
                        System.out.print("> "); 
                        inputChoice = scanner.next();
                        switch(inputChoice){
                            case "1": 
                                DeterminantMatrix = MatrixReader.readCLI(); 
                                Result = MatrixDeterminant.calculate(DeterminantMatrix);
                                System.out.println("Determinan: " + Result);
                                break;
                            case "2":
                                DeterminantMatrix = MatrixReader.readFileCLI(); 
                                Result = MatrixDeterminant.calculate(DeterminantMatrix);
                                System.out.println("Determinan: " + Result);
                                break;
                            case "3": 
                                isInput = false;
                                clear();
                                break;
                            default: 
                                clear();
                                System.out.println("Input tidak valid");
                                break;
                        }
                    }
                    break;
                case "3":
                    clear();
                    isDeterminant = false;
                    break;
                default: 
                    clear();
                    System.out.println("Input tidak Valid");
                    break;
            }
        }
    }

    private static void InverseMenu(){
        clear();
        boolean isInverse = true; 
        boolean isInput = true;
        String choice;
        String inputChoice;
        Matrix inversedMatrix; 
        var Matrix = new Matrix(0, 0); 
        
        while(isInverse){
            PrintListMenu(new String[]{
                "=============================Inverse Menu=================================", 
                "1. Metode OBE", 
                "2. Metode Adjoin", 
                "3. Back",
                "*Note: Pilih menggunakan angka yang sesuai",
            });
            isInput = true;
            System.out.print("> ");
            choice = scanner.next(); 
            switch(choice){
                case "1": 
                    clear();
                    while(isInput){
                        RepetitivePrinter(6);   
                        System.out.print("> "); 
                        inputChoice = scanner.next();
                        switch(inputChoice){
                            case "1": 
                                Matrix = MatrixReader.readCLI(); 
                                inversedMatrix = MatrixInverse.calculateWithGaussJordan(Matrix);
                                System.out.println("Matris Inverse: ");
                                MatrixPrinter.print(inversedMatrix);
                                break;

                            case "2":
                                Matrix = MatrixReader.readFileCLI(); 
                                inversedMatrix = MatrixInverse.calculateWithGaussJordan(Matrix);
                                System.out.println("Matris Inverse: ");
                                MatrixPrinter.print(inversedMatrix);
                                break;

                            case "3": 
                                clear();
                                isInput = false;
                                break;

                            default: 
                                clear();
                                System.out.println("Input tidak valid");
                                break;
                            }
                        }
                    break; 
                case "2": 
                    clear();
                    while(isInput){
                        RepetitivePrinter(6);   
                        System.out.print("> "); 
                        inputChoice = scanner.next();
                        switch(inputChoice){
                            case "1": 
                                Matrix = MatrixReader.readCLI(); 
                                inversedMatrix = MatrixInverse.calculateWithCofactor(Matrix);
                                System.out.println("Matris Inverse: ");
                                MatrixPrinter.print(inversedMatrix);
                                break;
                            case "2":
                                Matrix = MatrixReader.readFileCLI(); 
                                inversedMatrix = MatrixInverse.calculateWithCofactor(Matrix);
                                System.out.println("Matris Inverse: ");
                                MatrixPrinter.print(inversedMatrix);
                                break;
                            case "3": 
                                clear();
                                isInput = false;
                                break;
                            default: 
                                clear();
                                System.out.println("Input tidak valid");
                                break;
                            }
                        }
                    break; 
                case "3": 
                    isInverse = false;
                    clear();
                    break;
                default: 
                    clear();
                    break;
            }
        }
    }
    //Printer untuk string dari semua menu





    private static void PrintListMenu(String[] strings) {
        for (String str : strings) {
            System.out.println(str);
        }
    }

    private static void RepetitivePrinter(int Menu) {
        String[] printerMenu = {
            "==================================Cara Input=================================", 
            "1. CLI", 
            "2. File", 
            "3. Back",
            "*Note: Pilih menggunakan angka yang sesuai",
        };

        if(Menu == 2){
            printerMenu[0] = "==============================Regresi Linear Berganda================================";
        }
        else if(Menu == 6){
            printerMenu[0] = "================================Determinant Menu======================================";
        }
        else if(Menu == 7){
            printerMenu[0] = "================================Inverse Menu==========================================";
        }
        PrintListMenu(printerMenu);
    }

    private static void clear(){
        System.out.print("\033\143");
    }




}
