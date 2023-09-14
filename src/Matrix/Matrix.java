package Matrix;

public class Matrix{
    int row;
    int col;
    private double[][] data;

    public Matrix(int row, int col){
        this.data = new double[row][col];
        this.row = row;
        this.col = col;

        int i, j;
        for(i = 0; i < row; ++i){
            for(j = 0; j < col; ++j){
                this.set(i, j, 0.0);
            }
        }
    }

    double get(int i, int j){
        return this.data[i][j];
    }

    void set(int i, int j, double newValue){
        this.data[i][j] = newValue;
    }
}
