/*package Matrix;

public class MatrixCramer {


    public static double[] cramer(Matrix M, Matrix B){
        double[] solution; 
        double det;
        det = MatrixDeterminantWithOBE.determinantOBE(M);
        var constant = new MatrixManipulator(B).getCol(0);
        solution = new double[M.col];
        for (int i=0;i<M.col;i++){
            var solution_i = new MatrixManipulator(M);
            solution_i.setCol(i,constant);
            solution[i] = (MatrixDeterminantWithOBE.determinantOBE(solution_i.getResult()))/det;
        }
        return solution;
    }
}*/
