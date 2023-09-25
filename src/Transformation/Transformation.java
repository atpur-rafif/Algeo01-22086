package Transformation;

import Matrix.*;
import Vector.*;

public class Transformation {
    public Matrix matrix;

    public <T extends VectorSpace> T apply(T v){
        var r = VectorSpace.createNewBase(v);
        for(int i = 0; i < matrix.row; ++i){
            double tmp = 0;
            for(int j = 0; j < matrix.col; ++j){
                tmp += this.matrix.get(i, j) * v.get(j);
            }
            r.set(i, tmp);
        }
        return r;
    }
}
