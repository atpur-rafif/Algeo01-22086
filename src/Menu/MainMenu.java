package Menu;

import java.util.Scanner;


public class MainMenu {
    static Scanner scanner = new Scanner(System.in);
    
    public static void InterfaceProgram(){
        System.out.println("================Halo Selamat Datang==================");
        System.out.println("=====================================================");
        System.out.println("\n\n");
        System.out.println("===================List Program======================");
        ListProgram();
        System.out.println("Masukkan Input: ");
        System.out.print("> ");
        int choice = Integer.parseInt(scanner.next());
        while (choice >= 1 && choice < 9) {
            System.out.println("test");
            choice = Integer.parseInt(scanner.next());

        }

    }

    private static void ListProgram(){
        System.out.println("1. Gauss Elimination");
        System.out.println("2. GaussJordan Elimination");
        System.out.println("3. Inverse Matrix");
        System.out.println("4. Determinant");
        System.out.println("5. Interpolinom");
        System.out.println("6. Multiple Linear");
        System.out.println("7. Bicubic Spline");
        System.out.println("8. Quit");

    }
}
