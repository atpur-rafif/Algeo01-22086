package Menu;

import Application.MultipleLinear;
import Matrix.Matrix;
import Matrix.MatrixReader;
import Vector.EquationSpace;
import java.util.Scanner;

public class RegresiLinearMenu {
    static Scanner scanner = new Scanner(System.in);
    public static void Display(){
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
}
