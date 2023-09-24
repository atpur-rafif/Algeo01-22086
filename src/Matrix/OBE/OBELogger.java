package Matrix.OBE;

import Matrix.OBE.Type.*;

public abstract class OBELogger {
    OBELogType type;

    OBELogger(){
        OBELog p = new MultiplyRow(1, 2);

        if(p.type == OBELogType.MultiplyRow){
            var k = (SwitchRow) p;
        }
    }
}
