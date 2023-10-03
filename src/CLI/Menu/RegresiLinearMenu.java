package CLI.Menu;

import Application.MultipleLinear;
import CLI.StringFormatter;
import CLI.IO.IOFile;
import CLI.IO.IOPrompter;
import Matrix.Matrix;
import Matrix.MatrixReader;
import Vector.*;

public class RegresiLinearMenu {
    public static void Run(){
        while(true){
            var input = IOPrompter.getBoundedInt(new String[]{
                "Multiple Regression",
                "1. CLI",
                "2. File",
                "3. Back"
            }, 1, 3);

            if(input == 3) break;


            Matrix samplePoint = null;
            EuclideanSpace testPoint = null;
            if(input == 1){
                var n = IOPrompter.getIntegerInline("Masukkan banyak peubah: ");
                var m = IOPrompter.getIntegerInline("Masukkan banyak sampel: ");
                System.out.println("Masukkan sample point");
                samplePoint = MatrixReader.readCLI(m, n + 1);
                testPoint = IOPrompter.getEuclideanVectorInline("Masukkan test point: ", n);
            } else if(input == 2){
                var t = IOFile.readObscureFormat();
                samplePoint = t.matrix;
                testPoint = t.vector;
            }


            var equation = MultipleLinear.solve(samplePoint);
            var result = MultipleLinear.approximate(equation, testPoint);
            var out = StringFormatter.multipleRegression(equation) + "\n" + "Hasil regresi: " + result;
            System.out.println(out);

            IOFile.askToSave(out);
        }
    }
}
