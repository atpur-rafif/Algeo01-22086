package Matrix;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MatrixCramer {
    public static double[][] calculateSolution(Matrix M, Matrix B){
        Matrix matforcalculation;
        BigDecimal rounder;
        double[][] solution;
        double det;
        var cons = new MatrixManipulator(B);
        det = MatrixDeterminant.calculateWithOBE(M);
        solution = new double[M.col][2];
        for (int i=0;i<M.col;i++){
            var edit = new MatrixManipulator(M);
            edit.setCol(i, cons.getCol(0));
            matforcalculation = edit.getResult();
            rounder = new BigDecimal(Double.toString(MatrixDeterminant.calculateWithOBE(matforcalculation)));
            rounder = rounder.setScale(5, RoundingMode.HALF_DOWN); 
            solution[i][0] = rounder.doubleValue();
            solution[i][1] = solution[i][0]/det;
        }
        return solution;
    }

    public static double[][] calculateAugmented(Matrix M){
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
