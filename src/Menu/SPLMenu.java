package Menu;

import java.util.Scanner;
public class SPLMenu {
    static Scanner scanner = new Scanner(System.in);
    public static void Run(){
        boolean isSPL = true; 
        while(isSPL == true){
            boolean isInput = true;
            PrintListMenu.Print(new String[]{
                "=============================Sistem Persamaan Linear Menu=================================", 
                "1. Metode Gauss", 
                "2. Metode GaussJordan", 
                "3. Metode Cramer",
                "4. Back",
                "*Note: Pilih menggunakan angka yang sesuai",
            });
            System.out.print("> ");
            String choice = scanner.next(); 
            switch(choice){
                case "1":
                while(isInput){
                    PrintListMenu.Repetitive(5);
                    System.out.print("> ");
                    
                }
            }
        }
    }
}
