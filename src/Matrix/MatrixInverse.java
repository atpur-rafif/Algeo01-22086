package Matrix;

import Matrix.OBE.OBERunner;

public class MatrixInverse {
    public static Matrix calculateWithCofactor(Matrix M){
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
        var DimensionManipulator = new MatrixDimensionManipulator(adjoin);
        DimensionManipulator.transpose();
        return (DimensionManipulator.getResult()); 
    }
    
    private static Matrix addIdentityMatrixToRight(Matrix M){
        var I = Matrix.createIdentityMatrix(M.col);
        var ManipulatorI = new MatrixManipulator(I);
        var DimensionManipulatorM = new MatrixDimensionManipulator(M);
        var initialSize = M.col;
        for(int i = 0; i < initialSize; ++i){
            DimensionManipulatorM.addColumnToRight(ManipulatorI.getCol(i));
        }
        return DimensionManipulatorM.getResult();
    }

    private static Matrix getRightSideMatrix(Matrix M){
        var R = new Matrix(M.row, M.row);
        var ManipulatorM = new MatrixManipulator(M);
        var ManipulatorR = new MatrixManipulator(R);
        for(int i = 0; i<M.row; ++i){
            var col = ManipulatorM.getCol(i + M.row);
            ManipulatorR.setCol(i, col);
        }

        return ManipulatorR.getResult();
    }

    public static Matrix calculateWithGaussJordan(Matrix M){
        var T = addIdentityMatrixToRight(M); 
        var OBE = new OBERunner(T); 
        OBE.gausJordanElimination(); 
        return getRightSideMatrix(OBE.getResult()); 
    }
}
