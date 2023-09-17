import Matrix.*;

public class Test {
    public static void main(String[] args){
        var M = MatrixReader.read();


        var result = MatrixDeterminant.calculate(M);


        
        System.out.println(result);

        // var Manipulator = new MatrixManipulator(M);
        // Manipulator.gausJordanElimination();

        // System.out.println("");
        // MatrixPrinter.print(Manipulator.getResult());
    }
}