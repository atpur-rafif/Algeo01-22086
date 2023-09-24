package Matrix.OBE.Type;

import Matrix.OBE.OBELog;
import Matrix.OBE.OBELogType;

public class SwitchRow extends OBELog {
    OBELogType type = OBELogType.SwitchRow;
    int first, second;
    public SwitchRow(int first, int second){
        this.first = first;
        this.second = second;
    }
}
