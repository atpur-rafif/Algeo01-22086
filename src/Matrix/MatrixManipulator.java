package Matrix;

public class MatrixManipulator {
    Matrix M;

    public MatrixManipulator(Matrix M){
        this.M = M.copy();
    }

    public double[] getRow(int row){
        var r = new double[this.M.row];
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

    public void switchRow(int i, int j){
        var tmp = this.getRow(i);
        this.setRow(i, this.getRow(j));
        this.setRow(j, tmp);
    }

    public void multiplyRow(int row, double multiplier){
        var tmp = this.getRow(row);
        for(int i = 0; i < this.M.col; ++i) tmp[i] *= multiplier;
        this.setRow(row, tmp);
    }

    public void linearCombinationOfRow(int rowTarget, int rowSource, int multiplier){
        var target = this.getRow(rowTarget);
        var source = this.getRow(rowSource);
        for(int i = 0; i < this.M.col; ++i) target[i] += source[i] * multiplier;
        this.setRow(rowTarget, target);
    }

    public Matrix getResult(){
        return this.M;
    }
}
