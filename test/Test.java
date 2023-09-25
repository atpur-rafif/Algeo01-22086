import Matrix.*;
import Matrix.OBE.OBERunner;
import Vector.EuclideanSpace;
import Vector.VectorSpace;

public class Test {
    public static void main(String[] args){
        var a = new EuclideanSpace(1);
        var b = new EuclideanSpace(1);
        a.set(0, 10);

        var c = VectorSpace.scale(a, 10);
        System.out.println(c.get(0));
    }
}