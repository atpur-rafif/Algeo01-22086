package Matrix;

public class MatrixDimensionManipulator {
    Matrix M; 

    public MatrixDimensionManipulator(Matrix M){
        this.M = M.copy(); 
    }
    public void transpose(){
        var result = new Matrix(this.M.row, this.M.col);
        for(int i = 0; i < M.row; ++i){
            for(int j = 0; j < M.col; ++j){
                result.set(i, j, this.M.get(j, i));
            }
        }
        this.M = result; 

    }

    public void addRowToBelow(double[] row){
        var newM = new Matrix(this.M.row + 1, this.M.col);
        for(int i = 0; i < this.M.row; ++i){
            for(int j = 0; j < this.M.col; ++j){
                newM.set(i, j, this.M.get(i, j));
            }
        }
        var Manipulator = new MatrixManipulator(newM); 
        Manipulator.setRow(this.M.row, row);

        this.M = Manipulator.getResult();
    }
    public void addColumnToRight(double[] col){
        var newM = new Matrix(this.M.row, this.M.col + 1); 
        for(int i = 0; i < this.M.row; ++i){
            for(int j = 0; j < this.M.col; ++j){
                newM.set(i, j, this.M.get(i, j));
            }
        }
        var Manipulator = new MatrixManipulator(newM); 
        Manipulator.setCol(this.M.col, col);

        this.M = Manipulator.getResult();
    
    }
    public Matrix getResult(){
        return this.M; 
    }
}
