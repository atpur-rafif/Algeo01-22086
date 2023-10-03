package Matrix;

import CLI.IO.MatrixPrinter;
import Matrix.OBE.OBERunner;
import Matrix.OBE.LogType.MultiplyRow;
import Matrix.OBE.LogType.SwitchRow;

public class MatrixDeterminantWithOBE{

    public static double calculate(Matrix A){
        double det = 1;
        var R = new OBERunner(A);
        R.gaussianElimination_v2();
        var M = R.getResult();
        int sign = 0;
        double divider = 1;
        var logs = R.getLogs();
        for(int i = 0; i < logs.length; ++i){
            if(logs[i] instanceof SwitchRow) sign += 1;
            else if (logs[i] instanceof MultiplyRow) {
                var log = (MultiplyRow) logs[i];
                divider *= log.multiplier;
            } 
        }
        for (int j=0;j<M.col;j++){
            det *= M.get(j, j);
        }
        MatrixPrinter.print(R.getResult());
        return (det*Math.pow(-1, sign))/divider;
    }
    
}
