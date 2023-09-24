package Matrix;

import Matrix.OBE.OBERunner;
import Matrix.OBE.LogType.MultiplyRow;
import Matrix.OBE.LogType.SwitchRow;

public class MatrixDeterminantWithOBE{

    public static double determinantOBE(Matrix A){
        double det = 1;
        var R = new OBERunner(A);
        R.gaussianElimination();
        var M = R.getResult();
        int sign = 1;
        double divider = 1;
        var logs = R.getLogs();
        for(int i = 0; i < logs.length; ++i){
            if(logs[i] instanceof SwitchRow) sign *= -1;
            else if (logs[i] instanceof MultiplyRow) {
                var log = (MultiplyRow) logs[i];
                divider *= log.multiplier;
            } 
        }
        for (int j=0;j<M.col;j++){
            det *= M.get(j, j);
        }
        return (sign*det)/divider;
    }
    
}
