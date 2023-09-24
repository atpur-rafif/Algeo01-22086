package Matrix.OBE.Type;

import Matrix.OBE.OBELog;
import Matrix.OBE.OBELogType;

public class MultiplyRow extends OBELog{
    OBELogType type = OBELogType.MultiplyRow;
    int target, multiplier;
    public MultiplyRow(int target, int multiplier){
        this.target = target;
        this.multiplier = multiplier;
    }
}
