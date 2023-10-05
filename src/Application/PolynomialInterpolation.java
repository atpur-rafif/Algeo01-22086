package Application;

import Matrix.Matrix;
import Matrix.OBE.OBERunner;
import Vector.EquationSpace;
import Vector.EuclideanSpace;
import Vector.VectorSpace;

public class PolynomialInterpolation {

    private static Matrix pointToAugmented(Matrix points){
        var m = new Matrix(points.row,points.row+1);
        for (int i = 0; i < points.row; i++) {
            for (int j = 0; j < points.row; j++) {
                m.set(i, j, Math.pow(points.get(i, 0), j));
            }
            m.set(i, points.row, points.get(i, 1));
        }

        return m;
    }

    public static EquationSpace solve(Matrix points){
        var M = pointToAugmented(points);
        var eq = new EquationSpace(M.col - 1);
        var OBE = new OBERunner(M);
        OBE.gaussJordanElimination();
        var reduced = OBE.getResult();
        for (int i=0;i<reduced.row;i++){
            eq.set(i, reduced.get(i, reduced.col-1));
        }
        return eq;
    }

    public static double approximate(EquationSpace eq, double x){
        EuclideanSpace v = new EuclideanSpace(eq.basisCount);
        for(int i = 0; i < eq.basisCount; ++i){
            v.set(i, Math.pow(x, i));
        }
        return VectorSpace.innerProduct(eq, v);
    }
}
