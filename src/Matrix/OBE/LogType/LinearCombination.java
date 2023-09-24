package Matrix.OBE.LogType;

import Matrix.OBE.OBELog;
import Matrix.OBE.OBELogType;

public class LinearCombination extends OBELog {
    public OBELogType type = OBELogType.LinearCombination;
    public int target, source;
    public double multiplier;
    public LinearCombination(int target, int source, double multiplier){
        this.target = target;
        this.source = source;
        this.multiplier = multiplier;
    }
}
