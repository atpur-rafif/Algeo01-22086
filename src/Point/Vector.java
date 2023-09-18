package Point;

public class Vector {
    private double[] data;
    public int dimension;

    public Vector(int dimension){
        this.dimension = dimension;
        this.data = new double[dimension];
        for(int i = 0; i < dimension; ++i) this.data[i] = 0;
    }

    public double getComponent(int i){
        return this.data[i];
    }

    public void setComponent(int i, double value){
        this.data[i] = value;
    }
}