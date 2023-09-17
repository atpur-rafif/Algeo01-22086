import Matrix.*;

public class Test {
    public static void main(String[] args){
        var M = MatrixReader.read();




        
        var Manipulator = new MatrixManipulator(M); 
        Manipulator.gausJordanElimination();
        MatrixPrinter.print(Manipulator.getResult());
        

        // var Manipulator = new MatrixManipulator(M);
        // Manipulator.gausJordanElimination();

        // System.out.println("");
        // MatrixPrinter.print(Manipulator.getResult());
    }
}