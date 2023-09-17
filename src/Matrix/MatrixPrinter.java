package Matrix;

public class MatrixPrinter {
    public static void print(Matrix M){
        int n = M.row, m = M.col;

        for(int i = 0; i < n; ++i){
            for(int j = 0; j < m; ++j){
                if(M.get(i, j) != 0){
                    System.out.printf("%.2f ", M.get(i, j));
                } else {
                    System.out.printf("... ", M.get(i, j));
                }
            }
            System.out.print("\n");
        }
    }
}
