package Menu;

import java.util.Scanner;
import Matrix.MatrixReader;
import Matrix.MatrixLinearEquation;
import Matrix.MatrixPrinter;
public class SPLMenu {
    static Scanner scanner = new Scanner(System.in);
    public static void Run(){
        PrintListMenu.clear();
        boolean isSPL = true; 
        String inputChoice;
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
                    PrintListMenu.clear();
                    while(isInput){
                        PrintListMenu.Repetitive(5);
                        System.out.print("> ");
                        inputChoice = scanner.next();
                        switch(inputChoice){
                            case "1":
                                System.out.println("Masukkan matriks koefisien :");
                                var MatriksKoefisien = MatrixReader.readCLI();
                                System.out.println("Masukkan matriks konstanta :");
                                var MatriksKonstanta = MatrixReader.readCLI(MatriksKoefisien.row,1);
                                var result = MatrixLinearEquation.solution(MatriksKoefisien,MatriksKonstanta,1);
                                System.out.println(result);
                                break;
                            case "2":
                                System.out.print("Masukkan baris : ");
                                int row = Integer.parseInt(scanner.next());

                                System.out.print("Masukkan kolom : ");
                                int col = Integer.parseInt(scanner.next());

                                var MatriksFile = MatrixReader.readFileCLI(row,col);
                                if (MatriksFile.row==0||MatriksFile.col==0){
                                    break;
                                }
                                MatrixPrinter.print(MatriksFile);
                                var resultFile = MatrixLinearEquation.solutionAugmented(MatriksFile, 1);
                                System.out.println(resultFile);
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
                        PrintListMenu.Repetitive(5);
                        System.out.print("> ");
                        inputChoice = scanner.next();
                        switch(inputChoice){
                            case "1":
                                System.out.println("Masukkan matriks koefisien :");
                                var MatriksKoefisien = MatrixReader.readCLI();
                                System.out.println("Masukkan matriks konstanta :");
                                var MatriksKonstanta = MatrixReader.readCLI(MatriksKoefisien.row,1);
                                var result = MatrixLinearEquation.solution(MatriksKoefisien,MatriksKonstanta,2);
                                System.out.println(result);
                                break;
                            case "2":
                                System.out.print("Masukkan baris : ");
                                int row = Integer.parseInt(scanner.next());

                                System.out.print("Masukkan kolom : ");
                                int col = Integer.parseInt(scanner.next());

                                var MatriksFile = MatrixReader.readFileCLI(row,col);
                                if (MatriksFile.row==0||MatriksFile.col==0){
                                    break;
                                }
                                MatrixPrinter.print(MatriksFile);
                                var resultFile = MatrixLinearEquation.solutionAugmented(MatriksFile, 2);
                                System.out.println(resultFile);
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
                    PrintListMenu.clear();
                    while(isInput){
                        PrintListMenu.Repetitive(5);
                        System.out.print("> ");
                        inputChoice = scanner.next();
                        switch(inputChoice){
                            case "1":
                                System.out.println("Masukkan matriks koefisien :");
                                var MatriksKoefisien = MatrixReader.readCLI();
                                System.out.println("Masukkan matriks konstanta :");
                                var MatriksKonstanta = MatrixReader.readCLI(MatriksKoefisien.row,1);
                                var result = MatrixLinearEquation.solution(MatriksKoefisien,MatriksKonstanta,3);
                                System.out.println(result);
                                break;
                            case "2":
                                System.out.print("Masukkan baris : ");
                                int row = Integer.parseInt(scanner.next());

                                System.out.print("Masukkan kolom : ");
                                int col = Integer.parseInt(scanner.next());

                                var MatriksFile = MatrixReader.readFileCLI(row,col);
                                if (MatriksFile.row==0||MatriksFile.col==0){
                                    break;
                                }
                                MatrixPrinter.print(MatriksFile);
                                var resultFile = MatrixLinearEquation.solutionAugmented(MatriksFile, 3);
                                System.out.println(resultFile);
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
                case "4":
                    isSPL = false;
                    PrintListMenu.clear();
                    break;
                default:
                    PrintListMenu.clear();
                    System.out.println("Input tidak valid");
                    break;
            }
        }
    }
}
