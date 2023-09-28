package Matrix;

import java.util.Scanner;
import java.io.*;

public class MatrixReader {
    static Scanner cliScanner = new Scanner(System.in);
    static BufferedReader file;

    public static Matrix read(Scanner s){
        int row = Integer.parseInt(s.next());
        int col = Integer.parseInt(s.next());
        return read(s, row, col);
    }

    public static Matrix read(Scanner s, int row, int col){
        var M = new Matrix(row, col);
        for(int i = 0; i < row; ++i){
            for(int j = 0; j < col; ++j){
                M.set(i, j, Double.parseDouble(s.next()));
            }
        }

        return M;
    }
    
    public static Matrix readCLI(){
        System.out.print("Masukkan baris: ");
        int row = Integer.parseInt(cliScanner.next());
        
        System.out.print("Masukkan kolom: ");
        int col = Integer.parseInt(cliScanner.next());
        
        return read(cliScanner, row, col);
    }
    
    public static Matrix readCLI(int row, int col){
        return read(cliScanner, row, col);
    }

    public static Matrix readFile(String fileName) throws FileNotFoundException{
        int row = fileRow(fileName);
        int col = fileCol(fileName);
        var M = new Matrix(0, 0);
        file = new BufferedReader(new FileReader(fileName));
        var fileScanner = new Scanner(file);
        M = read(fileScanner,row,col);
        fileScanner.close();
        return M;
    }

    public static int fileRow(String fileName) throws FileNotFoundException{
        var file = new FileReader(fileName);
        var lines = new LineNumberReader(file);
        try {
            lines.skip(Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int row = lines.getLineNumber();
        try {
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return row;
    }

    public static int fileCol(String filename) throws FileNotFoundException{
        var file = new BufferedReader(new FileReader(filename));
        String firstline = "";
        try {
            firstline = file.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int col = 1;
        for (Character c : firstline.toCharArray()){
            if (c==' '){
                col++;
            }
        }
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return col;
        
    }

        public static Matrix readFile(String fileName, int row, int col) throws FileNotFoundException{
        var M = new Matrix(row, col);
        file = new BufferedReader(new FileReader(fileName));
        var fileScanner = new Scanner(file);
        M = read(fileScanner,row,col);
        fileScanner.close();
        return M;
    }

    public static Matrix readFileCLI() {
        System.out.print("Masukkan path: ");
        var fileName = cliScanner.next();
        var M = new Matrix(0, 0);
        boolean isReadFileSucceed = false;
        while (!isReadFileSucceed) {
            try {
                M = readFile(fileName);
                isReadFileSucceed = true;
            } catch (IOException e) {
                System.out.println("Tidak bisa membaca file");
                System.out.println("Masukkan Path lagi? (Y/N)");
                System.out.print("> ");
                String choice = cliScanner.next();
                if (choice.equals("Y") || choice.equals("y")) {
                    System.out.print("Masukkan Path lagi: ");
                    fileName = cliScanner.next();
                } else if (choice.equals("N") || choice.equals("n")) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Format File salah");
                System.out.print("Masukkan Path lagi: ");
                System.out.println("Masukkan Path lagi? (Y/N)");
                System.out.print("> ");
                String choice = cliScanner.next();
                if (choice.equals("Y") || choice.equals("y")) {
                    System.out.print("Masukkan Path lagi: ");
                    fileName = cliScanner.next();
                } else if (choice.equals("N") || choice.equals("n")) {
                    break;
                }
            }
        }

        return M;
    }

    public static Matrix readFileCLI(int row, int col) {
        System.out.print("Masukkan path: ");
        var fileName = cliScanner.next();
        var M = new Matrix(row, col);
        boolean isReadFileSucceed = false;
        while (!isReadFileSucceed) {
            try {
                M = readFile(fileName,row,col);
                isReadFileSucceed = true;
            } catch (IOException e) {
                System.out.println("Tidak bisa membaca file");
                System.out.println("Masukkan Path lagi? (Y/N)");
                System.out.print("> ");
                String choice = cliScanner.next();
                if (choice.equals("Y") || choice.equals("y")) {
                    System.out.print("Masukkan Path lagi: ");
                    fileName = cliScanner.next();
                } else if (choice.equals("N") || choice.equals("n")) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Format File salah");
                System.out.print("Masukkan Path lagi: ");
                System.out.println("Masukkan Path lagi? (Y/N)");
                System.out.print("> ");
                String choice = cliScanner.next();
                if (choice.equals("Y") || choice.equals("y")) {
                    System.out.print("Masukkan Path lagi: ");
                    fileName = cliScanner.next();
                } else if (choice.equals("N") || choice.equals("n")) {
                    break;
                }
            }
        }

        return M;
    }

}