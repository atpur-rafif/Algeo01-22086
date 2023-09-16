package Matrix;

import java.util.Scanner;

public class MatrixReader {
    static Scanner scanner = new Scanner(System.in);

    public static Matrix read(){
        System.out.print("Masukkan baris: ");
        int row = scanner.nextInt();

        System.out.print("Masukkan kolom: ");
        int col = scanner.nextInt();

        var M = new Matrix(row, col);

        for(int i = 0; i < row; ++i){
            for(int j = 0; j < col; ++j){
                M.set(i, j, scanner.nextDouble());
            }
        }

        return M;
    }
}