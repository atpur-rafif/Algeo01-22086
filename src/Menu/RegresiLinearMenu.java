package Menu;

import Application.MultipleLinear;
import Matrix.Matrix;
import Matrix.MatrixReader;
import java.util.Scanner;
import Vector.*;

public class RegresiLinearMenu {
    static Scanner scanner = new Scanner(System.in);
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
                    Matrix samplePoint = MatrixReader.readCLI(m, n + 1);
                    var coefficient =  MultipleLinear.solve(samplePoint); 
                    System.out.print("Hasil: ");
                    MultipleLinear.DisplayEquation(coefficient);

                    PrintListMenu.Print(new String[]{
                        "========================Coba Persamaan==========================", 
                        "1. Ya",
                        "2. Tidak",
                        "*Note: Masukkan dengan urutan angka yang sesuai"
                    });
                    String CobaPersamaan = scanner.next(); 
                        
                    while (CobaPersamaan.equalsIgnoreCase("1")) {
                        EquationSpace variable = new EquationSpace(coefficient.basisCount);
                        System.out.println("Masukkan nilai tiap x");
                        variable.set(0, 1);
                        for(int i = 1 ; i < variable.basisCount; ++i){
                            var value = Double.parseDouble(scanner.next());
                            variable.set(i, value);
                        }  
                        
                        var result = VectorSpace.innerProduct(coefficient, variable);
                        System.out.println("Hasil: " + result);
                        System.out.println("Coba lagi? (Y/N)");
                        System.out.print("> ");
                        CobaPersamaan = scanner.next();
                        if(CobaPersamaan.equalsIgnoreCase("N")){
                            CobaPersamaan = "2";
                        }
                        
                    }
                    
                    SaveResult.multipleValue(coefficient); //perlu diperbaiki savenya dengan hasil persamaan
                    
                    break; 
                case "2": 
                    System.out.println("Masukkan sample Point");
                    Matrix samplePointFile = MatrixReader.readFileCLI();
                    EquationSpace solvedSamplePointFile =  MultipleLinear.solve(samplePointFile); 

                    System.out.print("Hasil: ");
                    MultipleLinear.DisplayEquation(solvedSamplePointFile);
                    
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
}
