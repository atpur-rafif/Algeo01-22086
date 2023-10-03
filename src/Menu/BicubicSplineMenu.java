package Menu;

import java.util.Scanner;
import Transformation.LocalSplineTransformation;
import Vector.EquationSpace;
import Vector.EuclideanSpace;
import Application.BicubicSpline;
import Matrix.MatrixPrinter;

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

            int eqCount = LocalSplineTransformation.equationCount;
            int poCount = LocalSplineTransformation.pointCount;
            EuclideanSpace gradient = new EuclideanSpace(16);
            EuclideanSpace vector = null;
            if(choice == 1){
                String[] funcFormat = new String[] { "f", "f_x", "f_y", "f_xy" };
                for (int i = 0; i < eqCount; ++i) {
                    for (int j = 0; j < poCount; ++j) {
                        var p = LocalSplineTransformation.points[j];
                        System.out.print(funcFormat[i] + "(" + (int) p.x + "," + (int) p.y + ") = ");
                        double val = Double.parseDouble(scanner.next());
                        gradient.set(poCount * i + j, val);
                    }
                }
                vector = Prompter.getEuclideanVectorInline("Masukkan titik: ", 2);
            } else if(choice == 2){
                var t = IOFile.readObscureFormat();
                var m = t.matrix;
                MatrixPrinter.print(m);
                for(int i = 0; i < eqCount; ++i){
                    for(int j = 0; j < poCount; ++j){
                        gradient.set(poCount * i + j, m.get(i, j));
                    }
                }
                vector = t.vector;
            };

            EquationSpace eq = BicubicSpline.getEquation(gradient);
            double x = vector.get(0);
            double y = vector.get(1);
            double r = BicubicSpline.approximate(eq, x, y);
            System.out.println("Hasil aproximasi f(" + x + "," + y + ")" + " = " + r + "\n");

            IOFile.askToSave(Double.toString(r));
        }
    }
}
