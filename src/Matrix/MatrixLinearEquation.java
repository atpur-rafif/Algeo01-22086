package Matrix;

import CLI.StringFormatter;
import Matrix.OBE.OBERunner;

public class MatrixLinearEquation {

    private static Matrix augmentedMatrix(Matrix MatriksKoefisien,Matrix MatriksKonstanta){
            var MatriksAugmented_editor = new MatrixDimensionManipulator(MatriksKoefisien);
            MatriksAugmented_editor.addColumnToRight(new MatrixManipulator(MatriksKonstanta).getCol(0));
            var MatriksAugmented = MatriksAugmented_editor.getResult();
            var result = new OBERunner(MatriksAugmented);
            return result.getResult();
    }

    public static String solutionAugmented(Matrix augmentedmatrix, int type){
        var koefisienMatrix = new Matrix(augmentedmatrix.row,augmentedmatrix.col-1);
        var konstantaMatrix = new Matrix(augmentedmatrix.row, 1);
        var originalbase = new MatrixManipulator(augmentedmatrix);
        var koef_edit = new MatrixManipulator(koefisienMatrix);
        var konst_edit = new MatrixManipulator(konstantaMatrix);
        for (int i=0;i<koefisienMatrix.col;i++){
            koef_edit.setCol(i,originalbase.getCol(i));
        }
        konst_edit.setCol(0, originalbase.getCol(originalbase.col-1));
        koefisienMatrix = koef_edit.getResult();
        konstantaMatrix = konst_edit.getResult();
        return solution(koefisienMatrix, konstantaMatrix, type);

    }
    public static String solution(Matrix koefisien,Matrix konstanta,int type){
        String output = "";
        var augmented = augmentedMatrix(koefisien, konstanta);
        var OBEResult = new OBERunner(augmented);
        OBEResult.gaussianElimination();
        var typesolution = MatrixLinearEquation.typeOfSolution(OBEResult.getResult());
        if (typesolution==-1){
            output += "Tidak memiliki solusi";
            return output;
        }
        else if (typesolution==0){
            switch (type){
                case 1:
                    System.out.println("\nMatrix hasil OBE : ");
                    MatrixPrinter.print(OBEResult.getResult());
                    output += "\nSolusi parametrik : \n";
                    output += MatrixLinearEquation.parametricSolution(OBEResult.getResult());
                    break;
                case 2:
                    var OBEResult2 = new OBERunner(augmented);
                    OBEResult2.gausJordanElimination();
                    System.out.println("\nMatrix hasil OBE : ");
                    MatrixPrinter.print(OBEResult2.getResult());
                    output += "\nSolusi parametrik : \n";
                    output += MatrixLinearEquation.parametricSolution(OBEResult2.getResult());
                    break;
                case 3:
                    output += "Tidak dapat memberikan solusi, terdapat pembagian dengan 0";
                    break;
            }
            
        }
        else{
            output += "\nSolusi : \n";
            switch (type){
                case 1:
                    System.out.println("\nMatrix hasil OBE : ");
                    MatrixPrinter.print(OBEResult.getResult());
                    output += gaussianEliminationSolution(OBEResult.getResult());
                    break;
                case 2:
                    System.out.println("\nMatrix hasil OBE : ");
                    var OBEResult2 = new OBERunner(augmented);
                    OBEResult2.gaussJordanElimination_v2();
                    MatrixPrinter.print(OBEResult2.getResult());
                    output += gaussJordanEliminationSolution(OBEResult2.getResult());
                    break;
                case 3:
                    output += cramerSolution(koefisien,konstanta);
                    break;
            }
            
        }
        return output;
    }

    private static int typeOfSolution(Matrix M1){
        // -1 no solution
        // 0 inf many solution
        // 1 1 exact solution
        double count_coeff = 0, count_last = 0;
        for (int i=0;i<M1.col;i++){
            if(i!=M1.col-1){
                count_coeff += M1.get(M1.row-1, i);
            }
            else{
                count_last = M1.get(M1.row-1, i);
            }
        }
        if (count_coeff==0&&count_last==0){
            return 0;
        }
        else if (count_coeff==0&&count_last!=0){
            return -1;
        }
        else{
            return 1;
        }
    }

    private static String parametricSolution(Matrix M1){
        boolean first;
        String output = "";
        for (int j=0;j<M1.row-1;j++){
            first = false;
            if (M1.get(j,j)!=0){
                String currentSubscript =  StringFormatter.createSubscript(j);
                output += ("x"+(currentSubscript)+" = ");
            }
            if ((M1.get(j, M1.col-1))!=0){
                output += ("("+(M1.get(j, M1.col-1))*1+")");
                first = true;
            }
            for (int k=j+1;k<M1.col-1;k++){
                if (M1.get(j, k)!=0){
                    if(first){
                        output += (" + ");
                    }
                    output += ("("+M1.get(j, k)*(-1));
                    String currentSubscript2 = StringFormatter.createSubscript(k);
                    output += (")x"+(currentSubscript2));
                }
            }
            if (M1.get(j,j)!=0){
            output += ("\n");
            }
        }
        return output;
    }

    private static String gaussianEliminationSolution(Matrix M1){
        double[] count;
        count = new double[M1.row];
        String output = "";
        for (int i=M1.row-1;i>=0;i--){
            count[i] = M1.get(i, M1.col-1);
            String currentSubscript = StringFormatter.createSubscript(i);
            output += "x"+currentSubscript+" = ";
            if(i==M1.row-1){
                    output += M1.get(i,M1.col-1);
                }
            else{
                output += count[i]+"-";
            }
            for (int j=M1.col-2;j>i;j--){
                if(i!=M1.row-1){
                    String currentSubscript3 = StringFormatter.createSubscript(j);
                    output += "("+M1.get(i, j)+")x"+currentSubscript3;
                    count[i] -= M1.get(i, j)*count[j];
                    if (j-1!=i){
                        output += "-";
                    }
                }
            }
            if (i!=M1.row-1){
                String currentSubscript2 = StringFormatter.createSubscript(i);
                output += "\nx"+currentSubscript2+" = "+count[i];
            }
            output += "\n";
        }
        return output;
    }

    private static String gaussJordanEliminationSolution(Matrix M1){
        String output = "";
        for (int i=0;i<M1.row;i++){
            String currentSubscript = StringFormatter.createSubscript(i);
            output += "x"+currentSubscript+" = "+M1.get(i, M1.col-1)+"\n";
        }
        return output;
    }

    private static String cramerSolution(Matrix koefisien,Matrix konstanta){
        String output = ""; 
        var solution = MatrixCramer.calculateSolution(koefisien,konstanta);
        output += "det = "+ MatrixDeterminantWithOBE.calculate(koefisien)+"\n";
        for (int i=0;i<koefisien.row;i++){
            String currentSubscript = StringFormatter.createSubscript(i);
            output += "d"+currentSubscript+" = "+solution[i][0]+"\n";
        }
        for (int i=0;i<koefisien.row;i++){
            String currentSubscript = StringFormatter.createSubscript(i);
            output += "x"+currentSubscript+" = "+solution[i][1]+"\n";
        }
        return output;
    }
}


