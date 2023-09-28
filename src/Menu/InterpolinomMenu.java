package Menu;


import java.io.FileNotFoundException;
import java.util.Scanner;

import Matrix.Matrix;
import Matrix.MatrixPrinter;
import Matrix.MatrixReader;
import Application.*;

public class InterpolinomMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void Run(){
        boolean isInterp = true; 
        PrintListMenu.clear(); 
        while(isInterp){
            PrintListMenu.Repetitive(1);
            System.out.print("> ");
            String choice = scanner.next(); 
            switch (choice){
                
                //CLI
                case "1": 
                    System.out.print("Masukkan banyak titik : ");
                    int n = Integer.parseInt(scanner.next());
                    System.out.println("Masukkan x dan y dari titik : ");
                    var Matriks = MatrixReader.readCLI(n,2);
                    System.out.print("Masukkan nilai x : ");
                    double x = Double.parseDouble(scanner.next());
                    var interpolatedfunction = PolynomialInterp.EquationString(PolynomialInterp.calculate(Matriks));
                    var interpolatedX = PolynomialInterp.interpolatedXString(Matriks, x);
                    System.out.print(interpolatedfunction);
                    System.out.print(interpolatedX);
                    break;
                //FILE
                case "2": 
                    System.out.print("Masukkan path: ");
                    var fileName = scanner.next();
                    int row = 0;
                    try {
                        row = MatrixReader.fileRow(fileName)-1;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    int col = 0;
                    try {
                        col = MatrixReader.fileCol(fileName);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    var MatriksFile = new Matrix(row, col);
                    try {
                        MatriksFile = MatrixReader.readFile(fileName,row,col);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    double[] xFile = new double[1];
                    try {
                        xFile = MatrixReader.readLastLine(fileName);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    MatrixPrinter.print(MatriksFile);
                    System.out.println("x = "+xFile[0]+"\n");
                    var interpolatedfunctionFile = PolynomialInterp.EquationString(PolynomialInterp.calculate(MatriksFile));
                    var interpolatedXFile = PolynomialInterp.interpolatedXString(MatriksFile, xFile[0]);
                    System.out.print(interpolatedfunctionFile);
                    System.out.print(interpolatedXFile);
                    break; 

                case "3": 
                    PrintListMenu.clear();
                    isInterp = false;
                    break; 
            }
        }
    }

 
}
