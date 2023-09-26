package Matrix;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException; 
import java.util.Scanner;


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

    public static void printMatrixFile(Matrix M, String fileName){
            try{
                File myMatrix = new File(fileName); 
                fileName = fileName + ".txt";
                while (myMatrix.createNewFile()){
                    System.out.println("Nama file sudah ada, silahkan ganti namanya");
                    fileName = scanner.next();
                }
                System.out.println("Result Generated in " + myMatrix.getName());
            }
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            //tulis hasil di file
            try{
                FileWriter myMatrix = new FileWriter(fileName);
                int n = M.row, m = M.col;

                    for(int i = 0; i < n; ++i){
                        for(int j = 0; j < m; ++j){
                            myMatrix.write(String.format("%.2f ", M.get(i, j)));
                        }
                        myMatrix.write("\n");
                    }
                
                myMatrix.close();
                System.out.println("Matrix sudah ditulis");
            } 
            catch (IOException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }

}
