package Matrix.OBE;

import java.util.ArrayList;
import java.util.List;
import Matrix.*;
import Matrix.OBE.LogType.*;

public class OBERunner {
    Matrix origialMatrix;
    MatrixManipulator Manipulator;
    List<OBELog> logs = new ArrayList<OBELog>();

    public OBERunner(Matrix M){
        this.origialMatrix = M.copy();
        this.Manipulator = new MatrixManipulator(M);
    }

    public void switchRow(int i, int j){
        var tmp = this.Manipulator.getRow(i);
        this.Manipulator.setRow(i, this.Manipulator.getRow(j));
        this.Manipulator.setRow(j, tmp);

        this.logs.add(new SwitchRow(i, j));
    }

    public void multiplyRow(int row, double multiplier){
        var tmp = this.Manipulator.getRow(row);
        for(int i = 0; i < this.Manipulator.col; ++i) tmp[i] *= multiplier;
        this.Manipulator.setRow(row, tmp);

        this.logs.add(new MultiplyRow(row, multiplier));
    }

    public void linearCombinationOfRow(int rowTarget, int rowSource, double multiplier){
        var target = this.Manipulator.getRow(rowTarget);
        var source = this.Manipulator.getRow(rowSource);
        for(int i = 0; i < this.Manipulator.col; ++i) target[i] += source[i] * multiplier;
        this.Manipulator.setRow(rowTarget, target);

        this.logs.add(new LinearCombination(rowTarget, rowSource, multiplier));
    }

    private int getLeadingRow(int row){
        for(int i = 0; i < this.Manipulator.M.col; ++i){
            if(this.Manipulator.get(row, i) != 0) return i;
        }
        return -1;
    }

    public void gaussianElimination(){
        for(int i = 0; i < this.Manipulator.M.row; ++i){

            int mostLeftRow = i;
            int mostLeft = this.getLeadingRow(i);
            if(mostLeft == -1) mostLeft = Integer.MAX_VALUE;
            for (int j = i; j < this.Manipulator.M.row; ++j) {
                int t = this.getLeadingRow(j);
                if(t != -1 && t < mostLeft){
                    mostLeft = t;
                    mostLeftRow = j;
                } else if(
                    t == mostLeft && 
                    (Math.abs(this.Manipulator.get(j, t)) > Math.abs(this.Manipulator.get(mostLeftRow, mostLeft)))
                ){
                    mostLeft = t;
                    mostLeftRow = j;
                }
            }
            if(mostLeftRow != i) this.switchRow(i, mostLeftRow);


            if (mostLeft != Integer.MAX_VALUE && this.Manipulator.M.get(i, mostLeft) != 0.0) {
                double multipler = this.Manipulator.get(i, mostLeft);
                if(multipler != 1) this.multiplyRow(i, 1 / multipler);
                for (int j = i + 1; j < this.Manipulator.row; ++j) {
                    this.linearCombinationOfRow(j, i, (-1) * this.Manipulator.M.get(j, mostLeft));
                }
            }
        }
    }

    public void gaussJordanElimination(){
        this.gaussianElimination();

        for(int i = this.Manipulator.row - 1; i >= 0; --i){
            int mostLeft = this.getLeadingRow(i);

            if(mostLeft != -1){
                for(int j = 0; j < i; ++j){
                    this.linearCombinationOfRow(j, i, (-1) * this.Manipulator.get(j, mostLeft));
                }
            }
        }
    }

    public Matrix getOriginal(){
        return this.origialMatrix;
    }

    public Matrix getResult(){
        return this.Manipulator.getResult();
    }

    public OBELog[] getLogs(){
        var l = new OBELog[this.logs.size()];
        for(int i = 0; i < l.length; ++i) l[i] = this.logs.get(i);
        return l;
    }
}
