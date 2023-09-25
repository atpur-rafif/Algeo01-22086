package Menu;


import java.util.Scanner;
public class InterpolinomMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void Display(){
        boolean isInterp = true; 
        PrintListMenu.clear(); 
        while(isInterp){
            PrintListMenu.Repetitive(1);
            System.out.print("> ");
            String choice = scanner.next(); 
            switch (choice){
                
                //CLI
                case "1": 

                    break;
                //FILE
                case "2": 

                    break; 

                case "3": 
                    isInterp = false;
                    PrintListMenu.clear();
                    break; 
            }
        }
    }
}
