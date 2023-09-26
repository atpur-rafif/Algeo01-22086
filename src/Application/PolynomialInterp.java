package Application;

import Matrix.Matrix;
import Matrix.OBE.OBERunner;

public class PolynomialInterp {

    private static Matrix pointInput(Matrix points){
        var Mat = new Matrix(points.row,points.row+1);
        for (int i=0;i<points.row;i++){
            for (int j=0;j<points.row;j++){
                Mat.set(i, j, Math.pow(points.get(i,0),j));
            }
            Mat.set(i,points.row,(Math.pow(10, 10)*points.get(i,1)));
        }
        return Mat;
    }

    public static Matrix calculate(Matrix points){
        var M = pointInput(points);
        var Mat = new Matrix(M.col-1,1);
        var OBE = new OBERunner(M);
        OBE.gaussJordanElimination_v2();
        var reduced = OBE.getResult();
        for (int i=0;i<reduced.row;i++){
            Mat.set(i, 0, Math.round(reduced.get(i, reduced.col-1))/Math.pow(10, 10));
        }
        return Mat;
    }

    public static double f(Matrix points,double x){
        double result=0;
        var Mat = PolynomialInterp.calculate(points);
        for (int i=0;i<points.row;i++){
            result += Math.pow(x, i)*(Mat.get(i, 0))*Math.pow(10, 10);
        }
        return result/Math.pow(10, 10);
    }
    
}
