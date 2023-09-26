import Image.ImageLoader;
import Image.ImageSaver;
import Image.Resize;
import Matrix.*;
import Matrix.OBE.OBERunner;
import Vector.EuclideanSpace;
import Vector.VectorSpace;
import Menu.*;
import java.util.Scanner;

public class Test {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        
        Matrix test = MatrixReader.readCLI(); 
        System.out.print("Berikan nama file: ");
        String fileName = scanner.next();
        MatrixPrinter.printMatrixFile(test, fileName);

    }
}