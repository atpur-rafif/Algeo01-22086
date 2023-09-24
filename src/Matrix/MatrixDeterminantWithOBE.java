package Matrix;

public class MatrixDeterminantWithOBE{

    public static Matrix partialPivot(Matrix M,int pivot){
        int m = M.row, idrMaks;
        var result = new MatrixManipulator(M);
        idrMaks = pivot;
        double maks = M.get(pivot,pivot);
        for (int i=pivot;i<m;i++){
            if (M.get(i,pivot)>maks){
                maks = M.get(i,pivot);
                idrMaks = i;
            }
        }
        result.switchRow(pivot, idrMaks);
        return result.getResult();

    }

    public static Matrix echelonForm(Matrix M){
        for(int i=0;i<M.col;i++){
            M = MatrixDeterminantWithOBE.partialPivot(M, i);
            var manipulator = new MatrixManipulator(M);
            for(int j=i+1;j<M.row;j++){
                manipulator.linearCombinationOfRow(j,i,-1*(M.get(j, i)/M.get(i,i)));
            }
            M = manipulator.getResult();
        }
        return M;
    }

    public static double determinantOBE(Matrix M){
        double leadingDiagonal = 1;
        for(int i=0;i<M.col;i++){
            M = MatrixDeterminantWithOBE.partialPivot(M, i);
            var manipulator = new MatrixManipulator(M);
            for(int j=i+1;j<M.row;j++){
                manipulator.linearCombinationOfRow(j,i,-1*(M.get(j, i)/M.get(i,i)));
            }
            M = manipulator.getResult();
        }
        for (int i=0;i<M.col;i++){
            leadingDiagonal = leadingDiagonal*M.get(i, i);
        }
        return Math.pow(-1,power)*leadingDiagonal;
    }
    
}
