package Menu;

import java.util.Scanner;
import Transformation.LocalSplineTransformation;
import Vector.EquationSpace;
import Vector.EuclideanSpace;
import Application.BicubicSpline;

public class BicubicSplineMenu {
    static Scanner scanner = new Scanner(System.in);
    private static boolean isBicubic = true;
    private static String choice;
    private static EuclideanSpace base;
    private static double x, y; 
    public static void Run(){
        while(isBicubic){
            PrintListMenu.Print(new String[]{
                "===============================================Bicubic Spline Menu===============================================",
                "1. CLI", 
                "2. File",
                "3. Back",
            });
            choice = scanner.next();
            switch (choice) {
                case "1":
                    System.out.println("Masukkan titik-titik: ");
                    base = new EuclideanSpace(16);
                    String[] funcFormat = new String[]{"f", "f_x", "f_y", "f_xy"};
                    for(int i = 0; i < LocalSplineTransformation.equationCount; ++i){
                        for(int j = 0; j < LocalSplineTransformation.pointCount; ++j){
                            var p = LocalSplineTransformation.points[j];
                        
                            System.out.print(funcFormat[i] +"(" + (int)p.x + "," + (int)p.y + ") = ");
                            double val = Double.parseDouble(scanner.next());
                            base.set(LocalSplineTransformation.pointCount*i + j, val);
                        }
                    }

                    EquationSpace equation = BicubicSpline.getEquation(base);
                    System.out.print("Masukkan nilai x:"); 
                    x = Double.parseDouble(scanner.next());
                    System.out.print("Masukkan nilai Y:");
                    y = Double.parseDouble(scanner.next());
                    double resultApproximate = BicubicSpline.approximate(equation, x, y);
                    System.out.println("hasil approksimasi: " + resultApproximate);

                    break;
                case "2":
                    
                    break;
                case "3":
                    isBicubic = false;
                    PrintListMenu.clear();
                    break;

                default:
                    System.out.print("Input tidak valid\n");
                    break;
            }
        }

    }

  
    
}
