package Menu;

import Matrix.Matrix;
import Matrix.MatrixDeterminant;
import Matrix.MatrixDeterminantWithOBE;
import Matrix.MatrixReader;
import java.util.Scanner;

public class DeterminantMenu {
    static Scanner scanner = new Scanner(System.in);
    public static void Display(){
        PrintListMenu.clear();
        boolean isDeterminant = true; 
        String inputChoice;
        double Result; 
        var DeterminantMatrix = new Matrix(0, 0); 
        while(isDeterminant == true){
            boolean isInput = true;
            PrintListMenu.Print(new String[]{
                "=============================Determinant Menu=================================", 
                "1. Metode OBE", 
                "2. Metode Kofaktor", 
                "3. Back",
                "*Note: Pilih menggunakan angka yang sesuai",
            });

            System.out.print("> ");
            String choice = scanner.next(); 
            switch(choice){
                case "1": 
                    PrintListMenu.clear();
                    while(isInput){
                        PrintListMenu.Repetitive(6);   
                        System.out.print("> "); 
                        inputChoice = scanner.next();
                        switch(inputChoice){
                            case "1": 
                                DeterminantMatrix = MatrixReader.readCLI(); 
                                Result = MatrixDeterminantWithOBE.calculate(DeterminantMatrix);
                                System.out.println("Determinan: " + Result);
                                break;
                            case "2":
                                DeterminantMatrix = MatrixReader.readFileCLI(); 
                                Result = MatrixDeterminantWithOBE.calculate(DeterminantMatrix);
                                System.out.println("Determinan: " + Result);
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
                        PrintListMenu.Repetitive(6);   
                        System.out.print("> "); 
                        inputChoice = scanner.next();
                        switch(inputChoice){
                            case "1": 
                                DeterminantMatrix = MatrixReader.readCLI(); 
                                Result = MatrixDeterminant.calculate(DeterminantMatrix);
                                System.out.println("Determinan: " + Result);
                                break;
                            case "2":
                                DeterminantMatrix = MatrixReader.readFileCLI(); 
                                Result = MatrixDeterminant.calculate(DeterminantMatrix);
                                System.out.println("Determinan: " + Result);
                                break;
                            case "3": 
                                isInput = false;
                                PrintListMenu.clear();
                                break;
                            default: 
                                PrintListMenu.clear();
                                System.out.println("Input tidak valid");
                                break;
                        }
                    }
                    break;
                case "3":
                    PrintListMenu.clear();
                    isDeterminant = false;
                    break;
                default: 
                    PrintListMenu.clear();
                    System.out.println("Input tidak Valid");
                    break;
            }
        }
    }
}
