package Matrix.OBE.LogType;

import Matrix.OBE.OBELog;
import Matrix.OBE.OBELogType;

public class SwitchRow extends OBELog {
    public OBELogType type = OBELogType.SwitchRow;
    public int first, second;
    public SwitchRow(int first, int second){
        this.first = first;
        this.second = second;
    }
}
