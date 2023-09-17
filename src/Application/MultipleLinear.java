package Application;

import Matrix.*;

public class MultipleLinear{

    private static Matrix createMatrix(double[][] samplePoints) {
        int m = samplePoints.length;
        int n = samplePoints[0].length - 1;
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
                            temp += samplePoints[k][j-1];
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
                            temp += samplePoints[k][i-1];
                        }
                        matrix.set(i, j, temp);
                    }
                    else{
                        double temp = 0;
                        for(int k = 0; k < m; ++k){
                            temp += samplePoints[k][j-1] * samplePoints[k][i-1];
                        }
                        matrix.set(i, j, temp);
                    }
                }

            }
        }

        return matrix;
    }

    public static double[] solve(double[][] samplePoint){
        var M = createMatrix(samplePoint);
        var Manipulator = new MatrixManipulator(M); 
        Manipulator.gausJordanElimination();
        MatrixPrinter.print(Manipulator.getResult());
        return Manipulator.getCol(M.col - 1);
    }


}
