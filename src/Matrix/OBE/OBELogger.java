package Matrix.OBE;

import Matrix.OBE.Type.*;

public abstract class OBELogger {
    OBELogType type;

    OBELogger(){
        OBELog p = new SwitchRow(1, 2);

        if(p.type == OBELogType.SwitchRow){
            SwitchRow k = (SwitchRow) p;
        }
    }
}
