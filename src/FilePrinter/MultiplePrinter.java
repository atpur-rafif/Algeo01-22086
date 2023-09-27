package FilePrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Menu.Save;
import Vector.VectorSpace;

public class MultiplePrinter {
    static Scanner scanner = new Scanner(System.in);
 
    public static void PrintFileVectorSpace(VectorSpace Result, String fileName){
        Save.CreateFile(fileName);
        try{
            FileWriter myRegWrite = new FileWriter(fileName);
            String plus = " + ";
            for(int i = 0; i < Result.basisCount; ++i){
                String currentSubscript = String.valueOf((char)('\u2080' + (i + 1)));
                if(i == Result.basisCount - 1){
                    plus = "";
                }
                myRegWrite.write(Result.get(i) + "x" + currentSubscript + plus);
            }  
        
            //print hasil determinant              
            myRegWrite.close();
            System.out.println("\nHasil Regresi Linear sudah ditulis di file" + fileName + "\n");

        }
        catch (IOException e){
            System.out.println("Ada error");
            e.printStackTrace();
        }
    }
    
    public static void printFileCLI(VectorSpace Result){
        System.out.print("Masukkan nama file: ");
        String fileName = scanner.next();
        PrintFileVectorSpace(Result, fileName);
    }

    
}
