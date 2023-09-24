import Matrix.*;
import Matrix.OBE.OBERunner;

public class Test {
    public static void main(String[] args){
        
        Matrix testing = MatrixReader.readFileCLI();
        MatrixPrinter.print(testing);
    }
}