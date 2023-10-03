package Application;

import Matrix.Matrix;
import Matrix.OBE.OBERunner;
import Menu.StringFormatter;
import Vector.EquationSpace;

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

    public static EquationSpace calculate(Matrix points){
        var M = pointInput(points);
        var Mat = new EquationSpace(M.col-1);
        var OBE = new OBERunner(M);
        OBE.gaussJordanElimination_v2();
        var reduced = OBE.getResult();
        for (int i=0;i<reduced.row;i++){
            Mat.set(i, Math.round(reduced.get(i, reduced.col-1))/Math.pow(10, 10));
        }
        return Mat;
    }

    public static double f(Matrix points,double x){
        double result=0;
        var Mat = PolynomialInterp.calculate(points);
        for (int i=0;i<points.row;i++){
            result += Math.pow(x, i)*(Mat.get(i))*Math.pow(10, 10);
        }
        return result/Math.pow(10, 10);
    }

    public static String EquationString(EquationSpace result){
        String plus = " + ";
        String output = "f(x) = ";
        for(int i = result.basisCount-1; i >= 0; i--){
            if(i == 0){
                plus = "";
            }
            if(i == 0){
                output += (result.get(i) + plus);
            }
            else{
                String currentPower = StringFormatter.createSuperscript(i);
                output += (result.get(i) + "x" + currentPower + plus);
            }
        }
        output += ("\n");
        return output;
    }

    public static String interpolatedXString(Matrix M,double x){
        return "f("+x+") = "+ f(M, x)+"\n";
    }
    
}
