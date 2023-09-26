package Menu;


import java.util.Scanner;
import Matrix.MatrixReader;
import Application.*;

public class InterpolinomMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void Display(){
        boolean isInterp = true; 
        PrintListMenu.clear(); 
        while(isInterp){
            PrintListMenu.Repetitive(1);
            System.out.print("> ");
            String choice = scanner.next(); 
            switch (choice){
                
                //CLI
                case "1": 
                    var Matriks = readPoint();
                    double x = Double.parseDouble(scanner.next());
                    double result= PolynomialInterp.f(Matriks, x);
                    System.out.println("Hasil: "+ result);


                    break;
                //FILE
                case "2": 
                    var MatriksFile = MatrixReader.readFileCLI();
                    double xFile = Double.parseDouble(scanner.next());
                    double resultFile= PolynomialInterp.f(MatriksFile, xFile);
                    System.out.println("Hasil: "+ resultFile);

                    break; 

                case "3": 
                    isInterp = false;
                    PrintListMenu.clear();
                    break; 
            }
        }
    }

 
}
