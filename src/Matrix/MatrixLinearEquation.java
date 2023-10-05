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

        if(typesolution == MatrixLinearEquationSolutionType.NoSolution){
            output += "Tidak memiliki solusi";
            return output;
        } else if(typesolution == MatrixLinearEquationSolutionType.OneSolution){
            output += "Terdapat satu solusi\n";
        } else if(typesolution == MatrixLinearEquationSolutionType.ManySolution){
            output += "Terdapat banyak solusi\n";
        }


        var obe = new OBERunner(augmented);
        if(type == MatrixLinearEquationMethodType.Gaussian) obe.gaussianElimination();
        else if(type == MatrixLinearEquationMethodType.GausJordan) obe.gaussJordanElimination();

        Matrix m = obe.getResult();
        if(type == MatrixLinearEquationMethodType.Gaussian || type == MatrixLinearEquationMethodType.GausJordan){
            output += "Matrix setelah OBE: \n";
            output += IOStringFormatter.matrix(m);
            output += "\n";
        }

        if(typesolution == MatrixLinearEquationSolutionType.OneSolution){
            if(type == MatrixLinearEquationMethodType.Gaussian || type == MatrixLinearEquationMethodType.GausJordan){
                output += runSolution(m);
            } else if(type == MatrixLinearEquationMethodType.Crammer){
                output += cramerSolution(koefisien, konstanta);
            } else if(type == MatrixLinearEquationMethodType.Inverse){
                output += inverseSolution(koefisien, konstanta);
            }
        } else if(typesolution == MatrixLinearEquationSolutionType.ManySolution){
            if(type == MatrixLinearEquationMethodType.Gaussian || type == MatrixLinearEquationMethodType.GausJordan){
                output += MatrixLinearEquation.runSolution(m);
            } else if(type == MatrixLinearEquationMethodType.Crammer){
                output += "Tidak dapat memberikan solusi, terdapat pembagian dengan 0, atau terdapat matrix yang tidak memiliki balikan";
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

    private static String runSolution(Matrix M) {
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
                for(int j = leadingOne + 1; j < eq.length - 1; ++j){
                    if(eq[j] != 0 && parameterized[j]){
                        t.add("(" + (eq[j] * -1) + ")" + parameter[j]);
                    }
                }
                if(k != 0 || t.size() == 0) t.add(k + "");

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
