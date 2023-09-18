package Point;

public class Vector {
    private double[] data;

    public Vector(int size){
        this.data = new double[size];

        int i;
        for(i = 0; i < size; ++i){
            this.data[i] = 0;
        }
    }

    public double get(int i){
        return this.data[i];
    }
}