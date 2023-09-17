package Matrix;

public class MatrixInverse {
    public static Matrix calculate(Matrix M){
        var determinant = MatrixDeterminant.calculate(M);
        return MatrixArithmetic.MultiplyByConst(adjoin(M), 1/determinant); 
    }

    public static Matrix adjoin(Matrix M){
        var adjoin = new Matrix(M.row, M.row);
        for(int i = 0; i < M.col; ++i){
            for(int j = 0; j < M.row; ++j){
                var cof = MatrixDeterminant.cofactor(M, i, j);
                var sign = (i + j) % 2 == 0 ? 1 : -1;
                adjoin.set(i, j, sign * MatrixDeterminant.calculate(cof));
            }
        }
        var Manipulator = new MatrixManipulator(adjoin);
        Manipulator.transpose();
        return (Manipulator.getResult()); 
    }

}
