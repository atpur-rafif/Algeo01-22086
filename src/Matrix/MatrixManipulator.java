package Matrix;

public class MatrixManipulator {
    public Matrix M;

    public MatrixManipulator(Matrix M){
        this.M = M.copy();
    }

    public double get(int row, int col){
        return this.M.get(row, col);
    }

    public void set(int row, int col, double newValue){
        this.M.set(row, col, newValue);
    }

    public double[] getRow(int row){
        var r = new double[this.M.col];
        for(int i = 0; i < this.M.col; ++i) r[i] = this.M.get(row, i);
        return r;
    }

    public void setRow(int row, double[] rows){
        for(int i = 0; i < this.M.col; ++i) this.M.set(row, i, rows[i]);
    }

    public double[] getCol(int col){
        var c = new double[this.M.row];
        for(int i = 0; i < this.M.row; ++i) c[i] = this.M.get(i, col);
        return c;
    }

    public void setCol(int col, double[] cols){
        for(int i = 0; i < this.M.row; ++i) this.M.set(i, col, cols[i]);
    }

    public Matrix getResult(){
        return this.M;
    }
}
