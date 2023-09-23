package Menu;

import java.util.Scanner;


public class MainMenu {
    static Scanner scanner = new Scanner(System.in);
    
    public static void InterfaceProgram(){
        System.out.println("================Halo Selamat Datang==================");
        System.out.println("=====================================================");
        System.out.println("\n\n");
        System.out.println("Masukkan Input: ");
        System.out.println("> ");
        int choice = Integer.parseInt(scanner.next());
        while (choice > 1 && choice < 10) {
            System.out.println("test");
            choice = Integer.parseInt(scanner.next());
        }



    }
}
