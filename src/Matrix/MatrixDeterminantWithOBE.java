package Matrix;

import Matrix.OBE.OBERunner;

public class MatrixDeterminantWithOBE{

    public static OBERunner partialPivot(OBERunner M,int pivot){
        int row, idrMax;
        double elMax;
        row = M.getOriginal().row;
        elMax = M.getOriginal().get(pivot, pivot);
        idrMax = pivot;
        for (int i=0;i<row;i++){
            if (elMax<M.getOriginal().get(i,pivot)){
                elMax = M.getOriginal().get(i,pivot);
                idrMax = i;
            }
        }
        M.switchRow(pivot, idrMax);
        return M;
    }

    public static OBERunner echelonForm(OBERunner M){
        for (int i=0;i<M.getOriginal().col;i++){
            M = MatrixDeterminantWithOBE.partialPivot(M, i);
            for (int j=i+1;i<M.getOriginal().row;j++){
                M.linearCombinationOfRow(j,i,-1*((M.getOriginal().get(j, i))/(M.getOriginal().get(i,i))));
            }
        }
        return M;
    }

    public static double determinantOBE(Matrix A){
        var M = new OBERunner(A);
        double leadingDiagonal = 1;
        M = MatrixDeterminantWithOBE.echelonForm(M);
        for (int i=0;i<A.col;i++){
            leadingDiagonal = leadingDiagonal*M.getOriginal().get(i, i);
        }
        
        return Math.pow(-1,1)*leadingDiagonal;
    }
    
}
