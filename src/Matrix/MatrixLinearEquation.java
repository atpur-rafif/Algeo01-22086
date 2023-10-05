package Matrix;

import java.util.ArrayList;

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

    private static MatrixLinearEquationSolutionType typeOfSolution(Matrix M) {
        var obe = new OBERunner(M);
        obe.gaussianElimination();
        M = obe.getResult();

        boolean impos = false;
        int varCount = M.col - 1;
        int eqCount = 0;
        for(int i = 0; i < M.row; ++i){
            boolean allCoefZero = true;
            boolean isConstZero = M.get(i, M.col - 1) == 0;
            for(int j = 0; j < M.col - 1; ++j){
                if(M.get(i, j) != 0) allCoefZero = false;
            }
            if(allCoefZero && !isConstZero) impos = true;
            else if(!allCoefZero) eqCount += 1;
        }

        if(impos) return MatrixLinearEquationSolutionType.NoSolution;
        else if(varCount > eqCount) return MatrixLinearEquationSolutionType.ManySolution;
        else if(varCount == eqCount) return MatrixLinearEquationSolutionType.OneSolution;
        return MatrixLinearEquationSolutionType.NoSolution;
    }

    private static String parametricSolution(Matrix M) {
        var output = "";
        var varCount = M.col - 1;
        var parameterized = new boolean[varCount];
        var parameter = new String[varCount];
        for(int i = 0; i < varCount; ++i) parameterized[i] = false;

        var parameterCount = 0;
        for(int i = varCount - 1; i >= 0; --i){
            int leadingOne = -1;
            int leadingOneRow = 0;
            for(int j = M.row - 1; j >= 0; --j){
                int currentLeadingOne = -1;
                for(int k = 0; k < varCount; ++k){
                    if(M.get(j, k) == 1){
                        currentLeadingOne = k;
                        break;
                    }
                }

                if(currentLeadingOne == i){
                    leadingOne = currentLeadingOne;
                    leadingOneRow = j;
                }
            }


            if(leadingOne != -1){
                var eq = new double[M.col];
                for(int j = i; j < M.col; ++j){
                    eq[j] = M.get(leadingOneRow, j);
                }

                output += "\nBounded variable: \n";
                for(int j = 0; j < eq.length - 1; ++j){
                    output += "(" + eq[j] + ")" + "x" + IOStringFormatter.createSubscript(j);
                    if(j != eq.length - 2) output += " + ";
                }
                output += " = " + eq[eq.length - 1] + "\n";

                for(int j = leadingOneRow + 1; j < M.row; ++j){
                    var localLeading = -1;
                    for(int k = leadingOne + 1; k < varCount; ++k){
                        if(M.get(j, k) != 0){
                            localLeading = k;
                            break;
                        }
                    }

                    if(localLeading != -1){
                        var m = eq[localLeading];
                        for(int k = leadingOne + 1; k < M.col; ++k){
                            eq[k] -= M.get(j, k) * m;
                        }
                    }
                }

                output += "x" + IOStringFormatter.createSubscript(leadingOne) + " = ";

                var t = new ArrayList<String>();
                var k = eq[eq.length - 1];
                if(k != 0) t.add(k + "");
                for(int j = leadingOne + 1; j < eq.length - 1; ++j){
                    if(eq[j] != 0 && parameterized[j]){
                        t.add("(" + (eq[j] * -1) + ")" + parameter[j]);
                    }
                }

                var at = new String[t.size()];
                t.toArray(at);
                output += IOStringFormatter.combineString(at, " + ") + "\n";

            } else {
                parameterized[i] = true;
                parameterCount += 1;
                parameter[i] = "t" + IOStringFormatter.createSubscript(parameterCount);
                output += "\nFree variable: \n";
                output += "x" + IOStringFormatter.createSubscript(i);
                output += " = " + parameter[i] + "\n";
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
