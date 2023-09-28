package Menu;

import Matrix.Matrix;
import Matrix.MatrixInverse;
import Matrix.MatrixPrinter;
import Matrix.MatrixReader;
import java.util.Scanner;

public class InverseMenu {
    static Scanner scanner = new Scanner(System.in);
    private static boolean isInverse = true; 
    private static boolean isInput = true;
    private static String choice;
    private static String inputChoice;
    private static Matrix inversedMatrix; 
    private static Matrix Matrix;
    
    public static void Run(){
        PrintListMenu.clear();
        
        while(isInverse){
            PrintListMenu.Print(new String[]{
                "=============================Inverse Menu=================================", 
                "1. Metode OBE", 
                "2. Metode Adjoin", 
                "3. Back",
                "*Note: Pilih menggunakan angka yang sesuai",
            });
            isInput = true;
            System.out.print("> ");
            choice = scanner.next(); 
            switch(choice){
                case "1": 
                    PrintListMenu.clear();
                    while(isInput){
                        PrintListMenu.Repetitive(7);   
                        System.out.print("> "); 
                        inputChoice = scanner.next();
                        switch(inputChoice){
                            case "1": 
                                Matrix = MatrixReader.readCLI(); 
                                inversedMatrix = MatrixInverse.calculateWithGaussJordan(Matrix);
                                System.out.println("Matriks Inverse: ");
                                MatrixPrinter.print(inversedMatrix);
                                Save.ResultMatrix(inversedMatrix);
                                break;
                            case "2":
                                Matrix = MatrixReader.readFileCLI(); 
                                inversedMatrix = MatrixInverse.calculateWithGaussJordan(Matrix);
                                System.out.println("Matriks Inverse: ");
                                MatrixPrinter.print(inversedMatrix);
                                Save.ResultMatrix(inversedMatrix);
                                break;
                            case "3": 
                                PrintListMenu.clear();
                                isInput = false;
                                break;
                            default: 
                                PrintListMenu.clear();
                                System.out.println("Input tidak valid");
                                break;
                            }
                        }
                    break; 
                case "2": 
                    PrintListMenu.clear();
                    while(isInput){
                        PrintListMenu.Repetitive(7);   
                        System.out.print("> "); 
                        inputChoice = scanner.next();
                        switch(inputChoice){
                            case "1": 
                                Matrix = MatrixReader.readCLI(); 
                                inversedMatrix = MatrixInverse.calculateWithCofactor(Matrix);
                                System.out.println("Matriks Inverse: ");
                                MatrixPrinter.print(inversedMatrix);
                                Save.ResultMatrix(inversedMatrix);
                                break;
                            case "2":
                                Matrix = MatrixReader.readFileCLI(); 
                                inversedMatrix = MatrixInverse.calculateWithCofactor(Matrix);
                                System.out.println("Matriks Inverse: ");
                                MatrixPrinter.print(inversedMatrix);
                                Save.ResultMatrix(inversedMatrix);
                                break;
                            case "3": 
                                PrintListMenu.clear();
                                isInput = false;
                                break;
                            default: 
                                PrintListMenu.clear();
                                System.out.println("Input tidak valid");
                                break;
                            }
                        }
                    break; 
                case "3": 
                    isInverse = false;
                    PrintListMenu.clear();
                    break;
                default: 
                    PrintListMenu.clear();
                    break;
            }
        }
    }
}
