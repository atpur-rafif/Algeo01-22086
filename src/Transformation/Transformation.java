package Transformation;

import Matrix.*;
import Vector.*;

public class Transformation {
    public Matrix matrix;

    public VectorSpace apply(VectorSpace v){
        var r = new VectorSpace(matrix.row);
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
