package Matrix;

import CLI.IO.IOStringFormatter;
import Matrix.OBE.OBERunner;

public class MatrixLinearEquation {
    private static Matrix augmentedMatrix(Matrix MatriksKoefisien, Matrix MatriksKonstanta) {
        var MatriksAugmented_editor = new MatrixDimensionManipulator(MatriksKoefisien);
        MatriksAugmented_editor.addColumnToRight(new MatrixManipulator(MatriksKonstanta).getCol(0));
        var MatriksAugmented = MatriksAugmented_editor.getResult();
        var result = new OBERunner(MatriksAugmented);
        return result.getResult();
    }

    public static String solutionAugmented(Matrix augmentedmatrix, MatrixLinearEquationMethodType type) {
        var koefisienMatrix = new Matrix(augmentedmatrix.row, augmentedmatrix.col - 1);
        var konstantaMatrix = new Matrix(augmentedmatrix.row, 1);
        var originalbase = new MatrixManipulator(augmentedmatrix);
        var koef_edit = new MatrixManipulator(koefisienMatrix);
        var konst_edit = new MatrixManipulator(konstantaMatrix);
        for (int i = 0; i < koefisienMatrix.col; i++) {
            koef_edit.setCol(i, originalbase.getCol(i));
        }
        konst_edit.setCol(0, originalbase.getCol(originalbase.col - 1));
        koefisienMatrix = koef_edit.getResult();
        konstantaMatrix = konst_edit.getResult();
        return solution(koefisienMatrix, konstantaMatrix, type);
    }

    public static String solution(Matrix koefisien, Matrix konstanta, MatrixLinearEquationMethodType type) {
        String output = "";
        var augmented = augmentedMatrix(koefisien, konstanta);
        var typesolution = typeOfSolution(augmented);

        var obe = new OBERunner(augmented);
        if(type == MatrixLinearEquationMethodType.Gaussian) obe.gaussianElimination();
        else if(type == MatrixLinearEquationMethodType.GausJordan) obe.gaussJordanElimination();

        Matrix m = obe.getResult();
        if(type == MatrixLinearEquationMethodType.Gaussian || type == MatrixLinearEquationMethodType.GausJordan){
            output += "Matrix setelah OBE: \n";
            output += IOStringFormatter.matrix(m);
            output += "\n";
        }

        if(typesolution == MatrixLinearEquationSolutionType.NoSolution){
            output += "Tidak memiliki solusi";
        } else if(typesolution == MatrixLinearEquationSolutionType.OneSolution){
            if(type == MatrixLinearEquationMethodType.Gaussian){
                output += gaussianEliminationSolution(m);
            } else if(type == MatrixLinearEquationMethodType.GausJordan){
                output += gaussJordanEliminationSolution(m);
            } else if(type == MatrixLinearEquationMethodType.Crammer){
                output += cramerSolution(koefisien, konstanta);
            } else if(type == MatrixLinearEquationMethodType.Inverse){
                output += inverseSolution(koefisien, konstanta);
            }
        } else if(typesolution == MatrixLinearEquationSolutionType.ManySolution){
            if(type == MatrixLinearEquationMethodType.Gaussian || type == MatrixLinearEquationMethodType.GausJordan){
                output += MatrixLinearEquation.parametricSolution(m);
            } else if(type == MatrixLinearEquationMethodType.Crammer){
                output += "Tidak dapat memberikan solusi, terdapat pembagian dengan 0";
            } else if(type == MatrixLinearEquationMethodType.Inverse){
                output += "Tidak dapat memberikan solusi, matriks tidak memiliki balikan";
            }
        }

        return output;
    }

    private static MatrixLinearEquationSolutionType typeOfSolution(Matrix M1) {
        var obe = new OBERunner(M1);
        obe.gaussianElimination();
        M1 = obe.getResult();

        boolean impos = false;
        int var_count = M1.col - 1;
        int eq_count = 0;
        for(int i = 0; i < M1.row; ++i){
            boolean allCoefZero = true;
            boolean isConstZero = M1.get(i, M1.col - 1) == 0;
            for(int j = 0; j < M1.col - 1; ++j){
                if(M1.get(i, j) != 0) allCoefZero = false;
            }
            if(allCoefZero && !isConstZero) impos = true;
            else if(!allCoefZero) eq_count += 1;
        }

        if(impos) return MatrixLinearEquationSolutionType.NoSolution;
        else if(var_count > eq_count) return MatrixLinearEquationSolutionType.ManySolution;
        else if(var_count == eq_count) return MatrixLinearEquationSolutionType.OneSolution;
        return MatrixLinearEquationSolutionType.NoSolution;
    }

    private static String parametricSolution(Matrix M1) {
        boolean first;
        String output = "";
        for (int j = 0; j < M1.row - 1; j++) {
            first = false;
            if (M1.get(j, j) != 0) {
                String currentSubscript = IOStringFormatter.createSubscript(j);
                output += ("x" + (currentSubscript) + " = ");
            }
            if ((M1.get(j, M1.col - 1)) != 0) {
                output += ("(" + (M1.get(j, M1.col - 1)) * 1 + ")");
                first = true;
            }
            for (int k = j + 1; k < M1.col - 1; k++) {
                if (M1.get(j, k) != 0) {
                    if (first) {
                        output += (" + ");
                    }
                    output += ("(" + M1.get(j, k) * (-1));
                    String currentSubscript2 = IOStringFormatter.createSubscript(k);
                    output += (")x" + (currentSubscript2));
                }
            }
            if (M1.get(j, j) != 0) {
                output += ("\n");
            }
        }
        return output;
    }

    private static String gaussianEliminationSolution(Matrix M1) {
        double[] count;
        count = new double[M1.row];
        String output = "";
        for (int i = M1.row - 1; i >= 0; i--) {
            count[i] = M1.get(i, M1.col - 1);
            String currentSubscript = IOStringFormatter.createSubscript(i);
            output += "x" + currentSubscript + " = ";
            if (i == M1.row - 1) {
                output += M1.get(i, M1.col - 1);
            } else {
                output += count[i] + "-";
            }
            for (int j = M1.col - 2; j > i; j--) {
                if (i != M1.row - 1) {
                    String currentSubscript3 = IOStringFormatter.createSubscript(j);
                    output += "(" + M1.get(i, j) + ")x" + currentSubscript3;
                    count[i] -= M1.get(i, j) * count[j];
                    if (j - 1 != i) {
                        output += "-";
                    }
                }
            }
            if (i != M1.row - 1) {
                String currentSubscript2 = IOStringFormatter.createSubscript(i);
                output += "\nx" + currentSubscript2 + " = " + count[i];
            }
            output += "\n";
        }
        return output;
    }

    private static String gaussJordanEliminationSolution(Matrix M1) {
        String output = "";
        for (int i = 0; i < M1.row; i++) {
            String currentSubscript = IOStringFormatter.createSubscript(i);
            output += "x" + currentSubscript + " = " + M1.get(i, M1.col - 1) + "\n";
        }
        return output;
    }

    private static String cramerSolution(Matrix koefisien, Matrix konstanta) {
        String output = "";
        var solution = MatrixCramer.calculateSolution(koefisien, konstanta);
        output += "det = " + MatrixDeterminant.calculateWithOBE(koefisien) + "\n";
        for (int i = 0; i < koefisien.row; i++) {
            String currentSubscript = IOStringFormatter.createSubscript(i);
            output += "d" + currentSubscript + " = " + solution[i][0] + "\n";
        }
        for (int i = 0; i < koefisien.row; i++) {
            String currentSubscript = IOStringFormatter.createSubscript(i);
            output += "x" + currentSubscript + " = " + solution[i][1] + "\n";
        }
        return output;
    }

    private static String inverseSolution(Matrix koefisien, Matrix konstanta){
        String output = "";
        var invers = MatrixInverse.calculateWithGaussJordan(koefisien);
        var solution = MatrixArithmetic.Multiply(invers, konstanta);
        output += "Matriks Balikan Koefisien :\n";
        output += IOStringFormatter.matrix(invers);
        output +="\n\n";
        output += "Matriks Konstanta :\n";
        output += IOStringFormatter.matrix(konstanta);
        output += "\n\n";
        for(int i=0;i<solution.row;i++){
            var subscript = IOStringFormatter.createSubscript(i);
            output += "x"+subscript+" = ";
            output += solution.get(i, 0);
            output += "\n";
        }
        return output;
    }
}
