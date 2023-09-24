package Matrix.OBE.LogType;

import Matrix.OBE.OBELog;
import Matrix.OBE.OBELogType;

public class MultiplyRow extends OBELog{
    public OBELogType type = OBELogType.MultiplyRow;
    public int target;
    public double multiplier;
    public MultiplyRow(int target, double multiplier){
        this.target = target;
        this.multiplier = multiplier;
    }
}
