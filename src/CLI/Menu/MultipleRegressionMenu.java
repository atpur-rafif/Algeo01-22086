package CLI.Menu;

import Application.MultipleRegression;
import CLI.IO.IOFile;
import CLI.IO.IONavigator;
import CLI.IO.IOPrompter;
import CLI.IO.IOStringFormatter;
import Matrix.Matrix;
import Vector.*;

public class MultipleRegressionMenu {
    public static void Run(){
        IONavigator.next("Multiple Linear Regression");
        while(true){
            IOPrompter.printMultiLine(new String[]{
                "1. CLI",
                "2. File",
                "3. Back"
            });
            var input = IOPrompter.getBoundedInt("Input Type> ", 1, 3);

            if(input == 3) break;


            Matrix samplePoint = null;
            EuclideanSpace testPoint = null;

            IONavigator.next("Input");
            if(input == 1){
                var n = IOPrompter.getInteger("Masukkan banyak peubah: ");
                var m = IOPrompter.getInteger("Masukkan banyak sampel: ");
                System.out.println("Masukkan sample point");
                samplePoint = IOPrompter.getMatrix(m, n + 1);
                testPoint = IOPrompter.getEuclideanVectorInline("Masukkan test point: ", n);
            } else if(input == 2){
                var t = IOFile.readObscureFormat();
                samplePoint = t.matrix;
                testPoint = t.vector;
            }
            IONavigator.back();

            IONavigator.next("Result");
            var equation = MultipleRegression.solve(samplePoint);
            var result = MultipleRegression.approximate(equation, testPoint);
            var s = IOStringFormatter.multipleRegression(equation) + "\n" + "Hasil regresi: " + result;
            System.out.println(s);
            IOFile.askToSave(s);
            IONavigator.back();

            IONavigator.reload();
        }
        IONavigator.back();
    }
}
