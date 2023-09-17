package Temp;

import java.util.Scanner;

public class MultipleLinearInput {
    static Scanner scanner = new Scanner(System.in);  
    public static double[][] getInput(){
        int m, n;
        System.out.print("Banyak peubah: ");
        n = Integer.parseInt(scanner.next());

        System.out.print("Banyak sampel: ");
        m = Integer.parseInt(scanner.next());
        
        var R = new double[m][n+1];
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n + 1; ++j){
                R[i][j] = Double.parseDouble(scanner.next());
            }
        }

        return R;
    }
}
