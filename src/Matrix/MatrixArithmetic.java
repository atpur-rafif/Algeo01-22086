package Matrix;

public class MatrixArithmetic {
    Matrix M1, M2;
    
    public MatrixArithmetic(Matrix M1, Matrix M2){
        this.M1 = M1.copy(); 
        this.M2 = M2.copy(); 
    }

    public static Matrix Addition(Matrix M1, Matrix M2){
        
        Matrix Mresult = new Matrix(M1.row, M1.col);
        for(int i = 0; i < M1.row; ++i){
            for(int j = 0; j < M1.col; ++j){
                double temp = M1.get(i, j) + M2.get(i, j);
                Mresult.set(i, j, temp);
            }
        }
        return Mresult;

    }
    public static Matrix MultiplyByConst(Matrix M, double Constant){
        var Mresult = new Matrix(M.row, M.col);
        for(int i = 0; i < M.row; ++i){
            for(int j = 0; j < M.col; ++j){
                double temp = M.get(i, j) * Constant; 
                Mresult.set(i, j, temp);
            }
        }
        return Mresult;
    }

    public static Matrix Substraction(Matrix M1, Matrix M2){
        return Addition(M1, MultiplyByConst(M2, -1));
    }

    public static Matrix Multiply(Matrix M1, Matrix M2){
        var Mresult = new Matrix(M1.row, M2.col); 
        for(int i = 0; i < Mresult.row; ++i){
            for(int j = 0; j < Mresult.col; ++j){
                var temp = 0;
                for(int k = 0; k < M1.col; ++k){
                    temp += M1.get(i, k) * M2.get(k, j);
                }
                Mresult.set(i, j, temp);
            }
        }
        return Mresult; 
    }


}
