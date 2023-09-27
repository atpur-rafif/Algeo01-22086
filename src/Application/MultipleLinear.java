package Application;

import Matrix.*;
import Matrix.OBE.OBERunner;
import Vector.*;

public class MultipleLinear{

    private static Matrix createMatrix(Matrix samplePoints) {
        int m = samplePoints.row;
        int n = samplePoints.col - 1;
        var matrix = new Matrix(n + 1, n + 2);
        for(int i = 0; i < n + 1; ++i){
            if(i == 0){
                for(int j = 0; j < n + 2; ++j){
                    if(j == 0){
                        matrix.set(0, 0, (double) n);
                    }
                    else{
                        double temp = 0;
                        for(int k = 0; k < m; ++k){
                            temp += samplePoints.get(k, j-1);
                        }
                        matrix.set(0, j, temp);
                    }
                }
            }   
            else{
                for(int j = 0; j < n + 2; ++j){
                    if(j == 0){
                        double temp = 0;
                        for(int k = 0; k < m; ++k){
                            temp += samplePoints.get(k, i-1);
                        }
                        matrix.set(i, j, temp);
                    }
                    else{
                        double temp = 0;
                        for(int k = 0; k < m; ++k){
                            temp += samplePoints.get(k, j-1) * samplePoints.get(k, i-1);
                        }
                        matrix.set(i, j, temp);
                    }
                }

            }
        }

        return matrix;
    }

    public static EquationSpace solve(Matrix samplePoint){
        var M = createMatrix(samplePoint);
        var OBE = new OBERunner(M); 
        OBE.gausJordanElimination();
        M = OBE.getResult();
        var R = new EquationSpace(M.col - 1);
        for(int i = 0; i < R.basisCount; ++i){
            R.set(i, M.get(i, M.col - 1));
        }
        return R;
    }


    public static void DisplayEquation(EquationSpace result){
        String plus = " + ";
        for(int i = 0; i < result.basisCount; ++i){
            if(i == result.basisCount - 1){
                plus = "";
            }
            if(i == 0){
                System.out.print(result.get(i) + plus);
            }
            else{
                String currentSubscript = String.valueOf((char)('\u2080' + (i)));
                System.out.print(result.get(i) + "x" + currentSubscript + plus);
            }
        }
        System.out.println("");
    }


}
