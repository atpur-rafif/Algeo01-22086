package Matrix;

import java.util.Scanner;

public class MatrixReader {
    static Scanner scanner = new Scanner(System.in);

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
}