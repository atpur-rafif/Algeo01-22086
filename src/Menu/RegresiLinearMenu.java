package Menu;

import Application.MultipleLinear;
import Matrix.Matrix;
import Matrix.MatrixReader;
import java.util.Scanner;
import Vector.*;

public class RegresiLinearMenu {
    static Scanner scanner = new Scanner(System.in);
    private static EquationSpace coefficient;
    private static String tryEquation;
    private static double result;
    private static boolean isTryEquation;
    private static Matrix samplePoint;

    public static void Run(){
        boolean isRegresi = true;
        PrintListMenu.clear();
        while(isRegresi == true){
            PrintListMenu.Repetitive(2);
            System.out.print("> ");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    System.out.println("Masukkan sample Point");
                    System.out.print("Masukkan banyak peubah: ");
                    int n = Integer.parseInt(scanner.next()); 
                    System.out.print("Masukkan banyak sampel: ");
                    int m = Integer.parseInt(scanner.next());
                    samplePoint = MatrixReader.readCLI(m, n + 1);

                    coefficient =  MultipleLinear.solve(samplePoint); 
                    System.out.print("Hasil: ");
                    MultipleLinear.DisplayEquation(coefficient);

                    tryMultipleEquation(coefficient);
                    Save.EquationValue(coefficient); //perlu diperbaiki savenya dengan hasil persamaan
                    break; 
                case "2": 
                    System.out.println("Masukkan sample Point");
                    samplePoint = MatrixReader.readFileCLI();
                    coefficient =  MultipleLinear.solve(samplePoint); 
                    
                    System.out.print("Hasil: ");
                    MultipleLinear.DisplayEquation(coefficient);

                    tryMultipleEquation(coefficient);
                    Save.EquationValue(coefficient);
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

    public static void tryMultipleEquation(EquationSpace coefficient){
        while(true){
            PrintListMenu.Print(new String[]{
            "========================Coba Persamaan==========================", 
            "1. Ya",
            "2. Tidak",
            "*Note: Masukkan dengan urutan angka yang sesuai"
        });
            tryEquation = scanner.next(); 
            if(tryEquation.equals("1")){
                isTryEquation = true;
                break;
            }
            else if (tryEquation.equals("2")){
                isTryEquation = false;
                break;
            }
            else{
                PrintListMenu.clear();
                System.out.print("input tidak valid");
            }
        }

        while(isTryEquation == true){
            EquationSpace variable = new EquationSpace(coefficient.basisCount);
            System.out.println("Masukkan nilai tiap x");
            variable.set(0, 1);
            for(int i = 1 ; i < variable.basisCount; ++i){
                String currentSubscript = String.valueOf((char)('\u2080' + (i)));
                System.out.print("Masukkan nilai " + "X" + currentSubscript+": ");
                var value = Double.parseDouble(scanner.next());
                variable.set(i, value);
            }  
            result = VectorSpace.innerProduct(coefficient, variable);
            System.out.println("Hasil: " + result);
            System.out.println("Coba lagi? (Y/N)");
            System.out.print("> ");
            tryEquation = scanner.next();
            isTryEquation = false;
            while(true){
                if(tryEquation.equalsIgnoreCase("Y")){
                    isTryEquation = true;
                    break;
                }
                else if(tryEquation.equalsIgnoreCase("N")){
                    isTryEquation = false;
                    break;
                }
                else{
                    System.out.println("Input tidak valid"); 
                    System.out.print("> ");
                    tryEquation = scanner.next();
                }
            }
        }
    }
}
