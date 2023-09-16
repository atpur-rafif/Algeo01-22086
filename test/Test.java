import Matrix.*;

public class Test {
    public static void main(String[] args){
        var M = MatrixReader.read();

        var m = MatrixReader.read();

        var result = MatrixArithmetic.Multiply(M, m);
        

        
        MatrixPrinter.print(result);

        // var Manipulator = new MatrixManipulator(M);
        // Manipulator.gausJordanElimination();

        // System.out.println("");
        // MatrixPrinter.print(Manipulator.getResult());
    }
}