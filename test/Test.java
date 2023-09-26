import Application.MultipleLinear;
import Image.ImageLoader;
import Image.ImageSaver;
import Image.Resize;
import Matrix.*;
import Matrix.OBE.OBERunner;
import Vector.EquationSpace;
import Vector.EuclideanSpace;
import Vector.VectorSpace;

public class Test {
    public static void main(String[] args){
        var newMat = MatrixReader.readCLI();
        var result = MultipleLinear.solve(newMat);
        MultipleLinear.Display(result);
        
    }
}