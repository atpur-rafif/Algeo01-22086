package Application;

import Matrix.Matrix;
import Matrix.OBE.OBERunner;

public class PolynomialInterp {

    private static Matrix pointInput(double[][] points){
        var Mat = new Matrix(points.length,points.length+1);
        for (int i=0;i<points.length;i++){
            for (int j=0;j<points.length;j++){
                Mat.set(i, j, Math.pow(points[i][0],j));
            }
            Mat.set(i,points.length,(Math.pow(10, 10)*points[i][1]));
        }
        return Mat;
    }

    public static Matrix calculate(double[][] points){
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

    public static double f(double[][] points,double x){
        double result=0;
        var Mat = PolynomialInterp.calculate(points);
        for (int i=0;i<points.length;i++){
            result += Math.pow(x, i)*(Mat.get(i, 0))*Math.pow(10, 10);
        }
        return result/Math.pow(10, 10);
    }
    
}
