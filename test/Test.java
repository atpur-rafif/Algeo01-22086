import Matrix.*;
import Matrix.OBE.OBERunner;

public class Test {
    public static void main(String[] args){
        var m = MatrixReader.read();
        var r = new OBERunner(m);
        r.gausJordanElimination();
        MatrixPrinter.print(r.getResult());

        var k = r.getLogs();
        for(int i = 0; i < k.length; ++i){
            System.out.println(k[i]);
        }
    }
}