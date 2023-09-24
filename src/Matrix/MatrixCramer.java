package Matrix;

public class MatrixCramer {
    public static double[] calculateSolution(Matrix M, Matrix B){
        Matrix matforcalculation;
        double[] solution;
        double det;
        var cons = new MatrixManipulator(B);
        det = MatrixDeterminant.calculate(M);
        solution = new double[M.col];
        for (int i=0;i<M.col;i++){
            var edit = new MatrixManipulator(M);
            edit.setCol(i, cons.getCol(0));
            matforcalculation = edit.getResult();
            solution[i] = MatrixDeterminant.calculate(matforcalculation)/det;
        }
        return solution;
    }

    public static double[] calculateAugmented(Matrix M){
        var A = new Matrix(M.row,M.col-1);
        var B = new Matrix(M.row,1);
        var base = new MatrixManipulator(M);
        var newA = new MatrixManipulator(A);
        var newB = new MatrixManipulator(B);

        for (int i=0;i<A.col;i++){
            newA.setCol(i,base.getCol(i));
        }
        newB.setCol(0, base.getCol(M.col-1));

        A = newA.getResult();
        B = newB.getResult();

        return calculateSolution(A, B);
    }

}
