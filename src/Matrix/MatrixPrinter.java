package Matrix;

public class MatrixPrinter {
    public static void print(Matrix M){
        int n = M.row, m = M.col;

        int i, j;
        for(i = 0; i < n; ++i){
            for(j = 0; j < m; ++j){
                System.out.printf("%.2f ", M.get(i, j));
            }
            System.out.print("\n");
        }
    }
}
