package FilePrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import CLI.IO.IOFile;
import CLI.IO.IOStringFormatter;
import Vector.VectorSpace;

public class MultiplePrinter {
    static Scanner scanner = new Scanner(System.in);
 
    public static void PrintFileVectorSpace(VectorSpace Result, String fileName){
        IOFile.createFile(fileName);
        try{
            FileWriter myRegWrite = new FileWriter(fileName);
            String plus = " + ";
            for(int i = 0; i < Result.basisCount; ++i){

                myRegWrite.write(Result.get(i) + "x" + IOStringFormatter.createSubscript(i) + plus);
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
