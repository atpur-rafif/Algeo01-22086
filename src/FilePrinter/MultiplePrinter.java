package FilePrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MultiplePrinter {
    static Scanner scanner = new Scanner(System.in);
    public static void printFile(double[] Result, String fileName){
            fileName = fileName + ".txt";
            File regression = new File(fileName); 
            while (regression.exists()){
                System.out.println("Nama file sudah ada, silahkan ganti namanya");
                System.out.print("Nama file: ");
                fileName = scanner.next();
                fileName = fileName + ".txt";
                regression = new File(fileName); 
            }
            System.out.println("File " + regression.getName() + " dibuat");

            //tulis hasil di file
            try{
                FileWriter myRegWrite = new FileWriter(fileName);
                String plus = " + ";
                for(int i = 0; i < Result.length; ++i){
                    String currentSubscript = String.valueOf((char)('\u2080' + (i + 1)));
                    if(i == Result.length - 1){
                        plus = "";
                    }
                    myRegWrite.write(Result[i] + "x" + currentSubscript + plus);
                }  
            
                //print hasil determinant              
                myRegWrite.close();
                System.out.println("\nHasil Regresi Linear sudah ditulis di file" + regression.getName());
            }
            catch (IOException e){
                System.out.println("Ada error");
                e.printStackTrace();
            }
    }
    
    public static void printFileCLI(double[] Result){
        System.out.print("Masukkan nama file: ");
        String fileName = scanner.next();
        printFile(Result, fileName);
    }
}
