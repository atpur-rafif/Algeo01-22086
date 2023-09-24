package Menu;

import java.util.Scanner;
<<<<<<< HEAD
import java.lang.Runtime;
import Matrix.*;
=======

>>>>>>> main

public class MainMenu {
    static Scanner scanner = new Scanner(System.in);
    
    public static void InterfaceProgram(){
        System.out.println("================Halo Selamat Datang==================");
        System.out.println("=====================================================");
<<<<<<< HEAD
        System.out.println("\n");
=======
        System.out.println("\n\n");
        System.out.println("===================List Program======================");
>>>>>>> main
        ListProgram();
        System.out.println("Masukkan Input: ");
        System.out.print("> ");
        int choice = Integer.parseInt(scanner.next());
        while (choice >= 1 && choice < 9) {
            System.out.println("test");
<<<<<<< HEAD
            ListProgram();
            choice = Integer.parseInt(scanner.next());
            
=======
            choice = Integer.parseInt(scanner.next());

>>>>>>> main
        }

    }

    private static void ListProgram(){
<<<<<<< HEAD
        System.out.println("===================List Program======================");
        System.out.println("1. Gaussian Elimination");
        System.out.println("2. Inverse Matrix");
        System.out.println("3. Determinant");
        System.out.println("4. Interpolinom");
        System.out.println("5. Multiple Linear");
        System.out.println("6. Bicubic Spline");
        System.out.println("7. Quit");

    }

=======
        System.out.println("1. Gauss Elimination");
        System.out.println("2. GaussJordan Elimination");
        System.out.println("3. Inverse Matrix");
        System.out.println("4. Determinant");
        System.out.println("5. Interpolinom");
        System.out.println("6. Multiple Linear");
        System.out.println("7. Bicubic Spline");
        System.out.println("8. Quit");
    }
>>>>>>> main
}
