package Matrix;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MatrixReader {
    static Scanner scanner = new Scanner(System.in);
    static BufferedReader file;
    
    public static Matrix read(){
        System.out.print("Masukkan baris: ");
        int row = Integer.parseInt(scanner.next());
        
        System.out.print("Masukkan kolom: ");
        int col = Integer.parseInt(scanner.next());
        
        return MatrixReader.read(row, col);
    }
    
    public static Matrix read(int row, int col){
        var M = new Matrix(row, col);

        for(int i = 0; i < row; ++i){
            for(int j = 0; j < col; ++j){
                M.set(i, j, Double.parseDouble(scanner.next()));
            }
        }
        return M;
    }

    public static Matrix readFile(String fileName){
        var M = new Matrix(0, 0);
        boolean isReadFileSucceed = false; 
        while(!isReadFileSucceed) {
            try {
                file = new BufferedReader(new FileReader(fileName));
                var fileScanner = new Scanner(file);
                int row = Integer.parseInt(fileScanner.next());
                int col = Integer.parseInt(fileScanner.next());
                M = new Matrix(row, col); 
        
                for(int i = 0; i < row; ++i){
                    for(int j = 0; j < col; ++j){
                        M.set(i, j, Double.parseDouble(fileScanner.next()));
                    }
                }
                isReadFileSucceed = true;
                fileScanner.close();
            }
            catch (IOException e) {
                System.out.println("Tidak bisa membaca file");
                System.out.println("Masukkan Path lagi? (Y/N)");
                System.out.print("> ");
                String choice = scanner.next(); 
                if(choice.equals("Y") || choice.equals("y")){
                    System.out.print("Masukkan Path lagi: ");
                    fileName = scanner.next();
                }
                else if(choice.equals("N") || choice.equals("n")){
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Format File salah");
                System.out.print("Masukkan Path lagi: ");
                System.out.println("Masukkan Path lagi? (Y/N)");
                System.out.print("> ");
                String choice = scanner.next(); 
                if(choice.equals("Y") || choice.equals("y")){
                    System.out.print("Masukkan Path lagi: ");
                    fileName = scanner.next();
                }
                else if(choice.equals("N") || choice.equals("n")){
                    break;
                }
            }
        }

        return M;
    }

    public static Matrix readFileCLI() {
        System.out.print("Masukkan path: ");
        return readFile(scanner.next());
    }

}