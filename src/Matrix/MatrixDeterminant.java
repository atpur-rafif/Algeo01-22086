package Matrix;

import Matrix.OBE.OBERunner;
import Matrix.OBE.LogType.MultiplyRow;
import Matrix.OBE.LogType.SwitchRow;

public class MatrixDeterminant {

    public static Matrix cofactor(Matrix M, int Row, int Col){
        var cofMatrix = new Matrix(M.row - 1, M.col - 1);
        int row = 0, col = 0; 
        for(int i = 0; i < M.row; ++i){
            if(i == Row){
                continue;
            }
            for(int j = 0; j< M.col; ++j){
                if(j == Col){
                    continue;
                }
                cofMatrix.set(row, col, M.get(i, j));
                ++col; 
            }
            ++row;
            col = 0;
        }
        return cofMatrix;
    }

    public static double calculateWithOBE(Matrix A) {
        double det = 1;
        var R = new OBERunner(A);
        R.gaussianElimination();
        var M = R.getResult();
        int sign = 0;
        double divider = 1;
        var logs = R.getLogs();
        for(int i = 0; i < logs.length; ++i){
            if(logs[i] instanceof SwitchRow) sign += 1;
            else if (logs[i] instanceof MultiplyRow) {
                var log = (MultiplyRow) logs[i];
                divider *= log.multiplier;
            } 
        }
        for (int j=0;j<M.col;j++){
            det *= M.get(j, j);
        }
        return (det*Math.pow(-1, sign))/divider;
    }
    
    public static Double calculateWithCofactor(Matrix M){
        double determinant = 0;
        int sign = 1; 
        
        if(M.row == 1){
            determinant = M.get(0, 0);
        }
        else{
            for(int i = 0; i < M.col; ++i){
                determinant += sign * M.get(0, i) * calculateWithCofactor(cofactor(M, 0, i)) ;
                sign *= (-1);
            }
        }
        return determinant; 
    }
}
