package Point;

import Matrix.Matrix;
import Matrix.MatrixArithmetic;

public class Transformation {
    public Matrix matrix;

    public Vector apply(Vector v){
        return Vector.fromMatrix(MatrixArithmetic.Multiply(matrix, Vector.toMatrix(v)));
    }
}
