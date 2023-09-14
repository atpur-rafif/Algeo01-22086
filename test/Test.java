import Matrix.*;

public class Test {
    public static void main(String[] args){
        var M = MatrixReader.read();
        MatrixPrinter.print(M);

        var Manipulator = new MatrixManipulator(M);
        Manipulator.linearCombinationOfRow(0, 1, 1);

        System.out.println("");
        MatrixPrinter.print(Manipulator.getResult());
    }
}