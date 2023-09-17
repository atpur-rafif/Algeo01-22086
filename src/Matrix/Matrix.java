package Matrix;

public class Matrix{
    public int row;
    public int col;
    private double[][] data;

    public Matrix(int row, int col){
        this.data = new double[row][col];
        this.row = row;
        this.col = col;

        for(int i = 0; i < row; ++i){
            for(int j = 0; j < col; ++j){
                this.set(i, j, 0.0);
            }
        }
    }

    public double get(int row, int col){
        return this.data[row][col];
    }

    public void set(int row, int col, double newValue){
        if(newValue == -0.0) newValue = 0.0;
        this.data[row][col] = newValue;
    }

    public Matrix copy(){
        var M = new Matrix(row, col);

        for(int i = 0; i < row; ++i){
            for(int j = 0; j < col; ++j){
                M.set(i, j, this.get(i, j));
            }
        }

        return M;
    }

    
    public static Matrix createIdentityMatrix(int size){
        var I = new Matrix(size, size); 
        for(int i = 0; i < size ; ++i){
            I.set(i, i, 1);
        }

        return I;
    }
}
