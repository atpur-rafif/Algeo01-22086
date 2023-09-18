package Point;

public class Equation {
    private double[] data;

    public Equation(int size){
        this.data = new double[size];
        for(int i = 0; i < size; ++i) this.data[i] = 0;
    }

    public double getCoefficient(int i){
        return this.data[i];
    }
}
