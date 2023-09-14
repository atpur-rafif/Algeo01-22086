package Matrix;

public class MatrixManipulator {
    Matrix M;

    public MatrixManipulator(Matrix M){
        this.M = M.copy();
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

    public void linearCombinationOfRow(int rowTarget, int rowSource, double multiplier){
        var target = this.getRow(rowTarget);
        var source = this.getRow(rowSource);
        for(int i = 0; i < this.M.col; ++i) target[i] += source[i] * multiplier;
        this.setRow(rowTarget, target);
    }

    public int getMostLeftNonZeroOfRow(int row){
        for(int i = 0; i < this.M.col; ++i){
            if(this.M.get(row, i) != 0) return i;
        }
        return -1;
    }

    public void gaussianElimination(){
        for(int i = 0; i < this.M.row; ++i){

            int mostLeftRow = i;
            int mostLeft = this.getMostLeftNonZeroOfRow(i);
            if(mostLeft == -1) mostLeft = Integer.MAX_VALUE;
            for (int j = i; j < this.M.row; ++j) {
                int t = this.getMostLeftNonZeroOfRow(j);
                if(t != -1 && t < mostLeft){
                    mostLeft = t;
                    mostLeftRow = j;
                }
            }
            if(mostLeftRow != i) switchRow(i, mostLeftRow);

            if (mostLeft != Integer.MAX_VALUE && this.M.get(i, mostLeft) != 0.0) {
                this.multiplyRow(i, 1 / this.M.get(i, mostLeft));
                for (int j = i + 1; j < this.M.row; ++j) {
                    this.linearCombinationOfRow(j, i, (-1) * this.M.get(j, mostLeft));
                }
            }
        }
    }

    public Matrix getResult(){
        return this.M;
    }
}
