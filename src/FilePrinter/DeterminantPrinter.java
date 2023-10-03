package FilePrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import CLI.IO.IOFile;
import Matrix.*;


public class DeterminantPrinter {
    static Scanner scanner = new Scanner(System.in);
    public static void printFileDeterminant(Matrix matrix, double Result, String fileName){
        IOFile.createFile(fileName);
        try{
            FileWriter myMatrixWrite = new FileWriter(fileName);
            //print hasil determinant
            int n = matrix.row, m = matrix.col;
            for(int i = 0; i < n; ++i){
                for(int j = 0; j < m; ++j){
                    myMatrixWrite.write(String.format("%.2f ", matrix.get(i, j)));
                }
                myMatrixWrite.write("\n");
            }
            MatrixPrinter.writeMatrixfile(matrix, fileName);
            myMatrixWrite.write(String.format("Determinan: %.2f ", Result));
            myMatrixWrite.close();
            System.out.println("\nHasil Determinan sudah ditulis di file " + fileName);
        } 
        catch (IOException e){
            System.out.println("Ada error");
            e.printStackTrace();
        }
    }
    
    public static void printFileCLI(Matrix matrix, double Result){
        System.out.print("Masukkan nama file: ");
        String fileName = scanner.next();
        printFileDeterminant(matrix, Result, fileName);
    }
}
