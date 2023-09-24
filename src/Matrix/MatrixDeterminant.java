package Matrix;

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

    public static Double calculate(Matrix M){
        double determinant = 0;
        int sign = 1; 
        
        if(M.row == 1){
            determinant = M.get(0, 0);
        }
        else{
            for(int i = 0; i < M.col; ++i){
                determinant += sign * M.get(0, i) * calculate(cofactor(M, 0, i)) ;
                sign *= (-1);
            }
        }
        return determinant; 
    }

    
}
