package CLI.Menu;

import Application.MultipleRegression;
import CLI.IO.IOFile;
import CLI.IO.IOPrompter;
import CLI.IO.IOStringFormatter;
import CLI.IO.MatrixReader;
import Matrix.Matrix;
import Vector.*;

public class MultipleRegressionMenu {
    public static void Run(){
        while(true){
            IOPrompter.print(new String[]{
                "Multiple Regression",
                "1. CLI",
                "2. File",
                "3. Back"
            });
            var input = IOPrompter.getBoundedInt("", 1, 3);

            if(input == 3) break;


            Matrix samplePoint = null;
            EuclideanSpace testPoint = null;
            if(input == 1){
                var n = IOPrompter.getInteger("Masukkan banyak peubah: ");
                var m = IOPrompter.getInteger("Masukkan banyak sampel: ");
                System.out.println("Masukkan sample point");
                samplePoint = MatrixReader.readCLI(m, n + 1);
                testPoint = IOPrompter.getEuclideanVectorInline("Masukkan test point: ", n);
            } else if(input == 2){
                var t = IOFile.readObscureFormat();
                samplePoint = t.matrix;
                testPoint = t.vector;
            }


            var equation = MultipleRegression.solve(samplePoint);
            var result = MultipleRegression.approximate(equation, testPoint);
            var out = IOStringFormatter.multipleRegression(equation) + "\n" + "Hasil regresi: " + result;
            System.out.println(out);

            IOFile.askToSave(out);
        }
    }
}
