package Matrix;

import java.util.Scanner;

public class MatrixReader {
    public static Matrix read(){
        var scanner = new Scanner(System.in);

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

        scanner.close();
        return M;
    }
}