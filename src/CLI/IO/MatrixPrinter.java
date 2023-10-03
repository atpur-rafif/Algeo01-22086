package CLI.IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException; 
import java.util.Scanner;

import Matrix.Matrix;


public class MatrixPrinter {
    static Scanner scanner = new Scanner(System.in);
    public static void print(Matrix M){
        int n = M.row, m = M.col;

        for(int i = 0; i < n; ++i){
            for(int j = 0; j < m; ++j){
                System.out.printf("%.2f ", M.get(i, j));
            }
            System.out.print("\n");
        }
    }

    public static void createMatrixFile(Matrix M, String fileName){
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
        writeMatrixfile(M, fileName);
        
    }

    public static void writeMatrixfile(Matrix M, String fileName){
        try{
            FileWriter myMatrixWrite = new FileWriter(fileName);
            int n = M.row, m = M.col;

                for(int i = 0; i < n; ++i){
                    for(int j = 0; j < m; ++j){
                        myMatrixWrite.write(String.format("%.2f ", M.get(i, j)));
                    }
                    myMatrixWrite.write("\n");
                }
            
            myMatrixWrite.close();
            System.out.println("\nHasil matrix sudah ditulis di file " + fileName);
        } 
        catch (IOException e){
            System.out.println("Ada error");
            e.printStackTrace();
        }
    }


    public static void printMatrixFileCLI(Matrix M){
        System.out.print("Masukkan nama file: ");
        String fileName = scanner.next();
        createMatrixFile(M, fileName);
    }

}
