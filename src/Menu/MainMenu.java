package Menu;

import java.util.Scanner;
import java.lang.Runtime;
import Matrix.*;

public class MainMenu {
    static Scanner scanner = new Scanner(System.in);
    
    public static void InterfaceProgram(){
        System.out.println("================Halo Selamat Datang==================");
        System.out.println("=====================================================");
        System.out.println("\n");
        ListProgram();
        System.out.println("Masukkan Input: ");
        System.out.print("> ");
        int choice = Integer.parseInt(scanner.next());
        while (choice >= 1 && choice < 9) {
            System.out.println("test");
            ListProgram();
            choice = Integer.parseInt(scanner.next());
            
        }

    }

    private static void ListProgram(){
        System.out.println("===================List Program======================");
        System.out.println("1. Gaussian Elimination");
        System.out.println("2. Inverse Matrix");
        System.out.println("3. Determinant");
        System.out.println("4. Interpolinom");
        System.out.println("5. Multiple Linear");
        System.out.println("6. Bicubic Spline");
        System.out.println("7. Quit");

    }

}
