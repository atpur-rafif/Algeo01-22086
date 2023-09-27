package FilePrinter;

import Matrix.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class SinglePrinterwithMatrix {
    static Scanner scanner = new Scanner(System.in);
    public static void printFile(Matrix matrix, double Result, String fileName){
            fileName = fileName + ".txt";
            File myMatrix = new File(fileName); 
            while (myMatrix.exists()){
                System.out.println("Nama file sudah ada, silahkan ganti namanya");
                System.out.print("Nama file: ");
                fileName = scanner.next();
                fileName = fileName + ".txt";
                myMatrix = new File(fileName); 
            }
            System.out.println("File " + myMatrix.getName() + " dibuat");

            //tulis hasil di file
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
                System.out.println("\nHasil Determinan sudah ditulis di file" + myMatrix.getName());
            } 
            catch (IOException e){
                System.out.println("Ada error");
                e.printStackTrace();
            }
    }
    
    public static void printFileCLI(Matrix matrix, double Result){
        System.out.print("Masukkan nama file: ");
        String fileName = scanner.next();
        printFile(matrix, Result, fileName);
    }
}
