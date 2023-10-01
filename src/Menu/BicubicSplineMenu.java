package Menu;

import java.util.Scanner;
import Transformation.LocalSplineTransformation;
import Vector.EquationSpace;
import Vector.EuclideanSpace;
import Application.BicubicSpline;

public class BicubicSplineMenu {
    static Scanner scanner = new Scanner(System.in);

    static EuclideanSpace inputCLI(){
        var v = new EuclideanSpace(16);
        String[] funcFormat = new String[] { "f", "f_x", "f_y", "f_xy" };
        for (int i = 0; i < LocalSplineTransformation.equationCount; ++i) {
            for (int j = 0; j < LocalSplineTransformation.pointCount; ++j) {
                var p = LocalSplineTransformation.points[j];
                System.out.print(funcFormat[i] + "(" + (int) p.x + "," + (int) p.y + ") = ");
                double val = Double.parseDouble(scanner.next());
                v.set(LocalSplineTransformation.pointCount * i + j, val);
            }
        }
        return v;
    }

    public static void Run() {
        while(true){
            var choice = Prompter.getBoundedInt(new String[]{
                "===============================================Bicubic Spline Menu===============================================",
                "1. CLI", 
                "2. File",
                "3. Back",
            }, 1, 3);

            if(choice == 3) break;

            EuclideanSpace gradient = null;
            if(choice == 1){
                gradient = inputCLI();
            } else if(choice == 2){

            };

            EquationSpace eq = BicubicSpline.getEquation(gradient);
            EuclideanSpace p = Prompter.getEuclideanVectorInline("Masukkan titik: ", 2);
            double x = p.get(0);
            double y = p.get(1);
            double r = BicubicSpline.approximate(eq, x, y);
            System.out.println("Hasil aproximasi f(" + x + "," + y + ")" + " = " + r + "\n");

            IOFile.askToSave(Double.toString(r));
        }
    }
}
