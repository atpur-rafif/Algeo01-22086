package Matrix.OBE.Type;

import Matrix.OBE.OBELog;
import Matrix.OBE.OBELogType;

public class MultiplyRow extends OBELog{
    public OBELogType type = OBELogType.MultiplyRow;
    public int target, multiplier;
    public MultiplyRow(int target, int multiplier){
        this.target = target;
        this.multiplier = multiplier;
    }
}
