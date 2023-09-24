package Matrix.OBE.Type;

import Matrix.OBE.OBELog;
import Matrix.OBE.OBELogType;

public class LinearCombination extends OBELog {
    public OBELogType type = OBELogType.LinearCombination;
    public int target, source, multiplier;
    public LinearCombination(int target, int source, int multiply){
        this.target = target;
        this.source = source;
        this.multiplier = multiply;
    }
}
