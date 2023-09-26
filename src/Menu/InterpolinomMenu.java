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
                    System.out.print("Masukkan Jumlah Titik : ");
                    int n = Integer.parseInt(scanner.next());
                    var Matriks = MatrixReader.readCLI(n,2);
                    var interpolatedFunc = PolynomialInterp.calculate(Matriks);
                    System.out.print("f(x) = ");
                    for (int i=n-1;i>=0;i--){
                        System.out.print("("+interpolatedFunc.get(i, 0)+")x^"+i);
                        if(i!=0){
                            System.out.print("+");
                        }
                    }
                    System.out.print("\nx = ");
                    double x = Double.parseDouble(scanner.next());
                    double fxresult= PolynomialInterp.f(Matriks, x);
                    System.out.println("f("+x+") = "+ fxresult);


                    break;
                //FILE
                case "2": 
                    var MatriksFile = MatrixReader.readFileCLI();
                    var interpolatedFuncFile = PolynomialInterp.calculate(MatriksFile);
                    System.out.print("f(x) = ");
                    for (int i=MatriksFile.row-1;i>=0;i--){
                        System.out.print("("+interpolatedFuncFile.get(i, 0)+")x^"+i);
                        if(i!=0){
                            System.out.print("+");
                        }
                    }
                    System.out.print("\nx = ");
                    double xFile = Double.parseDouble(scanner.next());
                    double resultFile= PolynomialInterp.f(MatriksFile, xFile);
                    System.out.println("f("+xFile+") = "+ resultFile);

                    break; 

                case "3": 
                    isInterp = false;
                    PrintListMenu.clear();
                    break; 
            }
        }
    }

 
}
