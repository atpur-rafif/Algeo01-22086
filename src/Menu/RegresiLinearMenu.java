package Menu;

import Application.MultipleLinear;
import Matrix.Matrix;
import Matrix.MatrixReader;
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
                    double[] solvedSamplePoint =  MultipleLinear.solve(samplePoint); 

                    System.out.print("Hasil: ");
                    MultipleLinear.Display(solvedSamplePoint);
                    
                    break; 
                case "2": 
                    System.out.println("Masukkan sample Point");
                    Matrix samplePointFile = MatrixReader.readFileCLI();
                    double[] solvedSamplePointFile =  MultipleLinear.solve(samplePointFile); 

                    System.out.print("Hasil: ");
                    MultipleLinear.Display(solvedSamplePointFile);
                    
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
