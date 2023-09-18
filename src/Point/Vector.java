package Point;

public class Vector {
    private double[] data;

    public Vector(int size){
        this.data = new double[size];
        for(int i = 0; i < size; ++i) this.data[i] = 0;
    }

    public double getComponent(int i){
        return this.data[i];
    }
}